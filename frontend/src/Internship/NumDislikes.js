import { useEffect, useState } from 'react';
import Typography from '@mui/material/Typography';
import Button from '@mui/material/Button';
import axios from 'axios';

const NumDislikes = ({reviewId, userId, numDislikes}) => {

    const [numberDislikes, setNumberDislikes] = useState(numDislikes);

    useEffect(()=>{
        console.log("work");
    }, [numberDislikes])


    const handleDislike = () =>{
        axios.post(`/users/vote-helpful`, {
            isHelpful: "Unhelpful",
            reviewId: reviewId,
            userId: userId
        }).then((res) => {
            if (res.data.status === 'SUCCESS') {
                if (res.data.vote === 'HELPFUL'){
                    setNumberDislikes( numberDislikes - 1);
                } else if (res.data.vote === 'UNHELPFUL'){
                    setNumberDislikes(numberDislikes + 1);
                } else {
                    console.log("do nothing")
                }
            }
        });
    }

    return(
        <Button onClick={handleDislike}>
            <Typography sx={{ mr: 2, fontWeight: 'bold', color: 'white' }}> 👎 {numberDislikes}</Typography>
        </Button>
    )
}

export default NumDislikes;