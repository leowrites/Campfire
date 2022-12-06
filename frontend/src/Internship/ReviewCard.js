import { useEffect, useState } from 'react';
import Typography from '@mui/material/Typography';
import Paper from '@mui/material/Paper';
import Box from '@mui/material/Box';
import Button from '@mui/material/Button';
import axios from 'axios';
import CommentBox from './CommentBox';
import { useParams } from 'react-router-dom';
import CommentCard from './CommentCard';
import useAuthContext from '../AuthContext';

export default function ReviewCard({
  reviewId,
  userId,
  datePosted,
  numLikes,
  numDislikes,
  content,
  rating,
}) {
  const { corporateId, internshipId } = useParams();
  const [showComment, setShowComment] = useState(false);
  const [moreComments, setMoreComments] = useState([]);
  const authContext = useAuthContext();
  const principal = authContext.principal;
  const handleDelete = () => {
    console.log('called');
    axios
      .delete(`/corporates/${corporateId}/internships/${internshipId}/reviews/${reviewId}`, {
        data: {
          internshipId: internshipId,
          reviewId: reviewId,
          userId: userId,
        },
      })
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
    axios
      .get(`/corporates/${corporateId}/internships/${internshipId}/reviews/${reviewId}`)
      .then((res) => setMoreComments(res.data));
  };

  const postComment = (parentType, parentId, comment) => {
    if (principal) {
      axios
        .post(
          `/corporates/${corporateId}/internships/${internshipId}/reviews/${reviewId}/comments`,
          {
            userId: principal.username,
            parentType: parentType,
            parentId: parentId,
            content: comment,
          }
        )
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
    }
  };

  return (
    <Paper elevation={2} sx={{ mb: 3, p: 3 }}>
      <Box textAlign='start'>
        <Box sx={{ display: 'flex' }}>
          <Typography variant='h5'>{userId}: </Typography>
          <Typography sx={{ display: 'flex', ml: 'auto' }}>👍 {numLikes}</Typography>
          <Typography>👎 {numDislikes}</Typography>
        </Box>
        <Typography>{content}</Typography>
        <Typography>Rating: {rating}/10</Typography>
        <Box sx={{ display: 'flex' }}>
          <Typography sx={{ display: 'flex', ml: 'auto' }}>Date Posted: {datePosted}</Typography>
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
            delete
          </Button>
        </Box>
      </Box>
      {showComment ? (
        <Box sx={{ mt: 2 }}>
          <CommentBox
            handleShowCommentBox={handleShowCommentBox}
            parentType={'Review'}
            parentId={reviewId}
            postComment={postComment}
          />
        </Box>
      ) : undefined}

      {moreComments.length > 0 &&
        moreComments.map((comment, i) => {
          console.log(comment);
          return (
            <CommentCard
              key={i}
              reviewId={reviewId}
              parentType={'Review'}
              commentId={comment.id}
              parentId={reviewId}
              userId={userId}
              content={comment.content}
              datePosted={comment.datePosted}
              comments={comment.comments}
            />
          );
        })}
    </Paper>
  );
}
