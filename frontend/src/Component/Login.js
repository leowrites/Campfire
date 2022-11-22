import React, { useState } from 'react';
import Typography from '@mui/material/Typography';
import Container from '@mui/material/Container';
import TextField from '@mui/material/TextField';
import Stack from '@mui/material/Stack';
import axios from 'axios'
import Button from '@mui/material/Button';
import { useNavigate } from 'react-router';
import useAuthContext from '../AuthContext'

function Login() {
    const [formData, setFormData] = useState({
        username: '',
        password: ''
    });
    const navigate = useNavigate()
    const [error, setError] = useState('')
    const authContext = useAuthContext()

    const onUserNameChange = (e) => {
        setFormData({...formData, username: e.target.value})
    }

    const onPasswordChange = (e) => {
        setFormData({...formData, password: e.target.value})
    }

    const login = () => {
        const fd = new FormData()
        fd.append('username', formData.username)
        fd.append('password', formData.password)
        fd.append('remember-me', true)
        axios.post("/login", fd, {
            headers: { "Content-Type": "multipart/form-data" },
        })
            .then(data => {
                authContext.setPrincipal(data.data.principal)
                navigate('/')
            })
            .catch((err) => {
                setError('Username and password does not match')
                console.log(err)
            })
    };


    return (
        <Container>
            <Typography variant='h4'>Login</Typography>
            <Stack spacing={2}>
                <TextField label='Username' onChange={onUserNameChange}/>
                <TextField label='Password' type="password" onChange={onPasswordChange}/>
                {
                    error && <Typography sx={{color: 'red'}}>{error}</Typography>
                }
                <Button variant='contained' onClick={login}>Login</Button>
            </Stack>
        </Container>
    );
}

export default Login;