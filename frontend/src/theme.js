import { createTheme, responsiveFontSizes } from '@mui/material/styles';
import '@fontsource/ibm-plex-sans-condensed';

let theme = createTheme({
  typography: {
    fontFamily: ['IBM Plex Sans Condensed'],
  },
});

theme = responsiveFontSizes(theme)

export default theme;