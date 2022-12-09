import Box from '@mui/material/Box';
import Typography from '@mui/material/Typography';
import { Link } from 'react-router-dom';
import useAuthContext from './AuthContext';

export default function Footer() {
  const { principal } = useAuthContext();

  // a footer that spans the entire width of the page
  // has a prompt to allow company representatives to add their company
  // create a beautiful footer with some nice colors
  return (
    <Box>
      <Box
        sx={{
          p: 2,
          left: 0,
          bottom: 0,
          right: 0,
          backgroundColor: '#14110f',
          minHeight: '5rem',
        }}>
        <Typography sx={{ display: 'flex', color: 'white' }} variant='h7'>
          Company Representative?
          <Link
            to={principal ? `/corporates/create` : '/login'}
            style={{ color: 'gray', textDecoration: 'none' }}>
            Add your company
          </Link>
        </Typography>
        <Typography sx={{ display: 'flex', color: 'white' }} variant='h7'>
          Campfire © 2022
        </Typography>
      </Box>
    </Box>
  );
}
