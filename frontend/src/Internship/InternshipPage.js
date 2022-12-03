import { useEffect, useState } from 'react';
import axios from 'axios';
import Typography from '@mui/material/Typography';
import Box from '@mui/material/Box';
import { useParams } from 'react-router-dom';
import ReviewCard from './ReviewCard';
import Button from '@mui/material/Button';
import CommentCard from '../Internship/CommentBox';
import useAuthContext from '../AuthContext';
import Rating from '@mui/material/Rating';
import FavoriteBorderIcon from '@mui/icons-material/FavoriteBorder';
import FavoriteIcon from '@mui/icons-material/Favorite';

export default function InternshipPage() {
  const [reviews, setReviews] = useState([]);
  const { corporateId, internshipId } = useParams();
  const [showCommentBox, setShowCommentBox] = useState(false);
  const auth = useAuthContext();
  const handleShowCommentBox = () => {
    setShowCommentBox(!showCommentBox);
  };
  useEffect(() => {
    axios.get(`/corporates/${corporateId}/internships/${internshipId}/reviews`).then((data) => {
      console.log(data);
      setReviews(data.data);
    });
  }, []);

  const postComment = (comment, rating) => {
    axios.post(`/corporates/${corporateId}/internships/${internshipId}/reviews`, {
      reviewContent: comment,
      username: auth.username,
      rating: rating,
    });
  };

  return (
    <Box sx={{ my: 2 }} textAlign='start'>
      <Typography variant='h4'>Internship Id: {internshipId}</Typography>
      <Button onClick={handleShowCommentBox}>Add a review</Button>
      {showCommentBox ? (
        <Box>
          <Rating
            defaultValue={0}
            icon={<FavoriteIcon fontSize='inherit' />}
            emptyIcon={<FavoriteBorderIcon fontSize={'inherit'} />}
          />
          <CommentCard
            handleShowCommentBox={handleShowCommentBox}
            postComment={postComment}
            parentType={'Review'}
            parentId={internshipId}
          />
        </Box>
      ) : null}
      {reviews?.map((review) => (
        <ReviewCard id={review.id} />
      ))}
    </Box>
  );
}
