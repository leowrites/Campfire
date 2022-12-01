import Container from 'react-bootstrap/Container';
import { useEffect, useState } from 'react';
import InternshipPaper from './InternshipPaper';
import ReviewCard from '../Component/ReviewCard';
import Grid from '@mui/material/Grid';
import Box from '@mui/material/Box';
import ConnectPanel from './ConnectPanel';
import axios from 'axios';

function HomePage() {
  const [internships, setInternships] = useState([]);
  const [reviews, setReviews] = useState([]);

  useEffect(() => {
    const internships = [];
    for (let i = 0; i < 1; i++) {
      internships.push({
        name: 'Apple',
        info: 'Pls give me an internship 🙏 ',
      });
    }
    setInternships(internships);
  }, []);

  useEffect(() => {
    axios.get('/reviews').then((data) => {
      // console.log(data);
      setReviews(data.data);
    });
  }, []);
  // fetch all reviews for now to see the reviews made
  return (
    <>
      <Container>
        <Box sx={{ pt: 3 }}>
          <Grid container spacing={4}>
            <Grid item xs={8}>
              {internships.map((v, i) => (
                <InternshipPaper key={i} title={v.name} info={v.info} />
              ))}
              {reviews?.map((v, i) => (
                <ReviewCard key={i} {...v} />
              ))}
            </Grid>
            <Grid item xs={4}>
              <ConnectPanel />
            </Grid>
          </Grid>
        </Box>
      </Container>
    </>
  );
}

export default HomePage;
