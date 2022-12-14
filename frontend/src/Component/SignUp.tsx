import React, { useState } from 'react';
import Typography from '@mui/material/Typography';
import Container from '@mui/material/Container';
import Stack from '@mui/material/Stack';
import Button from '@mui/material/Button';
import axios from 'axios';
import useAuthContext from '../AuthContext';
import { useNavigate } from 'react-router';
import CustomTextField from './CustomTextfield';
import Grid from '@mui/material/Grid';
import signupImg from './signupImg.jpg';
import Box from '@mui/material/Box';

interface FieldError {
  field: string;
  message: string;
}

function SignUp() {
  const [formData, setFormData] = useState({
    firstName: '',
    lastName: '',
    username: '',
    email: '',
    password: '',
    confirmPassword: '',
  });

  const navigate = useNavigate();
  const { setPrincipal } = useAuthContext();

  const [usernameError, setUsernameError] = useState('');
  const [emailError, setEmailError] = useState('');
  const [passwordError, setPasswordError] = useState('');
  const [confirmPasswordError, setConfirmPasswordError] = useState('');

  const onFirstNameChange: TextFieldOnChange = (e) => {
    setFormData({ ...formData, firstName: e.target.value });
  };

  const onLastNameChange: TextFieldOnChange = (e) => {
    setFormData({ ...formData, lastName: e.target.value });
  };

  const onUserNameChange: TextFieldOnChange = (e) => {
    setFormData({ ...formData, username: e.target.value });
  };

  const onEmailChange: TextFieldOnChange = (e) => {
    setFormData({ ...formData, email: e.target.value });
  };

  const onPasswordChange: TextFieldOnChange = (e) => {
    setFormData({ ...formData, password: e.target.value });
  };

  const onConfirmPasswordChange: TextFieldOnChange = (e) => {
    setFormData({ ...formData, confirmPassword: e.target.value });
  };

  const signup = () => {
    fetch('/signup', {
      method: 'POST',
      credentials: 'include',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(formData),
    })
      .then((response) => response.json())
      .then((data) => {
        console.log(data);
        //if there are error messages:
        if (data.errorMessages.length > 0) {
          //iterate through each error message to display
          data.errorMessages.forEach((fieldError: FieldError) => {
            if (fieldError.field === 'email') {
              setEmailError(fieldError.message);
            }
            if (fieldError.field === 'password') {
              setPasswordError(fieldError.message);
            }
            if (fieldError.field === 'username') {
              setUsernameError(fieldError.message);
            }
            if (fieldError.field === 'confirmPassword') {
              setConfirmPasswordError(fieldError.message);
            }
          });
        } else {
          // No errors, redirect user to login page

          //create form for login
          const fd = new FormData();
          fd.append('username', formData.username);
          fd.append('password', formData.password);
          fd.append('remember-me', new Boolean(true).toString());

          //post to /login
          axios
            .post<User>('/login', fd, {
              headers: { 'Content-Type': 'multipart/form-data' },
            })
            .then((response) => {
              setPrincipal(response.data);
              navigate('/');
            });
        }
      })
      .catch((err) => err);
  };

  return (
    <Grid container sx={{ mt: 2, textAlign: 'start' }}>
      <Grid item xs={8} sm={6} md={4}>
        <Container sx={{ color: 'white', mt: 6 }}>
          <Box sx={{ mx: 2 }}>
            <Typography variant='h4' sx={{ my: 2 }}>
              SIGN UP
            </Typography>
            <Stack spacing={2}>
              <CustomTextField label='First Name' onChange={onFirstNameChange} />
              <CustomTextField label='Last Name' onChange={onLastNameChange} />
              <CustomTextField label='Username' onChange={onUserNameChange} />
              {usernameError && (
                <span style={{ color: 'red', fontSize: '12px' }}>{usernameError}</span>
              )}
              <CustomTextField label='Email' onChange={onEmailChange} />
              {emailError && <span style={{ color: 'red', fontSize: '12px' }}>{emailError}</span>}
              <CustomTextField label='Password' type='password' onChange={onPasswordChange} />
              {passwordError && (
                <span style={{ color: 'red', fontSize: '12px' }}>{passwordError}</span>
              )}
              <CustomTextField
                label='Confirm Password'
                type='password'
                onChange={onConfirmPasswordChange}
              />
              {confirmPasswordError && (
                <span style={{ color: 'red', fontSize: '12px' }}>{confirmPasswordError}</span>
              )}
              <Button variant='contained' onClick={signup}>
                Sign up
              </Button>
            </Stack>
          </Box>
        </Container>
      </Grid>
      <Grid item xs={4} sm={6} md={8}>
        <Box
          sx={{
            backgroundImage: `url(${signupImg})`,
            height: '100vh',
            backgroundSize: 'cover',
            backgroundPosition: 'center',
          }}
        />
      </Grid>
    </Grid>
  );
}

export default SignUp;
