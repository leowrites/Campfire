import Grid from '@mui/material/Grid';
import Box from '@mui/material/Box';
import Typography from '@mui/material/Typography';
import TextField from '@mui/material/TextField';
import Image from './bannerImage.png';
import { ReactComponent as BonfireSVG } from './bonfire.svg';
import { ReactComponent as CommentsSVG } from './comments.svg';
import { ReactComponent as ConnectSVG } from './connect.svg';

function HomePage() {
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
          <TextField
            id='standard-basic'
            label='Search For Companies'
            variant='filled'
            sx={{
              width: '30vw',
              mt: 2,
              backgroundColor: 'white',
              borderRadius: 4,
            }}
          />
        </Box>
      </Box>
      <Grid container sx={{ backgroundColor: '#fcd4bd', height: '60vh' }}>
        <Grid item xs={4} sx={{ p: 3 }}>
          <Typography
            sx={{
              fontFamily: 'arial',
              fontWeight: 'bold',
              color: '#8c1c0d',
            }}>
            You've reached an end of an important journey. Stop by and recount your valuable
            experience.
          </Typography>
          <BonfireSVG style={{ width: '18vw', height: '18vw' }} />
        </Grid>
        <Grid item xs={4} sx={{ padding: 3 }}>
          <Typography
            sx={{
              fontFamily: 'arial',
              fontWeight: 'bold',
              color: '#8c1c0d',
            }}>
            Curious about someone's experience? Interact on our platform by leaving a comment.
          </Typography>
          <CommentsSVG style={{ width: '18vw', height: '18vw' }} />
        </Grid>
        <Grid item xs={4} sx={{ p: 3 }}>
          <Typography
            sx={{
              fontFamily: 'arial',
              fontWeight: 'bold',
              color: '#8c1c0d',
            }}>
            Connect to other users to ask deeper, or other burning questions privately.
          </Typography>
          <ConnectSVG style={{ width: '18vw', height: '18vw' }} />
        </Grid>
      </Grid>
    </>
  );
}

export default HomePage;
