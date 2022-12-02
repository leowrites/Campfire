import Box from "@mui/material/Box"
import TextField from "@mui/material/TextField"
import axios from "axios"
import { useState } from 'react';
import { useParams } from "react-router-dom";
import Button from "@mui/material/Button";
import useAuthContext from "../AuthContext";

export default function CommentBox({ handleShowCommentBox, userId, parentType, parentId }) {
    const [comment, setComment] = useState('')
    const { corporateId, internshipId } = useParams();
    const authContext = useAuthContext();
    const principal = authContext.principal;
    const postComment = () => {
        axios.post(`/corporates/${corporateId}/internships/${internshipId}`, {
            userId: principal.username,
            parentType: parentType,
            parentId: parentId,
            content: comment,
        })
    }
    const handleValChange = (e) => {
        setComment(e.target.value)
    }
    return (
        <Box>
            <TextField fullWidth onChange={handleValChange}/>
            <Button onClick={postComment}>post</Button>
            <Button onClick={handleShowCommentBox}>Close</Button>
        </Box>
    )
}