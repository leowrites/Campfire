import { createTheme, responsiveFontSizes } from '@mui/material/styles';
import '@fontsource/ibm-plex-sans-condensed';

let theme = createTheme({
  typography: {
    fontFamily: ['IBM Plex Sans Condensed'],
  },
  palette: {
    primary: {
      main: 'rgb(255, 255, 255)',
    },
    secondary: {
      main: '#2E6489',
    },
  },
});

theme = responsiveFontSizes(theme)

export default theme;