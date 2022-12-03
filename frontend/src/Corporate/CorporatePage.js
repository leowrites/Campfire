import Typography from '@mui/material/Typography';
import Box from '@mui/material/Box';
import { useParams } from 'react-router-dom';
import { useEffect, useState } from 'react';
import InternshipCard from './InternshipCard';
import { Link } from 'react-router-dom';
import axios from 'axios';

export default function CorporatePage() {
  const { corporateId } = useParams();
  const [companyDetails, setCompanyDetails] = useState({});
  const [internships, setInternships] = useState([]);
  useEffect(() => {
    axios.get(`/corporates/${corporateId}`).then((data) => {
      setCompanyDetails(data.data);
    });
  }, []);
  useEffect(() => {
    axios.get(`/corporates/${corporateId}/internships`).then((data) => {
      console.log("internships", internships)
      setInternships(data.data);
    });
  }, []);
  return (
    <Box sx={{ my: 2 }} textAlign={'start'}>
      <Box sx={{ my: 2, display: 'flex', alignItems: 'center' }}>
        <Typography variant='h4'>{companyDetails?.companyName}</Typography>
        <Typography sx={{ ml: 'auto', display: 'inline-block' }}>
          Company Representitive?{' '}
          <Link to={`internships`} style={{ color: 'gray', textDecoration: 'none' }}>
            Create an internship
          </Link>
        </Typography>
      </Box>
      <Typography variant='h7' sx={{ display: 'block' }}>
        {companyDetails?.companyInfo}
      </Typography>
      {internships?.map((internship) => (
        <InternshipCard key={internship.id} id={internship.id} jobTitle={internship.jobTitle} />
      ))}
    </Box>
  );
}
