import Typography from '@mui/material/Typography';
import Box from '@mui/material/Box';
import { useParams } from 'react-router-dom';
import { useEffect, useState } from 'react';
import InternshipCard from './InternshipCard';
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
      <Box sx={{ my: 2, display: 'flex', alignItems: 'center' }}>
        <Typography variant='h4' sx={{ display: 'inline-block' }}>
          Company Id: {corporateId}
        </Typography>
        <Typography sx={{ ml: 'auto', display: 'inline-block' }}>
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
