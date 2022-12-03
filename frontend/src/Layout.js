import { Outlet } from 'react-router';
import Navbar from './Component/Navbar';
import Container from '@mui/material/Container';

export default function Layout() {
  return (
    <>
      <Navbar />
      <Container>
        <Outlet />
      </Container>
    </>
  );
}
