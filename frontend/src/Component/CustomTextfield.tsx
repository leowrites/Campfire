import TextField, { TextFieldProps } from '@mui/material/TextField';
import useTheme from '@mui/material/styles/useTheme';
import React from 'react';

export default function CustomTextField(props: any) {
  const theme = useTheme();
  return (
    <TextField
      size='small'
      variant='filled'
      InputProps={{ disableUnderline: true }}
      {...props}
      sx={{
        background: 'rgb(0, 0, 0, 0.3)',
        color: theme.palette.secondary.main,
        backgroundColor: 'rgba(0,0,0,0.3)',
        borderRadius: 2,
        '& .MuiInputBase-root': {
          color: 'white',
        },
        'label.Mui-focused': {
          color: theme.palette.secondary.main,
        },
        'label.MuiInputLabel-root': {
          color: theme.palette.secondary.main,
        },
        ...props.sx,
      }}
    />
  );
}
