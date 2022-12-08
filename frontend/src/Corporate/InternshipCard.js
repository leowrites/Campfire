import Typography from '@mui/material/Typography';
import Paper from '@mui/material/Paper';
import Button from '@mui/material/Button';
import { useParams, useNavigate } from 'react-router-dom';

function InternshipCard({ id, jobTitle }) {
  const { corporateId } = useParams();
  const navigate = useNavigate();

  return (
    <Paper elevation={2} sx={{ mb: 3, p: 3 }}>
      <Typography>{jobTitle}</Typography>
      <Button
        size='medium'
        onClick={() => {
          navigate(`/corporates/${corporateId}/internships/${id}`);
        }}>
        View this internship
      </Button>
    </Paper>
  );
}

export default InternshipCard;
