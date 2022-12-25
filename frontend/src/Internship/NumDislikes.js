import Typography from '@mui/material/Typography';
import Button from '@mui/material/Button';

export default function NumDislikes ({ numDislikes, handleDislike }) {
  return (
    <Button onClick={handleDislike}>
      <Typography sx={{ mr: 2, fontWeight: 'bold', color: 'white' }}>
        {' '}
        👎 {numDislikes}
      </Typography>
    </Button>
  );
};
