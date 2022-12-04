import { useEffect, useState } from 'react';
import Grid from '@mui/material/Grid';
import Box from '@mui/material/Box';
import axios from 'axios';
import CorporateCard from './CorporateCard';
import Typography from '@mui/material/Typography';




function HomePage() {

      // get all companies
      const [companies, setCompanies] = useState([]);
      useEffect(() => {
        axios.get('/corporates').then((data) => {
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
            <CorporateCard
              key={company.id}
              name={company.companyName}
              info={company.companyInfo}
              id={company.id}
            />
          ))}
        </Grid>
        {/*<Grid item xs={3}>*/}
        {/*  /!*<ConnectPanel />*!/*/}
        {/*</Grid>*/}
      </Grid>
    </Box>
  );
}

export default HomePage;
