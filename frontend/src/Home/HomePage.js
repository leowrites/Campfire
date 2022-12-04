import Grid from '@mui/material/Grid';
import Box from '@mui/material/Box';
import Typography from '@mui/material/Typography';
import TextField from '@mui/material/TextField';
import Image from './bannerImage.png';
import { ReactComponent as BonfireSVG } from './bonfire.svg';
import { ReactComponent as CommentsSVG } from './comments.svg';
import { ReactComponent as ConnectSVG } from './connect.svg';
import Paper from '@mui/material/Paper';
import Container from '@mui/material/Container';
import Button from '@mui/material/Button';

function HomePage() {
  const FeaturePaper = ({ text, children }) => {
    return (
      <Paper elevation={5} sx={{ borderRadius: 5, p: 5, height: '100%', backgroundColor: 'rgba(255, 255, 255, 0.7)' }}>
        <Typography
          sx={{
            fontFamily: 'arial',
            fontWeight: 'bold',
            color: '#8c1c0d',
          }}>
          {text}
        </Typography>
        <Box sx={{ mt: 5 }}>{children}</Box>
      </Paper>
    );
  };

  const TextBlock = () => {
    return (
      <Box sx={{ display: 'inline-block' }}>
        <Typography
          sx={{
            fontFamily: 'arial',
            fontWeight: 'bold',
            color: 'white',
          }}
          variant='h1'>
          Campfire
        </Typography>
        <Typography
          sx={{
            fontFamily: 'arial',
            fontWeight: 'bold',
            color: 'white',
          }}
          variant='h5'>
          Share your valuable internship experience.
        </Typography>
      </Box>
    );
  };

  return (
    <>
      <Box
        justifyContent='center'
        alignItems='center'
        sx={{
          display: 'flex',
          height: '90vh',
          backgroundImage: `url(${Image})`,
          backgroundSize: 'cover',
        }}
        textAlign='start'>
        <Container maxWidth='lg'>
          <Box
            sx={{
              display: 'flex',
              alignItems: 'end',
              justifyContent: 'space-evenly',
            }}>
            <TextBlock />
            <Box sx={{ display: 'flex', alignItems: 'end' }}>
              <TextField
                id='standard-basic'
                label='Search For Companies'
                variant='filled'
                sx={{
                  '& .Mui-focused': {
                    color: 'black',
                  },
                  '& .MuiFilledInput-root': {
                    '&:before': { borderBottomColor: 'transparent' },
                    '&:after': { borderBottomColor: 'transparent' },
                    '&:hover fieldset': {
                      borderColor: 'yellow', // - Set the Input border when parent has :hover
                    },
                  },
                  width: '20rem',
                  mt: 2,
                  mr: 2,
                  backgroundColor: 'white',
                  borderRadius: 2,
                }}
              />
              <Button variant={'contained'} sx={{ borderRadius: 2 }}>
                Go
              </Button>
            </Box>
          </Box>
        </Container>
      </Box>
      <Box sx={{ backgroundColor: '#131b23', py: 10, minHeight: '50rem', display: 'flex' }}>
        <Container maxWidth='xl' sx={{ display: 'flex' }}>
          <Grid container sx={{ minHeight: 'fit-content' }}>
            <Grid item sm={6} md={4} sx={{ p: 3 }}>
              <FeaturePaper
                text={
                  "You've reached an end of an important journey. Stop by and recount your valuable experience."
                }>
                <BonfireSVG style={{ width: '100%', maxWidth: '10rem', height: 'auto' }} />
              </FeaturePaper>
            </Grid>
            <Grid item sm={6} md={4} sx={{ padding: 3 }}>
              <FeaturePaper
                text={
                  "Curious about someone's experience? Interact on our platform by leaving a comment."
                }>
                <CommentsSVG style={{ width: '100%', maxWidth: '10rem', height: 'auto' }} />
              </FeaturePaper>
            </Grid>
            <Grid item md={4} sx={{ p: 3 }}>
              <FeaturePaper
                text={
                  'Connect to other users to ask deeper, or other burning questions privately.'
                }>
                <ConnectSVG style={{ width: '100%', maxWidth: '10rem', height: 'auto' }} />
              </FeaturePaper>
            </Grid>
          </Grid>
        </Container>
      </Box>
    </>
  );
}

export default HomePage;
