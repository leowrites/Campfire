import React, { useState } from 'react';
import Typography from '@mui/material/Typography';
import Container from '@mui/material/Container';
import CustomTextField from './CustomTextfield';
import Stack from '@mui/material/Stack';
import axios from 'axios';
import Button from '@mui/material/Button';
import { useNavigate } from 'react-router';
import useAuthContext from '../AuthContext';
import Grid from '@mui/material/Grid';
import LoginImg from './loginImg.jpg';
import Box from '@mui/material/Box';

function Login() {
  const [formData, setFormData] = useState({
    username: '',
    password: '',
  });
  const navigate = useNavigate();
  const [error, setError] = useState('');
  const authContext = useAuthContext();

  const onUserNameChange = (e) => {
    setFormData({ ...formData, username: e.target.value });
  };

  const onPasswordChange = (e) => {
    setFormData({ ...formData, password: e.target.value });
  };

  const login = () => {
    const fd = new FormData();
    fd.append('username', formData.username);
    fd.append('password', formData.password);
    fd.append('remember-me', true);
    axios
      .post('/login', fd, {
        headers: { 'Content-Type': 'multipart/form-data' },
      })
      .then((data) => {
        return {
          principal: data.data.principal,
          username: data.data.principal.username,
        };
      })
      .then((data) => {
        authContext.getUserInfo(data);
        navigate('/');
      })
      .catch((err) => {
        setError('Username and password does not match');
        console.log(err);
      });
  };

  return (
    <Grid container sx={{ mt: 2 }}>
      <Grid item xs={4}>
        <Container sx={{ textAlign: 'start', mt: 6 }}>
          <Box sx={{ mx: 2 }}>
            <Typography variant='h4' sx={{ color: 'white', my: 2 }}>
              Login
            </Typography>
            <Stack spacing={2}>
              <CustomTextField label='Username' onChange={onUserNameChange} />
              <CustomTextField label='Password' type='password' onChange={onPasswordChange} />
              {error && <Typography sx={{ color: 'red' }}>{error}</Typography>}
              <Button variant='contained' onClick={login}>
                Login
              </Button>
            </Stack>
          </Box>
        </Container>
      </Grid>
      <Grid item xs={8}>
        <Box
          sx={{
            backgroundImage: `url(${LoginImg})`,
            height: '100vh',
            backgroundSize: 'cover',
            backgroundPosition: 'center',
          }}
        />
      </Grid>
    </Grid>
  );
}

export default Login;
