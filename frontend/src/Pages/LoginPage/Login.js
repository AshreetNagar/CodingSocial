import React from 'react';
import LoginForm from '../../Components/LoginForm';
import { checkLoginStatus } from '../../common';
import { logoutFunction } from '../../common';

const handleLogout = async (event) => {
  event.preventDefault();
  logoutFunction();
  window.location.reload(); 
};

function LoginContent(){
  if (checkLoginStatus()){
    return <form onSubmit={handleLogout}><button type="submit">log out {sessionStorage.getItem("login")}</button></form>
  }else{
    return <LoginForm/>
  }
}

function Login() {

  console.log(checkLoginStatus());
  return (
    <div className="login-container">
      <h1>hello from LOGIN</h1>
      <LoginContent/>
    </div>
  );
} 

export default Login;
