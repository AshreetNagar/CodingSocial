import {
  BrowserRouter as Router,
  Route,
  Routes,
} from "react-router-dom";

import './App.css';
import Home from "./Pages/Home";
import Login from "./Pages/LoginPage/Login";
import Register from "./Pages/RegisterPage/Register";
import Friends from "./Pages/FriendsPage/Friends";
import * as common from './common';

function NavBar(){
  // if debug mode, show all options
  if (common.debugMode){
    return(
      <div className="navbar">
        <a href="/">Home Page </a>
        <a href="register">Register Page </a>
        <a href="login">Login Page </a>
        <a href="friends">Friends Page </a>
      </div>
    );
  }
  if (common.checkLoginStatus()){
    return(
      <div className="navbar">
        <a href="/">Home Page </a>
        <a href="friends">Friends Page </a>
      </div>
    );    
  }else{
    return(
      <div className="navbar">
        <a href="/">Home Page </a>
        <a href="register">Register Page </a>
        <a href="login">Login Page </a>
      </div>
    ); 
  }
}

function App() {
  return (
    <div>
      <NavBar/>
      <Router>
        <Routes>
          <Route exact path="/"  element={<Home/>} />
          <Route exact path="/login" element={<Login/>} />
          <Route exact path="/register" element={<Register/>  } />
          <Route exact path="/friends" element={<Friends/>  } />
        </Routes>
      </Router>
    </div>
  );
}

export default App;
