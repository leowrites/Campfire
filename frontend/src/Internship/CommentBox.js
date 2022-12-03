import Box from '@mui/material/Box';
import TextField from '@mui/material/TextField';
import { useState } from 'react';
import Button from '@mui/material/Button';

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
          parentType === "Internship" ? postComment(comment, 0)
          : postComment(parentType, parentId, comment);
        }}>
        post
      </Button>
      <Button onClick={handleShowCommentBox}>Close</Button>
    </Box>
  );
}
