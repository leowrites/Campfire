import { useState } from 'react';
import Typography from '@mui/material/Typography';
import Paper from '@mui/material/Paper';
import Button from '@mui/material/Button';
import Box from '@mui/material/Box';
import TextField from '@mui/material/TextField';
import axios from 'axios';

function InternshipPaper({ title, info }) {
  const [showReviewForm, setShowReviewForm] = useState(false);
  const [reviewContent, setReviewContent] = useState('');

  const handleOpenReviewForm = () => {
    setShowReviewForm(!showReviewForm);
  };

  const handlePostReview = () => {
    axios.post('/corporates/apple/internships/1/reviews', {
      internshipId: "1",
      reviewContent: reviewContent,
      username: "leo"
    })
    .then(res => console.log(res.data))
    .then(() => {window.location.reload()})
  };
  return (
    <Paper elevation={2} sx={{ mb: 3, p: 3 }}>
      <Typography>{title}</Typography>
      <Typography>{info}</Typography>
      <Button size='medium'>View this internship</Button>
      {showReviewForm ? (
        <Box>
          <TextField
            label='comments'
            value={reviewContent}
            onChange={(e) => setReviewContent(e.target.value)}></TextField>
          <Button variant='contained' onClick={handleOpenReviewForm}>
            Cancel
          </Button>
          <Button variant='contained' onClick={handlePostReview}>
            Post
          </Button>
        </Box>
      ) : (
        <Button variant='contained' size='small' onClick={handleOpenReviewForm}>
          Review
        </Button>
      )}
    </Paper>
  );
}

export default InternshipPaper;
