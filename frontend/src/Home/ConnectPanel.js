import { useState } from 'react';
import useGlobalContext from '../GlobalContext';
import Typography from '@mui/material/Typography';
import Box from '@mui/material/Box';
import Button from '@mui/material/Button';
import useAuthContext from '../AuthContext';
import CustomTextField from '../Component/CustomTextfield';

export default function ConnectPannel() {
  const globalContext = useGlobalContext();
  const [connectionInput, setConnectionInput] = useState();
  const { principal } = useAuthContext();

  return (
    <Box
      sx={{
        p: 5,
        background: '#212122',
        height: '100%',
        color: 'white',
      }}>
      <Typography
        variant='h5'
        textAlign={'start'}
        sx={{
          fontWeight: 'bold',
          '& .MuiFilledInput-root': {
            '&:before': { borderBottomColor: 'transparent' },
            '&:after': { borderBottomColor: 'transparent' },
            '&:hover fieldset': {
              borderColor: 'yellow', // - Set the Input border when parent has :hover
            },
          },
        }}>
        Search for a connection
      </Typography>
      <Box sx={{ display: 'flex', mt: 1 }}>
        <CustomTextField
          label='username'
          fullWidth
          onChange={(e) => setConnectionInput(e.target.value)}
        />
        <Button
          sx={{ mt: 1, ml: 1, backgroundColor: 'black', borderRadius: 2 }}
          variant='contained'
          onClick={() => globalContext.sendConnectionRequest(connectionInput)}>
          Connect
        </Button>
      </Box>
      <Box sx={{ mt: 2 }}>
        <Typography variant='h6' textAlign={'start'}>
          Your Connections
        </Typography>
        {principal?.user?.connectedUsers?.map((user) => (
          <Typography key={user.username} textAlign={'start'} variant='h6'>
            {user.username}
          </Typography>
        ))}
      </Box>
      <Box sx={{ mt: 2 }}>
        <Typography variant='h6' textAlign={'start'}>
          Your Outgoing Requests
        </Typography>
        {principal?.user?.outgoingConnectionRequests?.map((user) => (
          <Typography key={user.username} textAlign={'start'} variant='h6'>
            {user.username}
          </Typography>
        ))}
      </Box>
      <Box sx={{ mt: 2 }}>
        <Typography variant='h6' textAlign={'start'}>
          Your Incoming Requests
        </Typography>
        {principal?.user?.incomingConnectionRequests?.map((user) => (
          <Box key={user.username} sx={{ display: 'flex', justifyContent: 'left' }}>
            <Typography textAlign={'start'} variant='h6'>
              {user.username}
            </Typography>
            <Button
              sx={{ ml: 'auto' }}
              onClick={() =>
                globalContext.sendAcceptConnectionRequest(user.username)
              }>
              Accept
            </Button>
          </Box>
        ))}
      </Box>
    </Box>
  );
}
