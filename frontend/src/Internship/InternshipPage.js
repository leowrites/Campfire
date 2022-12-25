import { useEffect, useState } from 'react';
import axios from 'axios';
import Typography from '@mui/material/Typography';
import Box from '@mui/material/Box';
import { useParams } from 'react-router-dom';
import ReviewCard from './ReviewCard';
import CommentCard from '../Internship/CommentBox';
import useAuthContext from '../AuthContext';
import Rating from '@mui/material/Rating';
import FavoriteBorderIcon from '@mui/icons-material/FavoriteBorder';
import FavoriteIcon from '@mui/icons-material/Favorite';
import KeyboardBackspaceIcon from '@mui/icons-material/KeyboardBackspace';
import IconButton from '@mui/material/IconButton';
import { useNavigate } from 'react-router-dom';

export default function InternshipPage() {
  const [reviews, setReviews] = useState([]);
  const { corporateId, internshipId } = useParams();
  const [internshipDetails, setInternshipsDetails] = useState({});
  const [showCommentBox, setShowCommentBox] = useState(false);
  const [rating, setRating] = useState(0);
  const navigate = useNavigate();
  const { principal } = useAuthContext();
  const handleShowCommentBox = () => {
    setShowCommentBox(!showCommentBox);
  };

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
    <Box sx={{ my: 2, color: 'white' }} textAlign='start'>
      <IconButton sx={{ p: 0 }} onClick={() => navigate(-1)}>
        <KeyboardBackspaceIcon sx={{ color: 'white' }} />
      </IconButton>
      <Box sx={{ display: 'flex', alignItems: 'baseline' }}>
        <Typography variant='h4' sx={{ display: 'inline-flex' }}>
          {internshipDetails.jobTitle}
        </Typography>
        <Typography
          sx={{
            ml: 'auto',
            display: 'inline-flex',
            ':hover': {
              cursor: 'pointer',
            },
          }}
          onClick={handleShowCommentBox}>
          Add a review
        </Typography>
      </Box>
      {showCommentBox ? (
        <Box sx={{ background: 'rgba(22, 22, 22, 1)', p: 4, borderRadius: 5, my: 3 }}>
          <Typography variant='h5' sx={{ fontWeight: 'bold', mb: 2 }}>
            Write a review
          </Typography>
          <Rating
            sx={{ mb: 2 }}
            defaultValue={0}
            onChange={(e, newValue) => {
              setRating(newValue)
            }}
            icon={<FavoriteIcon fontSize='inherit' color='white' />}
            emptyIcon={<FavoriteBorderIcon fontSize={'inherit'} sx={{ color: 'white' }} />}
          />
          <CommentCard
            rating={rating}
            handleShowCommentBox={handleShowCommentBox}
            postReview={postReview}
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
