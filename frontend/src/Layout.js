import { Outlet } from 'react-router';
import Navbar from './Component/Navbar';
import Container from '@mui/material/Container';
import Footer from './Footer';

export default function Layout() {
  return (
    <>
      <Navbar />
      <Container disableGutters={true} sx={{padding:0, margin: 0, width:'100%'}}>
        <Outlet />
      </Container>
      <Footer />
    </>
  );
}
