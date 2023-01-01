import { useEffect, useState } from 'react';
import axios from 'axios';
import Typography from '@mui/material/Typography';
import Box from '@mui/material/Box';
import { useParams } from 'react-router-dom';
import ReviewCard from './ReviewCard';
import Rating from '@mui/material/Rating';
import FavoriteBorderIcon from '@mui/icons-material/FavoriteBorder';
import FavoriteIcon from '@mui/icons-material/Favorite';
import KeyboardBackspaceIcon from '@mui/icons-material/KeyboardBackspace';
import IconButton from '@mui/material/IconButton';
import { useNavigate } from 'react-router-dom';
import React from 'react';
import { usePostReview } from './hooks';
import useAuthContext from '../AuthContext';
import CommentBox from './CommentBox';

export default function InternshipPage() {
  const [reviews, setReviews] = useState<Review[]>([]);
  const { corporateId, internshipId } = useParams();
  const [internshipDetails, setInternshipsDetails] = useState<InternshipDetails>(
    {} as InternshipDetails
  );
  const { principal } = useAuthContext();
  const [showCommentBox, setShowCommentBox] = useState(false);
  const [rating, setRating] = useState(0);
  const navigate = useNavigate();
  const handleShowCommentBox = () => {
    setShowCommentBox(!showCommentBox);
  };

  useEffect(() => {
    fetchInternshipDetails();
  }, []);

  const { postReview } = usePostReview();

  const fetchInternshipDetails = () => {
    axios
      .get<InternshipDetails | undefined>(`/corporates/${corporateId}/internships/${internshipId}`)
      .then((response) => {
        if (!response.data) return navigate('/');
        setInternshipsDetails(response.data);
        setReviews(response.data.reviews);
      });
  };

  const handlePost = (comment: string): void => {
    if (!principal || !corporateId || !internshipId) return;
    postReview(corporateId, internshipId, comment, rating)
    .then(() => {
      fetchInternshipDetails();
      setShowCommentBox(false);
    })
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
              setRating(newValue || 0);
            }}
            icon={<FavoriteIcon fontSize='inherit' htmlColor='white' />}
            emptyIcon={<FavoriteBorderIcon fontSize={'inherit'} sx={{ color: 'white' }} />}
          />
          <CommentBox
            handleShowCommentBox={handleShowCommentBox}
            handlePost={handlePost}
          />
        </Box>
      ) : null}
      {reviews?.map((review, i) => (
        <ReviewCard
          key={i}
          id={review.id}
          userId={review.user.username}
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
