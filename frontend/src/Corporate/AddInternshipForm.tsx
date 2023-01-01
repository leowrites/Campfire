import { useState } from 'react';
import Typography from '@mui/material/Typography';
import Box from '@mui/material/Box';
import Button from '@mui/material/Button';
import { useParams, useNavigate } from 'react-router-dom';
import axios from 'axios';
import CustomTextField from '../Component/CustomTextfield';
import useGlobal from '../GlobalContext';
import React from 'react';

export default function AddInternshipForm() {
  // get form data
  const { corporateId } = useParams();
  const [title, setTitle] = useState('');
  const [clicked, setClicked] = useState(false);
  const navigate = useNavigate();
  const { setMsg, setShowMsg, setStatus } = useGlobal();

  // make request to backend using axios
  const handleSubmit = () => {
    setClicked(true);
    setTimeout(() => {
      setClicked(false);
    }, 3000);
    axios
      .post(`/corporates/${corporateId}/internships`, {
        jobTitle: title,
        companyID: corporateId,
      })
      .then((data) => {
        console.log(data);
        // redirect to back to corporate page
        navigate(`/corporates/${corporateId}`);
      })
      .catch((err) => {
        setMsg(err.response.data.message);
        setShowMsg(true);
        setStatus('error');
      });
  };

  return (
    <Box sx={{ my: 2, color: 'white' }}>
      <Typography variant='h4' sx={{ my: 2 }}>
        Add an Internship
      </Typography>
      <CustomTextField
        id='filled-basic'
        label='Job title'
        onChange={(e: TextInputEvent) => {
          setTitle(e.target.value);
        }}
        variant='filled'
        sx={{ width: '100%' }}
      />
      <Button
        variant='contained'
        sx={{ mt: 2 }}
        color='primary'
        onClick={handleSubmit}
        disabled={clicked}>
        Submit
      </Button>
    </Box>
  );
}
