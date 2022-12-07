import TextField from '@mui/material/TextField';

export default function CustomTextField(props) {
  return (
    <TextField
      size='small'
      variant='filled'
      InputProps={{ disableUnderline: true }}
      {...props}
      sx={{
        color: 'black',
        '& label.Mui-focused': {
          color: 'black',
        },
        backgroundColor: 'white',
        borderRadius: 2,
        ...props.sx
      }}
    />
  );
}
