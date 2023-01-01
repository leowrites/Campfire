import Typography from '@mui/material/Typography';
import Box from '@mui/material/Box';
import { Link } from 'react-router-dom';
import { useParams, useNavigate } from 'react-router-dom';
import React from 'react';

interface InternshipCardProps {
  id: string;
  jobTitle: string;
}

function InternshipCard({ id, jobTitle }: InternshipCardProps) {
  const { corporateId } = useParams();

  return (
    <Box textAlign={'start'} sx={{ color: 'white', my: 5, py: 2, borderBottom: '1px solid gray' }}>
      <Link
        style={{ textDecoration: 'none', color: 'white' }}
        to={`/corporates/${corporateId}/internships/${id}`}>
        <Typography variant='h4' sx={{}}>
          {id} - {jobTitle}
        </Typography>
      </Link>
    </Box>
  );
}

export default InternshipCard;
