import Typography from '@mui/material/Typography';
import Paper from '@mui/material/Paper';
import Box from '@mui/material/Box';
import { useNavigate } from 'react-router';
import React from 'react';

interface CorporateCardProps {
  name: string,
  info: string,
  id: string
}

export default function CorporateCard({ name, info, id }: CorporateCardProps): JSX.Element {
  const navigate = useNavigate();
  return (
    <Paper
      elevation={20}
      sx={{
        my: 2,
        p: 3,
        mx: 3,
        display: 'inline-flex',
        background: 'linear-gradient(to right top, #266126, #296723, #2d6d20, #31721c, #367816)',
        borderRadius: 5,
        width:'fit-content',
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
