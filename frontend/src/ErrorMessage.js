import Alert from '@mui/material/Alert';
import Snackbar from '@mui/material/Snackbar';

export default function ErrorMessage({ open, status, msg, handleClose }) {
  return (
    <Snackbar anchorOrigin={{ vertical: 'top', horizontal: 'right' }} open={open} autoHideDuration={3000} onClose={handleClose}>
      <Alert severity={status} onClose={handleClose}>
        {msg}
      </Alert>
    </Snackbar>
  );
}
