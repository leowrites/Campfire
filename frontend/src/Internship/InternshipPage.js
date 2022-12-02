import { useEffect, useState } from 'react';
import axios from 'axios';
import Typography from '@mui/material/Typography';
import Box from '@mui/material/Box';
import { useParams } from 'react-router-dom';
import ReviewCard from './ReviewCard';

export default function InternshipPage() {
  const [reviews, setReviews] = useState([]);
  const { corporateId, internshipId } = useParams();
  useEffect(() => {
    axios.get(`/corporates/${corporateId}/internships/${internshipId}/reviews`).then((data) => {
      console.log(data);
      setReviews(data.data);
    });
  }, []);
  return (
    <Box>
      <Typography>Internship {internshipId}</Typography>
      {reviews?.map((review) => (
        <ReviewCard id={review.id} />
      ))}
    </Box>
  );
}
