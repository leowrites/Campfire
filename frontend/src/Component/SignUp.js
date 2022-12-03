import React, { useState } from 'react';
import Typography from '@mui/material/Typography';
import Container from '@mui/material/Container';
import TextField from '@mui/material/TextField';
import Stack from '@mui/material/Stack';
import Button from '@mui/material/Button';
import axios from 'axios'
import useAuthContext from "../AuthContext";
import { useNavigate } from 'react-router';

function SignUp() {
    const [formData, setFormData] = useState({
        firstName: '',
        lastName: '',
        username: '',
        email: '',
        password: '',
        confirmPassword: ''
    });

    const navigate = useNavigate()
    const authContext = useAuthContext()

    const [usernameError, setUsernameError] = useState('');
    const [emailError, setEmailError] = useState('');
    const [passwordError, setPasswordError] = useState('');
    const [confirmPasswordError, setConfirmPasswordError] = useState('');

    const onFirstNameChange = (e) => {
        setFormData({...formData, firstName: e.target.value})
    }

    const onLastNameChange = (e) => {
        setFormData({...formData, lastName: e.target.value})
    }

    const onUserNameChange = (e) => {
        setFormData({...formData, username: e.target.value})
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
        fetch('/signup', {
            method: 'POST',
            credentials: 'include',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(formData),
        }).then((response) => response.json())
            .then((data) => {
                console.log(data)
                //if there are error messages:
                if(data.errorMessages.length > 0) {

                    //iterate through each error message to display
                    data.errorMessages.forEach(fieldError => {
                        if(fieldError.field === 'email'){
                            setEmailError(fieldError.message);
                        }
                        if(fieldError.field === 'password'){
                            setPasswordError(fieldError.message);
                        }
                        if(fieldError.field === 'username'){
                            setUsernameError(fieldError.message);
                        }
                        if(fieldError.field === 'confirmPassword'){
                            setConfirmPasswordError(fieldError.message);
                        }
                    });
                } else {
                    // No errors, redirect user to login page

                    //create form for login
                    const fd = new FormData()
                    fd.append('username', formData.username)
                    fd.append('password', formData.password)
                    fd.append('remember-me', true)

                    //post to /login
                    axios.post("/login", fd, {
                        headers: { "Content-Type": "multipart/form-data" },
                    })
                        .then(data => {
                            return {
                                principal: data.data.principal,
                                username: data.data.principal.username
                            }
                        })
                        .then(data => {
                            authContext.getUserInfo(data)
                            navigate('/')
                        })
                }
            })
            .catch((err) => err);
    };


    return (
        <Container>
            <Typography variant='h4'>SIGN UP</Typography>
            <Stack spacing={2}>
                <TextField label='First Name' onChange={onFirstNameChange}/>
                <TextField label='Last Name' onChange={onLastNameChange}/>
                <TextField label='Username' onChange={onUserNameChange}/>
                {
                    usernameError ? <span style={{ color: 'red', fontSize: '12px'}}>{usernameError}</span> : ''
                }
                <TextField label='Email' onChange={onEmailChange}/>
                {
                    emailError ? <span style={{ color: 'red', fontSize: '12px'}}>{emailError}</span> : ''
                }
                <TextField label='Password' type="password" onChange={onPasswordChange}/>
                {
                    passwordError ? <span style={{ color: 'red', fontSize: '12px'}}>{passwordError}</span> : ''
                }
                <TextField label='Confirm Password' type="password" onChange={onConfirmPasswordChange}/>
                {
                    confirmPasswordError ? <span style={{ color: 'red', fontSize: '12px'}}>{confirmPasswordError}</span> : ''
                }
                <Button variant='contained' onClick={signup}>Sign up</Button>
            </Stack>
        </Container>
    );
}

export default SignUp;