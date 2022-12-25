import { useState } from 'react';
import Typography from '@mui/material/Typography';
import Button from '@mui/material/Button';
import axios from 'axios';
import useAuthContext from "../AuthContext";


const VoteCount = ({reviewId, numLikes, numDislikes}) => {

    const [numberDislikes, setNumberDislikes] = useState(numDislikes);
    const [numberLikes, setNumberLikes] = useState(numLikes);
    const authContext = useAuthContext();
    const principal = authContext.principal;

    const handleLike = () =>{
        axios.post(`/users/vote-helpful`, {
            isHelpful: "Helpful",
            reviewId: reviewId,
            userId: principal.username
        }).then((res) => {
            if (res.data.status === 'SUCCESS') {
                setNumberLikes(res.data.finalHelpfulVote);
                console.log(res.data.finalHelpfulVote);
                setNumberDislikes(res.data.finalUnhelpfulVote);
                console.log(res.data.finalUnhelpfulVote);
            }
        });
    }

    const handleDislike = () =>{
        axios.post(`/users/vote-helpful`, {
            isHelpful: "Unhelpful",
            reviewId: reviewId,
            userId: principal.username
        }).then((res) => {
            if (res.data.status === 'SUCCESS') {
                setNumberLikes(res.data.finalHelpfulVote);
                console.log(res.data.finalHelpfulVote);
                setNumberDislikes(res.data.finalUnhelpfulVote);
                console.log(res.data.finalUnhelpfulVote);
            }
        });
    }

    return(
        <>
            <Button onClick={handleLike}>
              <Typography sx={{ mr: 2, fontWeight: 'bold', color: 'white' }}> 👍 {numberLikes}</Typography>
            </Button>
            <Button onClick={handleDislike}>
              <Typography sx={{ mr: 2, fontWeight: 'bold', color: 'white' }}> 👎 {numberDislikes}</Typography>
            </Button>
        </>
    )
}

export default VoteCount;