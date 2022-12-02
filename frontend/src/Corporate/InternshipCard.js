import { useEffect, useState } from 'react';
import Typography from '@mui/material/Typography';
import Paper from '@mui/material/Paper';
import Button from '@mui/material/Button';
import axios from 'axios';
import { useParams, useNavigate } from 'react-router-dom';

function InternshipCard({ id }) {
  const { corporateId } = useParams();
  const navigate = useNavigate();
  const [intenrshipDetails, setInternshipDetails] = useState({});

  // fetch internship details
  useEffect(() => {
    axios.get(`/corporates/${corporateId}internships/${id}`).then((data) => {
      setInternshipDetails(data.data);
    });
  });

  return (
    <Paper elevation={2} sx={{ mb: 3, p: 3 }}>
      <Typography>{intenrshipDetails.title}</Typography>
      <Typography>{intenrshipDetails.info}</Typography>
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
