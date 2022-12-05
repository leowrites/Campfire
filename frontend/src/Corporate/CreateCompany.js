import React, { useState } from 'react';
import Typography from '@mui/material/Typography';
import Container from '@mui/material/Container';
import TextField from '@mui/material/TextField';
import Stack from '@mui/material/Stack';
import Button from '@mui/material/Button';
import axios from 'axios'
import useAuthContext from "../AuthContext";
import { useNavigate } from 'react-router';

function CreateCompany() {
    const {principal} = useAuthContext();

    const [formData, setFormData] = useState({
        userId: principal.username,
        companyName: '',
        companyInfo: ''
    });

    const navigate = useNavigate()


    const [companyName, setCompanyName] = useState('');
    const [confirmInformation, setCompanyInformation] = useState('');

    const onCompanyNameChange = (e) => {
        setFormData({...formData, companyName: e.target.value})
    }

    const onCompanyInfoChange = (e) => {
        setFormData({...formData, companyInfo: e.target.value})
    }


    const create = () => {
        fetch('/corporates', {
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
                navigate("/")
            })
            .catch((err) => err);
    };


    return (
        <Container>
            <Typography variant='h4'>ADD YOUR COMPANY</Typography>
            <Stack spacing={2}>
                <TextField label='Company Name' onChange={onCompanyNameChange}/>
                <TextField label='Company Info' onChange={onCompanyInfoChange}/>
                <Button variant='contained' onClick={create}>Create</Button>
            </Stack>
        </Container>
    );
}

export default CreateCompany;