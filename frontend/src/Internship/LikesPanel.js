import Paper from '@mui/material/Paper';
import NumLikes from './NumLikes';
import NumDislikes from './NumDislikes';
import { useState } from 'react';
import axios from 'axios';

export default function LikesPanel({ userId, numLikes, numDislikes, reviewId }) {
  const [votes, setVotes] = useState({
    likes: numLikes,
    dislikes: numDislikes
  })

  const handleLike = () => {
    axios
      .post(`/users/vote-helpful`, {
        isHelpful: 'Helpful',
        reviewId: reviewId,
        userId: userId,
      })
      .then((res) => {
        if (res.data.status === 'SUCCESS') {
            setVotes({
                likes: res.data.votes[0],
                dislikes: res.data.votes[1]
            })
        }
      });
  };

  const handleDislike = () => {
    axios
      .post(`/users/vote-helpful`, {
        isHelpful: 'Unhelpful',
        reviewId: reviewId,
        userId: userId,
      })
      .then((res) => {
        if (res.data.status === 'SUCCESS') {
            setVotes({
                likes: res.data.votes[0],
                dislikes: res.data.votes[1]
            })
        }
      });
  };
  return (
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
      <NumLikes
        userId={userId}
        reviewId={reviewId}
        numLikes={votes.likes}
        handleLike={handleLike}></NumLikes>
      <NumDislikes
        userId={userId}
        reviewId={reviewId}
        numDislikes={votes.dislikes}
        handleDislike={handleDislike}></NumDislikes>
    </Paper>
  );
}
