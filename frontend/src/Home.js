import {Link} from "react-router-dom";
import React from "react";

function Home() {
    return(
        <div>
            <h1>HOME</h1>
            <Link to="signUp">SIGN UP.</Link>
            <br/>
            <Link to="login">LOG IN.</Link>
        </div>);

}

export default Home;