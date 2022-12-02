import Box from '@mui/material/Box';
import TextField from '@mui/material/TextField';
import axios from 'axios';
import { useState } from 'react';
import { useParams } from 'react-router-dom';
import Button from '@mui/material/Button';
import useAuthContext from '../AuthContext';

export default function CommentBox({ handleShowCommentBox, postComment, parentType, parentId }) {
  const [comment, setComment] = useState('');
  const handleValChange = (e) => {
    setComment(e.target.value);
  };
  return (
    <Box>
      <TextField fullWidth onChange={handleValChange} />
      <Button
        onClick={() => {
          postComment(parentType, parentId, comment);
        }}>
        post
      </Button>
      <Button onClick={handleShowCommentBox}>Close</Button>
    </Box>
  );
}
