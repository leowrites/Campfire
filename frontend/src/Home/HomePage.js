import Container from 'react-bootstrap/Container';
import { useEffect, useState } from 'react';
import InternshipPaper from './InternshipPaper';
import Grid from '@mui/material/Grid';
import Box from '@mui/material/Box';
import ConnectPanel from './ConnectPanel';

function HomePage() {
  const [internships, setInternships] = useState([]);

  useEffect(() => {
    const internships = [];
    for (let i = 0; i < 5; i++) {
      internships.push({
        name: 'Apple',
        info: 'Pls give me an internship 🙏 ',
      });
    }
    setInternships(internships);
  }, []);

  return (
    <>
      <Container>
        <Box sx={{ pt: 3 }}>
          <Grid container spacing={4}>
            <Grid item xs={8}>
              {internships.map((v, i) => (
                <InternshipPaper key={i} title={v.name} info={v.info} />
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
