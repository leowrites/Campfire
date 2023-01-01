import Box from '@mui/material/Box';
import { useState } from 'react';
import Button from '@mui/material/Button';
import CustomTextField from '../Component/CustomTextfield';
import useTheme from '@mui/material/styles/useTheme';
import React from 'react';
import { usePostComment, usePostReview } from './hooks';

interface CommentBoxProps {
  handleShowCommentBox: () => void;
  handlePost: (comment: string) => void
}

export default function CommentBox({
  handleShowCommentBox,
  handlePost,
}: CommentBoxProps) {
  const [comment, setComment] = useState('');
  const [clicked, setClicked] = useState(false);
  const theme = useTheme();
  const handleClickPost = () => {
    setClicked(true);
    handlePost(comment)
    setTimeout(() => {
      setClicked(false);
    }, 3000);
  };
  const handleValChange = (e: TextInputEvent) => {
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
          disabled={clicked}
          onClick={handleClickPost}>
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
