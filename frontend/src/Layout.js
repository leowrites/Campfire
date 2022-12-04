import { Outlet } from 'react-router';
import Navbar from './Component/Navbar';
import Container from '@mui/material/Container';
import Footer from './Footer';

export default function Layout() {
  return (
    <>
      <Navbar />
      <Outlet />
      <Footer />
    </>
  );
}
