import logo from './logo.svg';
import './App.css';
import React from "react";
import {useState} from "react";
import Typography from '@mui/material/Typography';
import Container from '@mui/material/Container';
import TextField from '@mui/material/TextField';
import Stack from '@mui/material/Stack';
import Button from '@mui/material/Button';
import { Routes, Route, Link } from "react-router-dom"


function App() {
  return (
      <div className="App">
        <SignUp></SignUp>
      </div>
  )
}

function SignUp() {
    const [formData, setFormData] = useState({
        firstName: '',
        lastName: '',
        email: '',
        password: '',
        confirmPassword: ''
    });

    const onFirstNameChange = (e) => {
        setFormData({...formData, firstName: e.target.value})
    }

    const onLastNameChange = (e) => {
        setFormData({...formData, lastName: e.target.value})
    }

    const onEmailChange = (e) => {
        setFormData({...formData, email: e.target.value})
    }

    const onPasswordChange = (e) => {
        setFormData({...formData, password: e.target.value})
    }

    const onConfirmPasswordChange = (e) => {
        setFormData({...formData, confirmPassword: e.target.value})
    }

    const signup = () => {
        fetch('/user/signup', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(formData),
        });
    };
    return (
        <Container>
            <Typography variant='h4'>SIGN UP</Typography>
            <Stack spacing={2}>
                <TextField label='First Name' onChange={onFirstNameChange}/>
                <TextField label='Last Name' onChange={onLastNameChange}/>
                <TextField label='Email' onChange={onEmailChange}/>
                <TextField label='Password' type="password" onChange={onPasswordChange}/>
                <TextField label='Confirm Password' type="password" onChange={onConfirmPasswordChange}/>
                <Button variant='contained' onClick={signup}>Sign up</Button>
            </Stack>
        </Container>
    );
}


export default App;
