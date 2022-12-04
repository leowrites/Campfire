import Grid from '@mui/material/Grid';
import Box from '@mui/material/Box';
import Typography from '@mui/material/Typography';
import Image from './bannerImage.png';

function HomePage() {
  return (
    <>
      <Box
        container
        justifyContent='center'
        alignItems='center'
        sx={{
          height: '50rem',
          width: '100vw',
          backgroundImage: `url(${Image})`,
          backgroundSize: 'cover',
          display: 'flex',
          alignItems: 'center',
        }}>
        <Box>
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
      </Box>
      <Grid container sx={{ mt: 4 }}>
        <Grid sm={4}>
          <Box>
            <Typography>Feature 1</Typography>
          </Box>
        </Grid>
        <Grid sm={4}>
          <Box>
            <Typography>Feature 2</Typography>
          </Box>
        </Grid>
        <Grid sm={4}>
          <Box>
            <Typography>Feature 3</Typography>
          </Box>
        </Grid>
      </Grid>
    </>
  );
}

export default HomePage;
