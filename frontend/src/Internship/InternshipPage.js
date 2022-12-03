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
  const [internshipDetails, setInternshipsDetails] = useState({});
  const [showCommentBox, setShowCommentBox] = useState(false);
  const { principal } = useAuthContext();
  const handleShowCommentBox = () => {
    setShowCommentBox(!showCommentBox);
  };

  // why is it getting all reviews

  useEffect(() => {
    axios.get(`/corporates/${corporateId}/internships/${internshipId}`).then((data) => {
      setInternshipsDetails(data.data);
    });
  }, []);

  const getReviews = () => {
    axios.get(`/corporates/${corporateId}/internships/${internshipId}/reviews`).then((data) => {
      console.log(data.data);
      setReviews(data.data);
    });
  };
  useEffect(() => {
    getReviews();
  }, []);

  const postReview = (comment, rating) => {
    axios
      .post(`/corporates/${corporateId}/internships/${internshipId}/reviews`, {
        reviewContent: comment,
        username: principal.username,
        rating: rating,
      })
      .then((res) => res.data.status === 'SUCCESS' && getReviews())
      .then(() => setShowCommentBox(false));
  };

  return (
    <Box sx={{ my: 2 }} textAlign='start'>
      <Typography variant='h4'>{internshipDetails.jobTitle}</Typography>
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
            postComment={postReview}
            parentType={'Internship'}
            parentId={internshipId}
          />
        </Box>
      ) : null}
      {reviews?.map((review, i) => (
        <ReviewCard
          key={i}
          reviewId={review.id}
          userId={review.userId}
          datePosted={review.datePosted}
          numLikes={review.numLikes}
          numDislikes={review.numDislikes}
          content={review.content}
          comments={review.comments}
          rating={review.rating}
        />
      ))}
    </Box>
  );
}
