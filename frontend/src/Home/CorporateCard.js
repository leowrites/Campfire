import Typography from '@mui/material/Typography';
import Paper from '@mui/material/Paper';
import Button from '@mui/material/Button';
import Box from '@mui/material/Box';
import { useNavigate } from 'react-router';
export default function CorporateCard({ name, info, id }) {
  const navigate = useNavigate();
  return (
    <Paper
      elevation={20}
      sx={{
        mb: 3,
        p: 3,
        background: 'linear-gradient(to right top, #266126, #296723, #2d6d20, #31721c, #367816)',
        borderRadius: 5,
        minWidth: '50rem',
        maxWidth: '50rem',
        transition: 'all 0.3s ease-out',
        '&:hover': {
          transform: 'scale(1.02)',
          cursor: 'pointer',
        },
      }}
      onClick={() => navigate(`/corporates/${id}`)}>
      <Box sx={{ textAlign: 'start' }}>
        <Typography variant='h5' sx={{ color: 'white', fontWeight: 'bold' }}>{name}</Typography>
        <Typography sx={{ color: 'white', fontWeight: 'bold' }}>{info}</Typography>
      </Box>
    </Paper>
  );
}
