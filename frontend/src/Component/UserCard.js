import { Typography } from '@mui/material';
import Paper from '@mui/material/Paper';
import Box from '@mui/material/Box'
import Button from '@mui/material/Button';

function UserCard({
  username,
  sendConnectionRequest,
  incomingConnectionRequests,
  outgoingConnectionRequests,
  sendAcceptConnectionRequest,
  connections,
}) {
  return (
    <Paper sx={{ m: 3}}>
      <Typography>{username.toUpperCase()}</Typography>
      <Typography>Connections:</Typography>
      {connections.map((c, i) => (
        <Typography key={i}>{c}</Typography>
      ))}
      <Typography sx={{ mt: 2 }}>Requests sent:</Typography>
      {outgoingConnectionRequests?.map((c, i) => (
        <Typography key={i}>{c}</Typography>
      ))}
    <Typography sx={{ mt: 2 }}>Requests from:</Typography>
      {incomingConnectionRequests?.map((c, i) => (
        <Box >
          <Typography key={i} sx={{display: 'inline-block'}}>{c}</Typography>
          <Button onClick={() => sendAcceptConnectionRequest(username, c)}>Accept</Button>
        </Box>
      ))}
      <Button
        onClick={() => {
          sendConnectionRequest(username);
        }}>
        Connect
      </Button>
    </Paper>
  );
}

export default UserCard;
