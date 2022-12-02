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
import Corporate from './Corporate/Corporate';
import Internship from './Internship/Internship';

const internshipRoutes = [{
  path: 'internships',
  children: [
    {
      path: ':internshipId',
      element: <Internship />,
    },
  ],
}];

const routes = [
  {
    element: <Layout />,
    children: [
      {
        path: '/',
        element: <HomePage />,
      },
      {
        path: 'corporates',
        children: [
          {
            path: ':corporateId',
            element: <Corporate />,
            children: internshipRoutes,
          },
        ],
      },
      {
        path: 'login',
        element: <Login />,
      },
      {
        path: 'signup',
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
