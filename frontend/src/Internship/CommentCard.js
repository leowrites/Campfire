import { useEffect, useState } from 'react';
import Typography from '@mui/material/Typography';
import Paper from '@mui/material/Paper';
import axios from 'axios';
import { useParams } from 'react-router-dom';
import Box from '@mui/material/Box';
import { Button } from '@mui/material';
import CommentBox from './CommentBox';
import useAuthContext from '../AuthContext';

export default function CommentCard({
  commentId,
  userId,
  parentType,
  reviewId,
  parentId,
  content,
  datePosted,
  comments,
}) {
  const { corporateId, internshipId } = useParams();
  const [showComment, setShowComment] = useState(false);
  const [moreComments, setMoreComments] = useState([]);
  const { principal } = useAuthContext();
  const handleDelete = () => {
    console.log(commentId, parentType, parentId, userId);
    axios
      .delete(
        `/corporates/${corporateId}/internships/${internshipId}/reviews/${reviewId}/comments`,
        {
          data: {
            commentId: commentId,
            parentType: parentType,
            parentId: parentId,
            userId: userId,
          },
        }
      )
      .then(() => {
        window.location.reload();
      });
  };
  const handleShowCommentBox = () => {
    setShowComment(!showComment);
  };

  useEffect(() => {
    fetchComments();
  }, []);

  const fetchComments = () => {
    console.log(commentId);
    axios
      .get(`/corporates/${corporateId}/internships/${internshipId}/reviews/${commentId}`)
      .then((res) => setMoreComments(res.data));
  };

  const postComment = (parentType, parentId, comment) => {
    console.log(parentType, parentId, comment, reviewId);
    axios
      .post(`/corporates/${corporateId}/internships/${internshipId}/reviews/${reviewId}/comments`, {
        userId: principal.username,
        parentType: parentType,
        parentId: parentId,
        content: comment,
      })
      .then((res) => {
        if (res.data.status === 'SUCCESS') {
          setShowComment(false);
          setMoreComments([
            ...moreComments,
            {
              id: res.data.id,
              content: comment,
              comments: [],
              datePosted: res.data.datePosted,
            },
          ]);
        }
      });
  };

  return (
    <Box
      sx={{
        background: 'rgba(22, 22, 22, 1)',
        borderRadius: 5,
        py: 1,
        px: 2,
      }}>
      <Box textAlign={'start'}>
        <Typography variant='h5'>{userId}: </Typography>
        <Typography>{content}</Typography>
        <Box textAlign={'right'}>
          <Typography sx={{ ml: 'auto' }}>{datePosted.match(/^\d{4}-\d{2}-\d{2}/)}</Typography>
        </Box>
        <Box sx={{ display: 'flex', my: 1 }}>
          <Button
            sx={{ ml: 'auto', mr: 1 }}
            variant='contained'
            onClick={handleShowCommentBox}
            color='primary'
            size='small'>
            comment
          </Button>
          <Button variant='contained' onClick={handleDelete} color='error' size='small'>
            delete
          </Button>
        </Box>
      </Box>
      {showComment ? (
        <Box sx={{ mt: 2 }}>
          <CommentBox
            handleShowCommentBox={handleShowCommentBox}
            postComment={postComment}
            parentType={'Comment'}
            parentId={commentId}
          />
        </Box>
      ) : undefined}

      {moreComments.length > 0 &&
        moreComments.map((comment) => {
          console.log(comment);
          return (
            <CommentCard
              key={comment.id}
              reviewId={reviewId}
              parentType={'Comment'}
              commentId={comment.id}
              parentId={commentId}
              userId={userId}
              content={comment.content}
              datePosted={comment.datePosted}
              comments={comments}
            />
          );
        })}
    </Box>
  );
}
