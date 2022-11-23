import * as React from 'react';
import AppBar from '@mui/material/AppBar';
import Stack from '@mui/material/Stack';
import Toolbar from '@mui/material/Toolbar';
import Typography from '@mui/material/Typography';
import Button from '@mui/material/Button';
import { ThemeProvider, createTheme } from '@mui/material/styles';
import { Box } from '@mui/system';
import { useNavigate } from 'react-router';
import { Link } from 'react-router-dom';
import useAuthContext from '../AuthContext';
import axios from 'axios';

const darkTheme = createTheme({
  palette: {
    mode: 'dark',
    primary: {
      main: '#1976d2',
    },
  },
});

export default function Navbar() {
  const navigate = useNavigate();
  const authContext = useAuthContext();
  const principal = authContext.principal;

  const handleLogout = () => {
    authContext.setPrincipal()
    axios.post('logout')
  }

  return (
    <Stack spacing={2} sx={{ flexGrow: 1 }}>
      <ThemeProvider theme={darkTheme}>
        <AppBar position='static' color='primary'>
          <Toolbar>
            <Link to='/' style={{ textDecoration: 'none' }}>
              <Typography variant='h6' sx={{ color: 'white' }}>
                Rate my Intern
              </Typography>
            </Link>
            <Box sx={{ ml: 'auto' }}>
              {principal ? (
                <>
                  <Typography sx={{ display: 'inline' }}>{principal.username}</Typography>
                  <Button
                    sx={{ color: 'white', m: 2 }}
                    onClick={handleLogout}>
                    Logout
                  </Button>
                </>
              ) : (
                <>
                  <Button sx={{ color: 'white' }} onClick={() => navigate('login')}>
                    Login
                  </Button>
                  <Button sx={{ color: 'white' }} onClick={() => navigate('signup')}>
                    Sign in
                  </Button>
                </>
              )}
            </Box>
          </Toolbar>
        </AppBar>
      </ThemeProvider>
    </Stack>
  );
}
