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
    axios.delete(
      `/corporates/${corporateId}/internships/${internshipId}/reviews/${reviewId}/comments`,
      {
        commentId: commentId,
        parentType: parentType,
        parentId: parentId,
        userId: userId,
      }
    );
  };
  const handleShowCommentBox = () => {
    setShowComment(!showComment);
  };

  useEffect(() => {
    fetchComments();
  }, []);

  const fetchComments = () => {
    console.log(commentId)
    axios
      .get(`/corporates/${corporateId}/internships/${internshipId}/reviews/${commentId}`)
      .then((res) => setMoreComments(res.data));
  };

  const postComment = (parentType, parentId, comment) => {
    console.log(parentType, parentId, comment, reviewId);
    axios.post(`/corporates/${corporateId}/internships/${internshipId}/reviews/${reviewId}/comments`, {
        userId: principal.username,
        parentType: parentType,
        parentId: parentId,
        content: comment,
    })
    .then(res => {
      if (res.data.status === "SUCCESS") {
        setShowComment(false);
        setMoreComments([...moreComments, {
          commentId: res.data.commentId,
          content: comment,
          comments: [],
          datePosted: res.data.datePosted
        }]);
      }
    })
}

  return (
    <Paper elevation={2} sx={{ my: 3, p: 3 }}>
      <Box textAlign={'start'}>
        <Typography variant='h5'>{userId}: </Typography>
        <Typography>{content}</Typography>
        <Box sx={{ display: 'flex' }}>
          <Typography sx={{ ml: 'auto' }}>Date Posted: {datePosted}</Typography>
        </Box>
        <Box sx={{ display: 'flex', my: 1 }}>
          <Button
            sx={{ ml: 'auto', mr: 1 }}
            variant='contained'
            onClick={handleShowCommentBox}
            color='success'
            size='small'>
            comment
          </Button>
          <Button variant='contained' onClick={handleDelete} color='error' size='small'>
            Delete
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
          console.log(comment)
          return (
            <CommentCard
              key={comment.id}
              reviewId={reviewId}
              parentType={'Review'}
              commentId={comment.id}
              parentId={comment.commentI}
              userId={userId}
              content={comment.content}
              datePosted={comment.datePosted}
              comments={comments}
            />
          );
        })}
    </Paper>
  );
}
