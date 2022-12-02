import { useEffect, useState } from 'react';
import Typography from '@mui/material/Typography';
import Paper from '@mui/material/Paper';
import Box from '@mui/material/Box';
import Button from '@mui/material/Button';
import axios from 'axios';
import CommentBox from './CommentBox';
import { useParams } from 'react-router-dom';
import CommentCard from './CommentCard';
import useAuthContext from "../AuthContext";

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
  const [moreComments, setMoreComments] = useState([]);
  const authContext = useAuthContext();
  const principal = authContext.principal;
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

  const postComment = (parentType, parentId, comment) => {
    axios.post(`/comments`, {
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
          return (
            <CommentCard
              key={i}
              reviewId={reviewId}
              parentType={'Review'}
              commentId={comment.commentId}
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
