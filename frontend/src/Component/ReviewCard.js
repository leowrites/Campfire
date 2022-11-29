import Typography from '@mui/material/Typography';
import Paper from '@mui/material/Paper';
import Box from '@mui/material/Box';

export default function ReviewCard({
  company,
  userID,
  datePosted,
  numLikes,
  numDislikes,
  content,
  comments,
  rating,
}) {
  return (
    <Paper elevation={2} sx={{ mb: 3, p: 3 }}>
      <Box textAlign='start'>
        <Typography> Company: {company}</Typography>
        <Typography>User: {userID}</Typography>
        <Typography>Date Posted: {datePosted}</Typography>
        <Typography>👍 {numLikes}</Typography>
        <Typography>👎 {numDislikes}</Typography>
        <Typography>Review: {content}</Typography>
        <Typography>{comments}</Typography>
        <Typography>Rating: {rating}/10</Typography>
      </Box>
    </Paper>
  );
}
