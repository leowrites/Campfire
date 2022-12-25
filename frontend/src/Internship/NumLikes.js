import Typography from '@mui/material/Typography';
import Button from '@mui/material/Button';

export default function NumLikes({ numLikes, handleLike }) {
  return (
    <Button onClick={handleLike}>
      <Typography sx={{ mr: 2, fontWeight: 'bold', color: 'white' }}> 👍 {numLikes}</Typography>
    </Button>
  );
}
