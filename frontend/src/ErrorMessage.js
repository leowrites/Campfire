import Alert from '@mui/material/Alert';
import Snackbar from '@mui/material/Snackbar';
import useGlobalContext from './GlobalContext';

export default function ErrorMessage() {
  const globalContext = useGlobalContext();
  return (
    <Snackbar
      anchorOrigin={{ vertical: 'top', horizontal: 'right' }}
      open={globalContext.showMsg}
      autoHideDuration={3000}
      onClose={globalContext.handleNotificationClose}>
      <Alert severity={globalContext.status} onClose={globalContext.handleNotificationClose}>
        {globalContext.msg}
      </Alert>
    </Snackbar>
  );
}
