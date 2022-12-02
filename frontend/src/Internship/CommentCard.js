import { useEffect, useState } from 'react';
import Typography from '@mui/material/Typography';
import Paper from '@mui/material/Paper';
import axios from 'axios';
import { useParams } from 'react-router-dom';
import Box from '@mui/material/Box';
import { Button } from '@mui/material';
import CommentBox from './CommentBox';

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
    const commentRequests = [];
    comments.forEach((commentId) => commentRequests.push(axios.get(`/comments/${commentId}`)));
    axios.all(commentRequests).then(
      axios.spread((...response) => {
        const commentResponse = response.map(res => res.data);
        setMoreComments(commentResponse);
      })
    );
  };

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
          <CommentBox handleShowCommentBox={handleShowCommentBox} />
        </Box>
      ) : undefined}

      {moreComments.length > 0 &&
        moreComments.map((comment) => {
          return (
            <CommentCard
              key={comment.commentId}
              reviewId={reviewId}
              parentType={'Review'}
              commentId={comment.commentId}
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
