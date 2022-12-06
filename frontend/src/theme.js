import { createTheme, responsiveFontSizes } from '@mui/material/styles';
import '@fontsource/ibm-plex-sans-condensed';

let theme = createTheme({
  typography: {
    fontFamily: ['IBM Plex Sans Condensed'],
  },
  palette: {
  }
});

theme = responsiveFontSizes(theme)

export default theme;