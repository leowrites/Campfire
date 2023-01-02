import React, { useState, useEffect } from 'react';
import Grid from '@mui/material/Grid';
import Box from '@mui/material/Box';
import Typography from '@mui/material/Typography';
import Autocomplete from '@mui/material/Autocomplete';
import CustomTextField from '../Component/CustomTextfield';
import Image from './bannerImage.png';
import { ReactComponent as BonfireSVG } from './bonfire.svg';
import { ReactComponent as CommentsSVG } from './comments.svg';
import { ReactComponent as ConnectSVG } from './connect.svg';
import { useNavigate } from 'react-router';
import Paper from '@mui/material/Paper';
import Container from '@mui/material/Container';
import Button from '@mui/material/Button';
import CorporateCard from './CorporateCard';
import axios from 'axios';
import useCompanyContext from '../global/CompanyContext';

interface ReactChildren {
  children: JSX.Element | JSX.Element[];
}

interface PageText {
  text: string;
}

interface RetrievedCompanies extends CompanyDetails {
  id: string;
}

function HomePage() {
  const { companies } = useCompanyContext();
  const [searchSelectedCompany, setSearchSelectedCompany] = useState<string | undefined>(
    companies?.[0]?.companyName
  );
  const [searchInput, setSearchInput] = useState<string>('');
  const navigate = useNavigate();

  const FeaturePaper = ({ text, children }: ReactChildren & PageText) => {
    return (
      <Paper
        elevation={20}
        sx={{
          borderRadius: 5,
          p: 5,
          height: '100%',
          background: '-webkit-linear-gradient(bottom left, rgba(226, 98, 51, 1), #E65943)',
        }}>
        <Typography
          sx={{
            fontFamily: 'arial',
            fontWeight: 'bold',
            color: 'white',
          }}>
          {text}
        </Typography>
        <Box sx={{ mt: 5 }}>{children}</Box>
      </Paper>
    );
  };

  const TitleText = ({ text }: PageText) => {
    return (
      <Typography sx={{ color: 'white', fontWeight: 'bold', mb: 5 }} variant='h3'>
        {text}
      </Typography>
    );
  };

  const PageWrapper = ({ children }: ReactChildren) => {
    return (
      <Box
        sx={{
          background: '#14110f',
        }}>
        <Container
          maxWidth='xl'
          sx={{
            display: 'flex',
            alignItems: 'center',
            justifyContent: 'center',
            flexDirection: 'column',
            minHeight: '80vh',
          }}>
          {children}
        </Container>
      </Box>
    );
  };

  const handleNavigateToCompany = (companyName: string) => {
    navigate(`/corporates/${companies?.find((company) => company.companyName === companyName)?.id}`);
  };

  return (
    <Box>
      <Box
        alignItems='center'
        sx={{
          minHeight: '30rem',
          display: 'flex',
          backgroundImage: `url(${Image})`,
          backgroundSize: 'cover',
          alignItems: 'center',
          color: 'white',
        }}>
        <Container maxWidth='xl'>
          <Typography
            sx={{
              fontWeight: 'bold',
            }}
            variant='h1'>
            Campfire
          </Typography>
          <Typography
            sx={{
              fontWeight: 'bold',
            }}
            variant='h5'>
            Come share your valuable internship experience.
          </Typography>
          <Box sx={{ display: 'flex', justifyContent: 'center', alignItems: 'end' }}>
            <Autocomplete
              id='search-for-companies'
              options={companies?.map((option) => option.companyName)}
              value={searchSelectedCompany}
              onChange={(_, value) => {
                setSearchSelectedCompany(value);
              }}
              onInputChange={(_, value) => {
                setSearchInput(value);
              }}
              disableClearable
              sx={{
                mt: 2,
                mr: 2,
                color: 'white',
                width: '20rem',
                '& label.Mui-focused': {
                  color: 'white',
                },
                '& .MuiInputLabel-root': {
                  color: 'white',
                },
                backgroundColor: 'rgba(0,0,0,0.3)',
                borderRadius: 2,
                '& .MuiInputBase-root': {
                  color: 'white', // Custom text color
                },
              }}
              renderInput={(params) => (
                <CustomTextField
                  {...params}
                  label='Search For Companies'
                  InputProps={{
                    ...params.InputProps,
                    type: 'search',
                  }}
                />
              )}
            />
            <Button
              variant={'contained'}
              sx={{ borderRadius: 2, backgroundColor: 'black', height: 'auto' }}
              onClick={() => {
                searchSelectedCompany && handleNavigateToCompany(searchSelectedCompany);
              }}>
              <Typography fontWeight={'bold'}>Go</Typography>
            </Button>
          </Box>
        </Container>
      </Box>
      <PageWrapper>
        <Box
          sx={{
            minHeight: '50rem',
            my: 5,
            display: 'flex',
            justifyContent: 'center',
            flexDirection: 'column',
          }}>
          <TitleText text={'What is Campfire?'} />
          <Grid container sx={{ minHeight: 'fit-content' }}>
            <Grid item sm={6} md={4} sx={{ p: 3, width: '100%' }}>
              <FeaturePaper
                text={
                  "You've reached an end of an important journey. Stop by and recount your valuable experience."
                }>
                <BonfireSVG style={{ width: '100%', maxWidth: '5rem', height: 'auto' }} />
              </FeaturePaper>
            </Grid>
            <Grid item sm={6} md={4} sx={{ padding: 3, width: '100%' }}>
              <FeaturePaper
                text={
                  "Curious about someone's experience? Interact on our platform by leaving a comment."
                }>
                <CommentsSVG style={{ width: '100%', maxWidth: '5rem', height: 'auto' }} />
              </FeaturePaper>
            </Grid>
            <Grid item md={4} sx={{ p: 3, width: '100%' }}>
              <FeaturePaper
                text={
                  'Connect to other users to ask deeper, or other burning questions privately.'
                }>
                <ConnectSVG style={{ width: '100%', maxWidth: '5rem', height: 'auto' }} />
              </FeaturePaper>
            </Grid>
          </Grid>
        </Box>
        <Box textAlign={'start'} sx={{ minHeight: '30rem', width: '100%', mb: 5 }}>
          <TitleText text={'Our Companies'} />
          {companies?.map((company) => (
            <CorporateCard
              key={company.id}
              name={company.companyName}
              info={company.companyInfo}
              id={company.id}
            />
          ))}
        </Box>
      </PageWrapper>
    </Box>
  );
}

export default HomePage;
