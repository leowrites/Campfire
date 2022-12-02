import { useState } from 'react';
import Typography from '@mui/material/Typography';
import Paper from '@mui/material/Paper';
import Box from '@mui/material/Box';
import Button from '@mui/material/Button';
import axios from 'axios';
import CommentBox from './CommentBox';
import { useParams } from 'react-router-dom';

export default function ReviewCard({
  reviewId,
  userId,
  datePosted,
  numLikes,
  numDislikes,
  content,
  comments,
  rating,
}) {
  const { corporateId, internshipId } = useParams();
  const [showComment, setShowComment] = useState(false);
  const handleDelete = () => {
    axios.delete(`/corporates/${corporateId}/internships/${internshipId}`, {
      internshipId: internshipId,
      reviewId: reviewId,
      userId: userId,
    });
  };
  const handleShowCommentBox = () => {
    setShowComment(!showComment);
  };
  const fetchComments = () => {
    
  }
  return (
    <Paper elevation={2} sx={{ mb: 3, p: 3 }}>
      <Box textAlign='start'>
        <Typography>User: {userId}</Typography>
        <Typography>Date Posted: {datePosted}</Typography>
        <Typography>👍 {numLikes}</Typography>
        <Typography>👎 {numDislikes}</Typography>
        <Typography>Review: {content}</Typography>
        <Typography>{comments}</Typography>
        <Typography>Rating: {rating}/10</Typography>
        <Button variant='contained' onClick={handleShowCommentBox} color='success'>
          Leave a comment
        </Button>
        <Button variant='contained' onClick={handleDelete} color='error'>
          Delete this review
        </Button>
      </Box>
      {showComment ? (
        <Box sx={{ mt: 2 }}>
          <CommentBox handleShowCommentBox={handleShowCommentBox} />
        </Box>
      ) : (
        undefined
      )}
    </Paper>
  );
}
