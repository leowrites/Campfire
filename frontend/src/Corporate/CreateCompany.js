import React, { useState } from 'react';
import Typography from '@mui/material/Typography';
import Container from '@mui/material/Container';
import Button from '@mui/material/Button';
import CustomTextField from '../Component/CustomTextfield';
import useAuthContext from '../AuthContext';
import { useNavigate } from 'react-router';
import useTheme from '@mui/material/styles/useTheme';
import useGlobal from '../GlobalContext';
import Stack from '@mui/material/Stack';

function CreateCompany() {
  const theme = useTheme();
  const { setMsg, showMsg } = useGlobal()
  const { principal } = useAuthContext();

  const [formData, setFormData] = useState({
    userId: principal?.username,
    companyName: '',
    companyInfo: '',
  });

  const navigate = useNavigate();

  const [companyName, setCompanyName] = useState('');
  const [confirmInformation, setCompanyInformation] = useState('');

  const onCompanyNameChange = (e) => {
    setFormData({ ...formData, companyName: e.target.value });
  };

  const onCompanyInfoChange = (e) => {
    setFormData({ ...formData, companyInfo: e.target.value });
  };

  const create = () => {
    fetch('/corporates', {
      method: 'POST',
      credentials: 'include',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(formData),
    })
      .then((data) => {
        setMsg('Company created successfully!')
        showMsg(true)
        navigate('/')
      })
      .catch((err) => console.log(err));
  };

  return (
    <Container>
      <Stack direction={'column'} spacing={2}>
        <Typography variant='h4' sx={{ color: theme.palette.secondary.main }}>
          ADD YOUR COMPANY
        </Typography>
        <CustomTextField label='Company Name' fullWidth onChange={onCompanyNameChange} />
        <CustomTextField label='Company Info' fullWidth onChange={onCompanyInfoChange} />
        <Button variant='contained' onClick={create}>
          Create
        </Button>
      </Stack>
    </Container>
  );
}

export default CreateCompany;
