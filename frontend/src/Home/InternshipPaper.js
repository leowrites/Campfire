import { Typography } from "@mui/material"
import Paper from "@mui/material/Paper"
import Button from '@mui/material/Button';

function InternshipPaper ({ title, info }){
    return (
      <Paper elevation={2} sx={{ m: 3, p: 3 }}>
        <Typography>
            {title}
        </Typography>
        <Typography>
            {info}
        </Typography>
        <Button size="medium">
          View this internship
        </Button>
      </Paper>
    )
  }

export default InternshipPaper