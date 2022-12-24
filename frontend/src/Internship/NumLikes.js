import { useEffect, useState } from 'react';
import Typography from '@mui/material/Typography';
import Button from '@mui/material/Button';
import axios from 'axios';
import useAuthContext from "../AuthContext";


const NumLikes = ({reviewId, numLikes}) => {


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
                if (res.data.vote === 'HELPFUL'){
                    setNumberLikes(numberLikes + 1);
                } else if (res.data.vote === 'UNHELPFUL'){
                    setNumberLikes(numberLikes - 1);
                } else {
                    console.log("do nothing")
                }
            }
        });
    }

    return(
        <Button onClick={handleLike}>
            <Typography sx={{ mr: 2, fontWeight: 'bold', color: 'white' }}> 👍 {numberLikes}</Typography>
        </Button>
    )
}

export default NumLikes;