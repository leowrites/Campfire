import * as React from 'react';
import AppBar from '@mui/material/AppBar';
import Stack from '@mui/material/Stack';
import Toolbar from '@mui/material/Toolbar';
import Typography from '@mui/material/Typography';
import Button from '@mui/material/Button';
import TextField from "@mui/material/TextField";
import { Box } from '@mui/system';
import { useNavigate } from 'react-router';
import { Link } from 'react-router-dom';
import useAuthContext from '../AuthContext';
import axios from 'axios';
import { ReactComponent as Logo } from './campfire.svg';
import './fonts.css';
import {ReactComponent as ConnectIcon} from "./connectIcon.svg";
import {useState} from "react";
import ConnectPanel from "../Home/ConnectPanel";
import {Drawer} from "@mui/material";

export default function Navbar() {
  const navigate = useNavigate();
  const authContext = useAuthContext();
  const principal = authContext.principal;

  const handleLogout = () => {
    authContext.setPrincipal();
    axios.post('logout');
  };

  const [open, setOpen] = useState('false');

  return (
    <Stack spacing={2} sx={{ flexGrow: 1 }}>
      <Drawer open={open} anchor={"right"} onClose={() => setOpen(false)}>
        <ConnectPanel />
      </Drawer>
      <AppBar position='static' style={{ background: '#050f04', height: "30%" }} elevation={0}>
        <Toolbar>
          <Link to='/' style={{ textDecoration: 'none', display:'flex' }}>
            <Logo style={{height: 80, padding: 10, marginLeft: '10%'}}></Logo>
            <div style={ {display: 'inline-flex', alignItems: 'center', marginLeft: '10%', width: 300, padding: 0}}>
              <Typography variant='h4' sx={{ color: '#F6F2F2', fontFamily: 'ExtraBold', paddingTop: '3%' }}>
                CAMP
              </Typography>
              <Typography variant='h4' sx={{ color: '#ff5634', fontFamily: 'ExtraBold', marginLeft: '2%', paddingTop: '3%'}}>
                F I R E
              </Typography>
            </div>

          </Link>

          <TextField id="outlined-basic" label="Search" variant="outlined"  />

          <Box sx={{ ml: 'auto' }}>
            {principal ? (
              <div style={{display: 'inline-flex', alignItems: 'center'}}>
                <Button onClick={() => setOpen(true)}>
                  <ConnectIcon style={{height: 70, width: 70, marginLeft: '10%'}}></ConnectIcon>
                </Button>

                <Typography sx={{ display: 'inline' }}>{principal.username}</Typography>

                <Button sx={{ color: 'white', m: 2 }} onClick={handleLogout}>
                  Logout
                </Button>
              </div>
            ) : (
              <>
                <Button sx={{ color: '#F6F2F2', fontFamily: 'bold', fontSize: 20}} onClick={() => navigate('login')}>
                  Login
                </Button>
                <Button sx={{ color: '#ff5634', fontFamily: 'ExtraBold', fontSize: 20 }} onClick={() => navigate('signup')}>
                  Sign up
                </Button>
              </>
            )}
          </Box>
        </Toolbar>
      </AppBar>
    </Stack>
  );
}
