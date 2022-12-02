import Typography from '@mui/material/Typography';
import Paper from '@mui/material/Paper';
import Button from '@mui/material/Button';
import Box from '@mui/material/Box';
import { useNavigate } from 'react-router';
export default function CorporateCard({ name, info, id }) {
  const navigate = useNavigate();
  return (
    <Paper elevation={2} sx={{ mb: 3, p: 3 }}>
      <Box sx={{ textAlign: 'start' }}>
        <Typography>{name}</Typography>
        <Typography>{info}</Typography>
        <Button size='medium' onClick={() => navigate(`/corporates/${id}`)}>
          View this Company
        </Button>
      </Box>
    </Paper>
  );
}
