import * as React from 'react';
import AppBar from '@mui/material/AppBar';
import Stack from '@mui/material/Stack';
import Toolbar from '@mui/material/Toolbar';
import Typography from '@mui/material/Typography';
import Button from '@mui/material/Button';
import TextField from '@mui/material/TextField';
import { Box } from '@mui/system';
import { useNavigate } from 'react-router';
import { Link } from 'react-router-dom';
import useAuthContext from '../AuthContext';
import axios from 'axios';
import { ReactComponent as Logo } from './campfire.svg';
import './fonts.css';
import { ReactComponent as ConnectIcon } from './connectIcon.svg';
import { useState } from 'react';
import ConnectPanel from '../Home/ConnectPanel';
import { Drawer } from '@mui/material';

export default function Navbar() {
  const navigate = useNavigate();
  const authContext = useAuthContext();
  const principal = authContext.principal;

  const handleLogout = () => {
    authContext.setPrincipal();
    axios.post('logout');
  };

  const [open, setOpen] = useState(false);

  return (
    <>
      <AppBar
        position='static'
        style={{ background: '#050f04', minHeight: '4rem', maxHeight: 'fit-content' }}
        elevation={0}>
        <Toolbar>
          <Link to='/' style={{ textDecoration: 'none' }}>
            <Box sx={{ display: 'flex', alignItems: 'center' }}>
              <Logo style={{ height: 60, padding: 12, ml: 2 }} />
              <Typography
                variant='h5'
                sx={{
                  color: '#F6F2F2',
                  fontWeight: 'bold',
                }}>
                CAMP
              </Typography>
              <Typography
                variant='h5'
                sx={{
                  color: '#ff5634',
                  fontWeight: 'bold',
                  ml: 1,
                }}>
                FIRE
              </Typography>
            </Box>
          </Link>

          <TextField
            sx={{ ml: 2 }}
            id='outlined-basic'
            label='Search'
            variant='outlined'
            size='small'
          />

          <Box sx={{ ml: 'auto' }}>
            {principal ? (
              <Box sx={{ display: 'inline-flex', alignItems: 'center' }}>
                <Button onClick={() => setOpen(true)}>
                  <ConnectIcon style={{ height: 'auto', width: 60 }}></ConnectIcon>
                </Button>
                <Typography sx={{ color: 'white', fontWeight: 'bold' }}>
                  {principal.username}
                </Typography>
                <Button onClick={handleLogout}>
                  <Typography sx={{ fontWeight: 'bold', color: 'white', textTransform: 'none' }}>
                    Logout
                  </Typography>
                </Button>
              </Box>
            ) : (
              <>
                <Button onClick={() => navigate('login')}>
                  <Typography sx={{ color: '#F6F2F2', fontWeight: 'bold' }} variant='h6'>
                    Login
                  </Typography>
                </Button>
                <Button onClick={() => navigate('signup')}>
                  <Typography sx={{ color: '#ff5634', fontWeight: 'bold' }} variant='h6'>
                    Sign up
                  </Typography>
                </Button>
              </>
            )}
          </Box>
        </Toolbar>
      </AppBar>
      <Drawer open={open} anchor={'right'} onClose={() => setOpen(false)}>
        <ConnectPanel />
      </Drawer>
    </>
  );
}
