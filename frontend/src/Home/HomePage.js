
import Grid from '@mui/material/Grid';

import Typography from '@mui/material/Typography';
import Image from './bannerImage.png';



function HomePage() {

  return (
    <>
      <Grid
          container
          direction="column"
          justifyContent="center"
          alignItems="center"
          sx={{
              height: '90vh',
              width: '100vw',
              ml: '-10vw',
              backgroundImage: `url(${Image})`,
              backgroundSize: 'cover'
          }}
      >
          <Grid>

          <Typography
              sx={{
                  fontFamily: 'arial',
                  fontWeight: 'bold',
                  color: 'white',
              }}
              variant="h1"
          >
              Campfire
          </Typography>
          <Typography
              sx={{
                  fontFamily: 'arial',
                  fontWeight: 'bold',
                  color: 'white',
              }}
              variant="h5"
          >
              Share your valuable internship experience.
          </Typography>
          <br></br>
          </Grid>

      </Grid>
        <Grid>

        </Grid>
    </>

  );
}

export default HomePage;
