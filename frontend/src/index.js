import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import App from './App';
import Login from "./Component/Login";
import SignUp from "./Component/SignUp";
import {createBrowserRouter, RouterProvider} from "react-router-dom";



const router = createBrowserRouter([
    {
        path: '/',
        element: <App />
    },
    {
        path: 'login',
        element: <Login />
    },
    {
        path: 'signup',
        element: <SignUp />
    }
])
ReactDOM.createRoot(document.getElementById("root")).render(<RouterProvider router={router} />)
