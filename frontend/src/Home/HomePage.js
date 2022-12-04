
import Grid from '@mui/material/Grid';

import Typography from '@mui/material/Typography';
import TextField from "@mui/material/TextField";
import Image from './bannerImage.png';
import {ReactComponent as BonfireSVG} from './bonfire.svg'
import {ReactComponent as CommentsSVG} from './comments.svg'
import {ReactComponent as ConnectSVG} from './connect.svg'


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
        <Grid container
              sx={{backgroundColor:'#fcd4bd',
                  height: '60vh',
                  width: '100vw'}}>
            <Grid item xs={4} alignItems="center" justifyContent="center" sx={{padding: '2%'}}>
                <Typography sx={{
                    fontFamily: 'arial',
                    fontWeight: 'bold',
                    color: '#8c1c0d',
                }}>
                    You've reached an end of an important journey. Stop by and recount your valuable experience.
                </Typography>
                <BonfireSVG style={{width: '18vw', height:'18vw'}}/>
            </Grid>
            <Grid item xs={4} alignItems="center" justifyContent="center" sx={{padding: '2%'}}>
                <Typography sx={{
                    fontFamily: 'arial',
                    fontWeight: 'bold',
                    color: '#8c1c0d',
                }}>
                    Curious about someone's experience? Interact on our platform by leaving a comment.
                </Typography>
                <CommentsSVG style={{width: '18vw', height:'18vw'}}/>
            </Grid>
            <Grid item xs={4} alignItems="center" justifyContent="center" sx={{padding: '2%'}}>
                <Typography sx={{
                    fontFamily: 'arial',
                    fontWeight: 'bold',
                    color: '#8c1c0d',
                }}>
                    Connect to other users to ask deeper, or other burning questions privately.
                </Typography>
                <ConnectSVG style={{width: '18vw', height:'18vw'}}/>
            </Grid>
        </Grid>
    </>

  );
}

export default HomePage;
