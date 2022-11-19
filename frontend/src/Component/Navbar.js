import * as React from 'react';
import AppBar from '@mui/material/AppBar';
import Stack from '@mui/material/Stack';
import Toolbar from '@mui/material/Toolbar';
import Typography from '@mui/material/Typography';
import Button from '@mui/material/Button';
import { ThemeProvider, createTheme } from '@mui/material/styles';
import { Box } from '@mui/system';

const darkTheme = createTheme({
  palette: {
    mode: 'dark',
    primary: {
      main: '#1976d2',
    },
  },
});

export default function Navbar({sendMessage}) {
  const resetAllConnections = () => {
    fetch('/users/reset', {
      method: "POST"
    })
  }
  return (
    <Stack spacing={2} sx={{ flexGrow: 1 }}>
      <ThemeProvider theme={darkTheme}>
        <AppBar position='static' color='primary'>
          <Toolbar>
            <Typography variant='h6'>Rate my Intern</Typography>
            <Box sx={{ ml: 'auto' }}>
              <Button sx={{ color: 'white' }}>Login</Button>
              <Button sx={{ color: 'white' }}>Sign in</Button>
              <Button sx={{ color: 'white' }} onClick={sendMessage}>Send a message</Button>
              <Button sx={{ color: 'white' }} onClick={resetAllConnections} > reset all connections</Button>
            </Box>
          </Toolbar>
        </AppBar>
      </ThemeProvider>
    </Stack>
  );
}
