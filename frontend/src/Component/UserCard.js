import { Typography } from "@mui/material"
import Paper from "@mui/material/Paper"
import Button from '@mui/material/Button';

function UserCard ({ username, sendConnectionRequest, connections }){
    return (
        <Paper sx={{ m: 3}}>
            <Typography>
                {username}
            </Typography>
            <Typography>
                CONNECTED USERS: 
            </Typography>
            {
                connections.map((c, i) => <Typography key={i}>{c}</Typography>)
            }
            <Button onClick={() => { sendConnectionRequest(username)}}>
                Connect
            </Button>
        </Paper>
    )
  }

export default UserCard