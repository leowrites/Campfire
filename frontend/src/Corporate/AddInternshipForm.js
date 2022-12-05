import { useState } from 'react';
import Typography from '@mui/material/Typography';
import Box from '@mui/material/Box';
import TextField from '@mui/material/TextField';
import Button from '@mui/material/Button';
import { useParams, useNavigate } from 'react-router-dom';
import useAuthContext from '../AuthContext';
import axios from 'axios';

export default function AddInternshipForm() {
  // get form data
  const { corporateId } = useParams();
  const [title, setTitle] = useState('');
  const navigate = useNavigate();
  const { principal } = useAuthContext()

  // make request to backend using axios
  const handleSubmit = () => {
    axios
      .post(`/corporates/${corporateId}/internships`, {
        jobTitle: title,
        companyID: corporateId,
        creatorUsername: principal.username
      })
      .then((data) => {
        console.log(data);
        // redirect to back to corporate page
        navigate(`/corporates/${corporateId}`);
      });
  };

  return (
    <Box sx={{ my: 2 }}>
      <Typography variant='h4'>Add an Internship</Typography>
      <TextField
        id='outlined-basic'
        label='Job title'
        onChange={(e) => setTitle(e.target.value)}
        variant='outlined'
        sx={{ width: '100%', mt: 2 }}
      />
      <Button variant='contained' sx={{ mt: 2 }} color='success' onClick={handleSubmit}>
        Submit
      </Button>
    </Box>
  );
}
