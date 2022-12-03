import Box from '@mui/material/Box';
import Typography from '@mui/material/Typography';
import { Link } from 'react-router-dom';

export default function Footer() {
  // a footer that spans the entire width of the page
  // has a prompt to allow company representatives to add their company
  // create a beautiful footer with some nice colors
  return (
    <Box
      sx={{
        mt: 2,
        p: 2,
        position: 'fixed',
        left: 0,
        bottom: 0,
        right: 0,
        backgroundColor: '#eff1f3',
        minHeight: '5rem',
      }}>
      <Typography sx={{ display: 'flex' }} variant='h7'>
        Company representitive?
        <Link to={`internships`} style={{ color: 'gray', textDecoration: 'none' }}>
          Add your company
        </Link>
      </Typography>
      <Typography sx={{ display: 'flex' }} variant='h7'>
        My Intern Days 2022
      </Typography>
    </Box>
  );
}
