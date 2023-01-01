import { Outlet, useNavigate } from 'react-router';
import Typography from '@mui/material/Typography';
import Box from '@mui/material/Box';
import { useParams } from 'react-router-dom';
import { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import axios from 'axios';
import { Container } from '@mui/system';
import applepark from './applepark.jpg';
import React from 'react';

interface GetCorporateResponse {
  companyName: string | undefined;
  companyInfo: string | undefined;
}

export default function CorporateLayout() {
  const { corporateId } = useParams();
  const [companyDetails, setCompanyDetails] = useState<CompanyDetails>({} as CompanyDetails);
  const navigate = useNavigate();
  useEffect(() => {
    axios
      .get<GetCorporateResponse>(`/corporates/${corporateId}`)
      .then((response) => {
        if (response.data.companyName && response.data.companyInfo) {
          setCompanyDetails({
            companyName: response.data.companyName,
            companyInfo: response.data.companyInfo,
          });
        } else {
          navigate('/');
        }
      })
      .catch((error) => {
        console.log(error);
        navigate('/');
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
              {companyDetails.companyName}
            </Typography>
            <Typography variant='h6' sx={{ fontWeight: 'bold', ml: 5 }}>
              Cupertino, California, United States.
            </Typography>
          </Box>
          <Typography variant='h4' sx={{ display: 'block', color: 'white', mt: 2 }}>
            {companyDetails.companyInfo}
          </Typography>
        </Container>
      </Box>
      <Container>
        <Outlet />
      </Container>
      <Box>
        <Typography sx={{ ml: 'auto', mt: 20, display: 'inline-block', color: 'white' }}>
          Company Representitive?{' '}
          <Link
            to={`/corporates/${corporateId}/internships/create`}
            style={{ color: 'gray', textDecoration: 'none' }}>
            Create an internship
          </Link>
        </Typography>
      </Box>
    </>
  );
}
