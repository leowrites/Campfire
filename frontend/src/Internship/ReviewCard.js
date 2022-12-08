import { useEffect, useState } from 'react';
import Typography from '@mui/material/Typography';
import Paper from '@mui/material/Paper';
import Box from '@mui/material/Box';
import Button from '@mui/material/Button';
import axios from 'axios';
import CommentBox from './CommentBox';
import { useParams } from 'react-router-dom';
import CommentCard from './CommentCard';
import useAuthContext from '../AuthContext';
import Rating from '@mui/material/Rating';
import FavoriteBorderIcon from '@mui/icons-material/FavoriteBorder';
import FavoriteIcon from '@mui/icons-material/Favorite';
import NumLikes from './NumLikes'
import NumDislikes from "./NumDislikes";

export default function ReviewCard({
  reviewId,
  userId,
  datePosted,
  numLikes,
  numDislikes,
  content,
  rating,
}) {
  const { corporateId, internshipId } = useParams();
  const [showComment, setShowComment] = useState(false);
  const [moreComments, setMoreComments] = useState([]);
  const [numberLikes, setNumberLikes] = useState(numLikes);
  const [numberDislikes, setNumberDislikes] = useState(numDislikes);
  const authContext = useAuthContext();
  const principal = authContext.principal;
  const handleDelete = () => {
    console.log('called');
    console.log('numlikes:' + numLikes);
    axios
      .delete(`/corporates/${corporateId}/internships/${internshipId}/reviews/${reviewId}`, {
        data: {
          internshipId: internshipId,
          reviewId: reviewId,
          userId: userId,
        },
      })
      .then(() => {
        window.location.reload();
      });
  };
  const handleShowCommentBox = () => {
    setShowComment(!showComment);
  };

  useEffect(() => {
    fetchComments();
  }, []);

  const fetchComments = () => {
    axios
      .get(`/corporates/${corporateId}/internships/${internshipId}/reviews/${reviewId}`)
      .then((res) => setMoreComments(res.data));
  };

  const postComment = (parentType, parentId, comment) => {
    console.log(parentType, parentId, comment, reviewId);
    axios
      .post(`/corporates/${corporateId}/internships/${internshipId}/reviews/${reviewId}/comments`, {
        userId: principal.username,
        parentType: parentType,
        parentId: parentId,
        content: comment,
      })
      .then((res) => {
        if (res.data.status === 'SUCCESS') {
          setShowComment(false);
          setMoreComments([
            ...moreComments,
            {
              userId: principal.username,
              id: res.data.id,
              content: comment,
              comments: [],
              datePosted: res.data.datePosted,
            },
          ]);
        }
      });
  };

  const handleLike = () =>{
      axios.post(`/users/vote-helpful`, {
          isHelpful: "Helpful",
          reviewId: reviewId,
          userId: userId
      }).then((res) => {
          if (res.data.status === 'SUCCESS') {
              if (res.data.vote === 'HELPFUL'){
                  setNumberLikes(numberLikes + 1);
              } else if (res.data.vote === 'UNHELPFUL'){
                  setNumberDislikes(numberDislikes + 1);
              } else {
                console.log("do nothing")
              }
          }
      });
    }

  return (
    <Box
      sx={{
        background: 'rgba(22, 22, 22, 1)',
        borderRadius: 5,
        my: 3,
        py: 3,
        px: 5,
      }}>
      <Box textAlign='start'>
        <Box sx={{ display: 'flex' }}>
          <Typography variant='h5' sx={{ fontWeight: 'bold' }}>
            {userId}
          </Typography>
          <Paper
            sx={{
              ml: 'auto',
              display: 'inline-flex',
              alignItems: 'center',
              background: 'rgba(35, 36, 35, 0.4)',
              color: 'white',
              px: 2,
              py: 1,
              borderRadius: 3,
            }}
            elevation={5}>
            {/*<Button onClick={handleLike}>*/}
            {/*    <Typography sx={{ mr: 2, fontWeight: 'bold', color: 'white' }}> 👍 {numberLikes}</Typography>*/}
            {/*</Button>*/}
              <NumLikes userId={userId} reviewId={reviewId} numLikes={numLikes}></NumLikes>
              <NumDislikes userId={userId} reviewId={reviewId} numDislikes={numDislikes}></NumDislikes>

          </Paper>
        </Box>
        <Rating
          value={2}
          icon={<FavoriteIcon />}
          emptyIcon={<FavoriteBorderIcon sx={{ color: 'gray' }} />}
          readOnly
        />
        <Box sx={{ ml: 2, my: 1 }}>
          <Typography variant='h7' style={{ wordWrap: 'break-word' }}>
            {content}
          </Typography>
        </Box>
        <Box textAlign={'right'}>
          <Typography sx={{ ml: 'auto' }}>{datePosted.match(/^\d{4}-\d{2}-\d{2}/)}</Typography>
        </Box>
        <Box sx={{ display: 'flex', my: 1 }}>
          <Button
            sx={{ ml: 'auto', mr: 1 }}
            variant='contained'
            onClick={handleShowCommentBox}
            color='primary'
            size='small'>
            comment
          </Button>
          <Button variant='contained' onClick={handleDelete} color='error' size='small'>
            delete
          </Button>
        </Box>
      </Box>
      {showComment ? (
        <Box sx={{ mt: 2 }}>
          <CommentBox
            handleShowCommentBox={handleShowCommentBox}
            parentType={'Review'}
            parentId={reviewId}
            postComment={postComment}
          />
        </Box>
      ) : undefined}

      {moreComments.length > 0 &&
        moreComments.map((comment, i) => {
          console.log(comment);
          return (
            <CommentCard
              key={i}
              reviewId={reviewId}
              parentType={'Review'}
              commentId={comment.id}
              parentId={reviewId}
              userId={comment.userId}
              content={comment.content}
              datePosted={comment.datePosted}
              comments={comment.comments}
            />
          );
        })}
    </Box>
  );
}
