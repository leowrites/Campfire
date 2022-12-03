import Typography from '@mui/material/Typography';
import Box from '@mui/material/Box';
import { useParams } from 'react-router-dom';
import Container from '@mui/material/Container';
import { useEffect, useState } from 'react';
import InternshipCard from './InternshipCard';
import Button from '@mui/material/Button';
import { useNavigate, Link } from 'react-router-dom';
import axios from 'axios';

export default function CorporatePage() {
  const { corporateId } = useParams();
  const { companyDetails, setCompanyDetails } = useState({});
  const navigate = useNavigate();
  useEffect(() => {
    axios.get(`/corporates/${corporateId}`).then((data) => {
      setCompanyDetails(data.data);
    });
  });
  return (
    <Box sx={{ my: 2 }} textAlign={'start'}>
      <Box sx={{ my: 2 }}>
        <Typography variant='h4'>Company Id: {corporateId}</Typography>
        <Typography>{companyDetails?.name}</Typography>
        <Typography>{companyDetails?.info}</Typography>
        <Typography sx={{ ml: 'auto' }}>
          Company Representitive?{' '}
          <Link to={`internships`} style={{ color: 'gray', textDecoration: 'none' }}>
            <Typography sx={{ display: 'inline-block' }}> Create an internship</Typography>
          </Link>
        </Typography>
      </Box>
      {companyDetails?.internships?.map((internship) => (
        <InternshipCard id={internship.id} />
      ))}
      <InternshipCard id={4} />
    </Box>
  );
}
