import React, { useState } from 'react';
import Typography from '@mui/material/Typography';
import Container from '@mui/material/Container';
import TextField from '@mui/material/TextField';
import Stack from '@mui/material/Stack';
import axios from 'axios'
import Button from '@mui/material/Button';

function Login() {
    const [formData, setFormData] = useState({
        username: '',
        password: ''
    });

    const [usernameError, setUsernameError] = useState('');
    const [passwordError, setPasswordError] = useState('');



    const onUserNameChange = (e) => {
        setFormData({...formData, username: e.target.value})
    }

    const onPasswordChange = (e) => {
        setFormData({...formData, password: e.target.value})
    }

    const login = async (username, password) => {
        const response = await axios.post("http://localhost:8080/login", {
            password: formData.username,
            username: formData.password
        })
    };


    return (
        <Container>
            <Typography variant='h4'>Login</Typography>
            <Stack spacing={2}>
                <TextField label='Username' onChange={onUserNameChange}/>
                {
                    usernameError ? <span style={{ color: 'red', fontSize: '12px'}}>{usernameError}</span> : ''
                }
                <TextField label='Password' type="password" onChange={onPasswordChange}/>
                {
                    passwordError ? <span style={{ color: 'red', fontSize: '12px'}}>{passwordError}</span> : ''
                }
                <Button variant='contained' onClick={login}>Login</Button>
            </Stack>
        </Container>
    );
}

export default Login;