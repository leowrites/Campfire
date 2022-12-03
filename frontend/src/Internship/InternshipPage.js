import { useEffect, useState } from 'react';
import axios from 'axios';
import Typography from '@mui/material/Typography';
import Box from '@mui/material/Box';
import { useParams } from 'react-router-dom';
import ReviewCard from './ReviewCard';
import Button from '@mui/material/Button';

export default function InternshipPage() {
  const [reviews, setReviews] = useState([]);
  const { corporateId, internshipId } = useParams();
  useEffect(() => {
    axios.get(`/corporates/${corporateId}/internships/${internshipId}/reviews`).then((data) => {
      console.log(data);
      setReviews(data.data);
    });
  }, []);
  // allow user to add a review through a button
  return (
    <Box sx={{ my: 2 }} textAlign='start'>
      <Typography variant='h4'>Internship Id: {internshipId}</Typography>
      <Button>Add a review</Button>

      {reviews?.map((review) => (
        <ReviewCard id={review.id} />
      ))}
    </Box>
  );
}
