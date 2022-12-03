import { useState } from 'react';
import Typography from '@mui/material/Typography';
import Box from '@mui/material/Box';
import TextField from '@mui/material/TextField';
import Button from '@mui/material/Button';
import Container from '@mui/material/Container';
import { useParams, useNavigate } from 'react-router-dom';
import axios from 'axios';

export default function AddInternshipForm() {
  // get form data
  const { corporateId } = useParams();
  const [title, setTitle] = useState('');
  const [description, setDescription] = useState('');
  const navigate = useNavigate();

  // make request to backend using axios
  const handleSubmit = (e) => {
    // need to integrate with backend once it's ready
    console.log(title, description);
    // axios request to backend
    axios
      .post(`/corporates/${corporateId}/internships`, {
        title: title,
        description: description,
      })
      .then((data) => {
        console.log(data);
        // redirect to newly created internship using navigate
        navigate(`${data.data.id}`);
      });
  };

  return (
    <Box sx={{ my: 2 }}>
      <Typography variant='h4'>Add an Internship</Typography>
      <TextField
        id='outlined-basic'
        label='Title'
        onChange={(e) => setTitle(e.target.value)}
        variant='outlined'
        sx={{ width: '100%', mt: 2 }}
      />
      <TextField
        id='outlined-basic'
        onChange={(e) => setDescription(e.target.value)}
        label='Description'
        variant='outlined'
        sx={{ width: '100%', mt: 2 }}
      />
      <Button variant='contained' sx={{ mt: 2 }} color='success' onClick={handleSubmit}>
        Submit
      </Button>
    </Box>
  );
}
