import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import App from './App';
import Login from './Component/Login';
import SignUp from './Component/SignUp';
import HomePage from './Home/HomePage';
import { createBrowserRouter, RouterProvider, ScrollRestoration } from 'react-router-dom';
import Layout from './Layout';
import { GlobalContextProvider } from './GlobalContext';
import { AuthContextProvider } from './AuthContext';
import InternshipPage from './Internship/InternshipPage';
import AddInternshipForm from './Corporate/AddInternshipForm';
import CreateCompany from './Corporate/CreateCompany';
import ScrollToTop from './ScrollToTop';
import { ThemeProvider } from '@mui/material';
import theme from './theme';
import CorporateLayout from './Corporate/CorporateLayout';
import InternshipGroup from './Corporate/InternshipGroup';
import RouteProtectionWrapper from './RouteProtectionWrapper';

const routes = [
  {
    element: <Layout />,
    children: [
      {
        path: '/',
        element: <HomePage />,
      },
      {
        path: '/corporates/create',
        element: (
          <RouteProtectionWrapper>
            <CreateCompany />
          </RouteProtectionWrapper>
        ),
      },
      {
        path: '/corporates/:corporateId',
        element: <CorporateLayout />,
        children: [
          {
            index: true,
            element: <InternshipGroup />,
          },
          {
            path: 'internships/create',
            element: (
              <RouteProtectionWrapper>
                <AddInternshipForm />
              </RouteProtectionWrapper>
            ),
          },
          {
            path: 'internships/:internshipId',
            element: <InternshipPage />,
          },
        ],
      },
      {
        path: '/login',
        element: <Login />,
      },
      {
        path: '/signup',
        element: <SignUp />,
      },
    ],
  },
];

const router = createBrowserRouter([
  {
    element: (
      <GlobalContextProvider>
        <ThemeProvider theme={theme}>
          <ScrollToTop />
          <App />
        </ThemeProvider>
      </GlobalContextProvider>
    ),
    children: routes,
  },
]);

ReactDOM.createRoot(document.getElementById('root')).render(
  <AuthContextProvider>
    <RouterProvider router={router} />
  </AuthContextProvider>
);
