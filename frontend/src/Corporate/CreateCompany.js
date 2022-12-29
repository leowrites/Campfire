import React, { useState } from 'react';
import Typography from '@mui/material/Typography';
import Container from '@mui/material/Container';
import Button from '@mui/material/Button';
import CustomTextField from '../Component/CustomTextfield';
import { useNavigate } from 'react-router';
import useTheme from '@mui/material/styles/useTheme';
import useGlobal from '../GlobalContext';
import Stack from '@mui/material/Stack';
import axios from 'axios';

function CreateCompany() {
  const theme = useTheme();
  const { setMsg, setShowMsg, setStatus } = useGlobal();
  const [clicked, setClicked] = useState(false)

  const [formData, setFormData] = useState({
    companyName: '',
    companyInfo: '',
  });

  const navigate = useNavigate();

  const onCompanyNameChange = (e) => {
    setFormData({ ...formData, companyName: e.target.value });
  };

  const onCompanyInfoChange = (e) => {
    setFormData({ ...formData, companyInfo: e.target.value });
  };

  const create = () => {
    setClicked(true)
    setTimeout(() => {
      setClicked(false)
    }, 3000)
    axios
      .post('/corporates', formData, {
        withCredentials: true,
        headers: { 'Content-Type': 'application/json' },
      })
      .then((data) => {
        setMsg('Company created successfully!');
        setShowMsg(true);
        navigate('/');
      })
      .catch((err) => {
        setMsg(err.response.data.message);
        setShowMsg(true);
        setStatus('error')
      });
  };

  return (
    <Container>
      <Stack direction={'column'} spacing={2}>
        <Typography variant='h4' sx={{ color: theme.palette.secondary.main }}>
          ADD YOUR COMPANY
        </Typography>
        <CustomTextField label='Company Name' fullWidth onChange={onCompanyNameChange} />
        <CustomTextField label='Company Info' fullWidth onChange={onCompanyInfoChange} />
        <Button variant='contained' onClick={create} disabled={clicked}>
          Create
        </Button>
      </Stack>
    </Container>
  );
}

export default CreateCompany;
