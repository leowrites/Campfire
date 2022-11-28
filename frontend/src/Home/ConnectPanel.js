import { useState } from 'react';
import useGlobalContext from '../GlobalContext';
import Typography from '@mui/material/Typography';
import Box from '@mui/material/Box';
import TextField from '@mui/material/TextField';
import Button from '@mui/material/Button';
import useAuthContext from '../AuthContext';

export default function ConnectPannel() {
  const globalContext = useGlobalContext();
  const [connectionInput, setConnectionInput] = useState();
  const { principal } = useAuthContext();

  return (
    <Box>
      <Typography variant='h5' textAlign={'start'}>
        Search for a connection
      </Typography>
      <Box sx={{ display: 'flex', mt: 1 }}>
        <TextField
          label='username'
          variant='filled'
          size='small'
          fullWidth
          onChange={(e) => setConnectionInput(e.target.value)}
        />
        <Button
          sx={{ mt: 1, ml: 1 }}
          variant='contained'
          onClick={() => globalContext.sendConnectionRequest(principal?.username, connectionInput)}>
          Connect
        </Button>
      </Box>
      <Box sx={{ mt: 2 }}>
        <Typography variant='h5' textAlign={'start'}>
          Your Connections
        </Typography>
        {principal?.user?.connections?.map((connection) => (
          <Typography key={connection} textAlign={'start'} variant='h6'>
            {connection}
          </Typography>
        ))}
      </Box>
      <Box sx={{ mt: 2 }}>
        <Typography variant='h5' textAlign={'start'}>
          Your Outgoing Requests
        </Typography>
        {principal?.user?.outgoingConnectionRequests?.map((username) => (
          <Typography key={username} textAlign={'start'} variant='h6'>
            {username}
          </Typography>
        ))}
      </Box>
      <Box sx={{ mt: 2 }}>
        <Typography variant='h5' textAlign={'start'}>
          Your Incoming Requests
        </Typography>
        {principal?.user?.incomingConnectionRequests?.map((target) => (
          <Box key={target} sx={{ display: 'flex', justifyContent: 'left' }}>
            <Typography textAlign={'start'} variant='h6'>
              {target}
            </Typography>
            <Button
              sx={{ ml: 'auto' }}
              onClick={() =>
                globalContext.sendAcceptConnectionRequest(principal?.username, target)
              }>
              Accept
            </Button>
          </Box>
        ))}
      </Box>
    </Box>
  );
}
