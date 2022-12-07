import { useState } from 'react';
import Grid from '@mui/material/Grid';
import Box from '@mui/material/Box';
import Typography from '@mui/material/Typography';
import TextField from '@mui/material/TextField';
import CustomTextField from '../Component/CustomTextfield';
import Image from './bannerImage.png';
import { ReactComponent as BonfireSVG } from './bonfire.svg';
import { ReactComponent as CommentsSVG } from './comments.svg';
import { ReactComponent as ConnectSVG } from './connect.svg';
import Paper from '@mui/material/Paper';
import Container from '@mui/material/Container';
import Button from '@mui/material/Button';
import CorporateCard from './CorporateCard';
import { useEffect } from 'react';
import axios from 'axios';

function HomePage() {
  const [corporates, setCorporates] = useState([]);

  useEffect(() => {
    axios.get('/corporates').then((res) => setCorporates(res.data));
  }, []);

  const FeaturePaper = ({ text, children }) => {
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

  const TitleText = ({ text }) => {
    return (
      <Typography sx={{ color: 'white', fontWeight: 'bold', mb: 5 }} variant='h3'>
        {text}
      </Typography>
    );
  };

  const PageWrapper = ({ children }) => {
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
            display: 'flex',
          }}>
          {children}
        </Container>
      </Box>
    );
  };

  return (
    <Box sx={{ pt: 10 }}>
      <Box
        alignItems='center'
        sx={{
          minHeight: '40rem',
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
            Share your valuable internship experience.
          </Typography>
          <Box sx={{ display: 'flex', justifyContent: 'center', alignItems: 'end' }}>
            <CustomTextField
              sx={{
                mt: 2,
                mr: 2,
                color: 'white',
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
              id='standard-basic'
              label='Search For Companies'
            />
            <Button
              variant={'contained'}
              sx={{ borderRadius: 2, backgroundColor: 'black', height: 'auto' }}>
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
          {corporates.map((corporate) => (
            <CorporateCard
              key={corporate.id}
              name={corporate.companyName}
              info={corporate.companyInfo}
              id={corporate.id}
            />
          ))}
        </Box>
      </PageWrapper>
    </Box>
  );
}

export default HomePage;
