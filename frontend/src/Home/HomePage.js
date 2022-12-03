import Container from 'react-bootstrap/Container';
import { useEffect, useState } from 'react';
import Grid from '@mui/material/Grid';
import Box from '@mui/material/Box';
import ConnectPanel from './ConnectPanel';
import axios from 'axios';
import CorporateCard from './CorporateCard';
import Typography from '@mui/material/Typography';

function HomePage() {
  // get all companies
  const [companies, setCompanies] = useState([]);
  useEffect(() => {
    axios.get('/corporates').then((data) => {
      console.log(data);
      setCompanies(data.data);
    });
  }, []);

  return (
    <Box sx={{ my: 2 }} textAlign='start'>
      <Typography variant='h4' sx={{ mb: 2 }}>
        Companies
      </Typography>
      <Grid container spacing={4}>
        <Grid item xs={9}>
          {companies?.map((company) => (
            <CorporateCard name={company.name} info={company.info} id={company.id} />
          ))}
          <CorporateCard name={'Apple'} info={"Who doesn't like Apple?"} id={1} />
        </Grid>
        <Grid item xs={3}>
          <ConnectPanel />
        </Grid>
      </Grid>
    </Box>
  );
}

export default HomePage;
