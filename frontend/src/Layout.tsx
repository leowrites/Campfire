import { Outlet } from 'react-router';
import Navbar from './Component/Navbar';
import Footer from './Footer';
import Box from '@mui/material/Box'
import React from 'react';

export default function Layout() {
  return (
    <>
      <Navbar />
      <Box sx={{minHeight: '100vh'}}>
      <Outlet />
      </Box>
      <Footer />
    </>
  );
}
