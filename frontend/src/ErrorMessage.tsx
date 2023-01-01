import Alert from '@mui/material/Alert';
import Snackbar from '@mui/material/Snackbar';
import useGlobalContext from './GlobalContext';
import React from 'react';

export default function ErrorMessage() {
  const globalContext = useGlobalContext();
  return (
    <Snackbar
      anchorOrigin={{ vertical: 'top', horizontal: 'right' }}
      open={globalContext.showMsg}
      autoHideDuration={3000}
      onClose={globalContext.handleNotificationClose}>
      <Alert>
        {globalContext.msg}
      </Alert>
    </Snackbar>
  );
}
