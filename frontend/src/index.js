import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import App from './App';
import Login from './Component/Login';
import SignUp from './Component/SignUp';
import HomePage from './Home/HomePage';
import { createBrowserRouter, RouterProvider } from 'react-router-dom';
import Layout from './Layout';
import { GlobalContextProvider } from './GlobalContext';
import { AuthContextProvider } from './AuthContext';
import CorporatePage from './Corporate/CorporatePage';
import InternshipPage from './Internship/InternshipPage';
import AddInternshipForm from './Corporate/AddInternshipForm';
import CreateCompany from "./Corporate/CreateCompany";

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
        element: <CreateCompany></CreateCompany>
      },
      {
        path: '/corporates/:corporateId',
        element: <CorporatePage />,
      },
      {
        path: '/corporates/:corporateId/internships/:internshipId',
        element: <InternshipPage />,
      },
      {
        path: '/corporates/:corporateId/internships',
        element: <AddInternshipForm />,
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
        <App />
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
