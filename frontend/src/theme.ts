import { createTheme, responsiveFontSizes } from '@mui/material/styles';
import '@fontsource/ibm-plex-sans-condensed';

let theme = createTheme({
  typography: {
    fontFamily: 'IBM Plex Sans Condensed',
  },
  palette: {
    primary: {
      main: '#14110f',
    },
    secondary: {
      main: '#ffffff',
    },
  },
});

theme = responsiveFontSizes(theme);

export default theme;
