
import Grid from '@mui/material/Grid';

import Typography from '@mui/material/Typography';
import TextField from "@mui/material/TextField";
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
              backgroundImage: `url(${Image})`,
              backgroundSize: 'cover'
          }}
      >


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
          <TextField id="standard-basic" label="Search For Companies" variant="filled" sx={{
              width: '30vw',
              backgroundColor: 'white',
              borderRadius: 4
          }} />

      </Grid>
        <Grid container sx={{backgroundColor:'blue', height: '30vh', width: '100vw'}}>

        </Grid>
    </>

  );
}

export default HomePage;
