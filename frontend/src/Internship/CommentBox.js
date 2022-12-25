import Box from '@mui/material/Box';
import { useState } from 'react';
import Button from '@mui/material/Button';
import CustomTextField from '../Component/CustomTextfield';
import useTheme from '@mui/material/styles/useTheme';
import useAuthContext from '../AuthContext';
import axios from 'axios';
import { useParams } from 'react-router-dom';

export default function CommentBox({
  handleShowCommentBox,
  parentType,
  parentId,
  reviewId,
  postReview,
  rating,
  handleAddComments,
}) {
  const [comment, setComment] = useState('');
  const [clicked, setClicked] = useState(false);
  const authContext = useAuthContext();
  const principal = authContext.principal
  const theme = useTheme();
  const { corporateId, internshipId } = useParams()
  const handleClickPost = () => {
    setClicked(true);
    parentType === 'Internship' ? postReview(comment, rating) :
    postComment();
    setTimeout(() => {
      setClicked(false);
    }, 3000);
  };
  const handleValChange = (e) => {
    setComment(e.target.value);
  };

  const postComment = () => {
    axios
      .post(`/corporates/${corporateId}/internships/${internshipId}/reviews/${reviewId}/comments`, {
        userId: principal.username,
        parentType: parentType,
        parentId: parentId,
        content: comment,
      })
      .then((res) => {
        if (res.data.status === 'SUCCESS') {
          handleShowCommentBox();
          handleAddComments({
            userId: principal.username,
            id: res.data.id,
            content: comment,
            comments: [],
            datePosted: res.data.datePosted,
          });
        }
      });
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
