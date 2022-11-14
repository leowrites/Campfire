import logo from './logo.svg';
import './App.css';
import React from "react";
import { Routes, Route, Link } from "react-router-dom"

function App() {
  return (
      <div className="App">
        <Routes>
            <Route path="/" element={ <Home/> } />
            <Route path="signup" element={ <SignUp/> }> </Route>
            <Route path="login" element={ <Login/> }> </Route>
      </Routes>
      </div>
  )
}

function Home() {
  return(
      <div>
        <h1>HOME</h1>
          <Link to="signUp">SIGN UP.</Link>
          <br/>
          <Link to="login">LOG IN.</Link>
      </div>);

}

function SignUp() {
  return <h2>SIGN UP</h2>;
}

function Login(){
    return <h2> LOGIN </h2>
}


export default App;
