import Typography from '@mui/material/Typography';
import Box from '@mui/material/Box';
import { useParams } from 'react-router-dom';
import { useEffect, useState } from 'react';
import InternshipCard from './InternshipCard';
import { Link } from 'react-router-dom';
import axios from 'axios';
import { Container } from '@mui/system';
import applepark from './applepark.jpg';
import React from 'react';

export default function CorporatePage() {
  const { corporateId } = useParams();
  const [companyDetails, setCompanyDetails] = useState<CompanyDetails>({} as CompanyDetails);
  const [internships, setInternships] = useState<InternshipDetails[]>([]);
  useEffect(() => {
    axios.get(`/corporates/${corporateId}`).then((data) => {
      setCompanyDetails(data.data);
    });
  }, []);
  useEffect(() => {
    axios.get(`/corporates/${corporateId}/internships`).then((data) => {
      console.log('internships', internships);
      setInternships(data.data);
    });
  }, []);
  return (
    <>
      <Box
        textAlign={'start'}
        sx={{
          backgroundImage: `url(${applepark})`,
          backgroundSize: 'cover',
          backgroundPosition: 'center',
          minHeight: '20rem',
          display: 'flex',
          alignItems: 'center',
        }}>
        <Container sx={{ mx: 2 }}>
          <Box sx={{ display: 'flex', alignItems: 'baseline', color: 'white' }}>
            <Typography variant='h1' sx={{ fontWeight: 'bold' }}>
              {companyDetails?.companyName}
            </Typography>
            <Typography variant='h6' sx={{ fontWeight: 'bold', ml: 5 }}>
              Cupertino, California, United States.
            </Typography>
          </Box>
          <Typography variant='h4' sx={{ display: 'block', color: 'white', mt: 2 }}>
            {companyDetails?.companyInfo}
          </Typography>
        </Container>
      </Box>
      <Container>
        <Box sx={{ mt: 5 }}>
          {internships?.map((internship) => (
            <InternshipCard key={internship.id} id={internship.id} jobTitle={internship.jobTitle} />
          ))}
        </Box>
      </Container>
      <Box>
        <Typography sx={{ ml: 'auto', mt: 20, display: 'inline-block', color: 'white' }}>
          Company Representitive?{' '}
          <Link to={`internships`} style={{ color: 'gray', textDecoration: 'none' }}>
            Create an internship
          </Link>
        </Typography>
      </Box>
    </>
  );
}
