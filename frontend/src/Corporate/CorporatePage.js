import Typography from '@mui/material/Typography';
import Box from '@mui/material/Box';
import { useParams } from 'react-router-dom';
import Container from '@mui/material/Container';
import { useEffect, useState } from 'react';
import InternshipCard from './InternshipCard';
import axios from 'axios';

export default function CorporatePage() {
  const { corporateId } = useParams();
  const { companyDetails, setCompanyDetails } = useState({});
  useEffect(() => {
    axios.get(`/corporates/${corporateId}`).then((data) => {
      setCompanyDetails(data.data);
    });
  });
  return (
    <Box sx={{ mt: 2 }}>
      <Typography variant='h4'>Company Id: {corporateId}</Typography>
      <Typography>{companyDetails?.name}</Typography>
      <Typography>{companyDetails?.info}</Typography>
      <Container>
        {companyDetails?.internships?.map((internship) => (
          <InternshipCard id={internship.id} />
        ))}
        <InternshipCard id={4} />
      </Container>
    </Box>
  );
}
