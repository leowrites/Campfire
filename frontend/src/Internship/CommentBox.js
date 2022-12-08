import Box from '@mui/material/Box';
import { useState } from 'react';
import Button from '@mui/material/Button';
import CustomTextField from '../Component/CustomTextfield';
import useTheme from '@mui/material/styles/useTheme';

export default function CommentBox({ handleShowCommentBox, postComment, parentType, parentId }) {
  const [comment, setComment] = useState('');
  const theme = useTheme();
  const handleValChange = (e) => {
    setComment(e.target.value);
  };
  return (
    <Box>
      <CustomTextField
        placeholder='Write something...'
        fullWidth
        hiddenLabel
        multiline
        onChange={handleValChange}
        sx={{ mb: 2 }}
      />
      <Box sx={{ display: 'flex' }}>
        <Button
          size='small'
          sx={{ background: theme.palette.primary.main, color: 'white', mr: 1, ml: 'auto' }}
          onClick={() => {
            parentType === 'Internship'
              ? postComment(comment, 0)
              : postComment(parentType, parentId, comment);
          }}>
          post
        </Button>
        <Button
          size='small'
          sx={{ background: theme.palette.primary.main, color: 'white' }}
          onClick={handleShowCommentBox}>
          Close
        </Button>
      </Box>
    </Box>
  );
}
