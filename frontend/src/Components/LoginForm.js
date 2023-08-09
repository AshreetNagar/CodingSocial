import React, { useState } from "react";
import { Form, Button } from "react-bootstrap";
import axios from "axios";

function LoginForm() {
  const [userData, setUserData] = useState({
    login: "",
    password: "",
    rememberMe: ""
  });

  const handleChange = (event) => {
    const { name, value, checked } = event.target;
    if (name == "rememberMe"){
      setUserData(userData)
      setUserData((prevState) => ({ ...prevState, [name]: value }));
      console.log(userData)
    }
    setUserData((prevState) => ({ ...prevState, [name]: value }));
  };

  const handleSubmit = async (event) => {
    event.preventDefault();

    const user = { ...userData};

    try {
      const response = await axios.post(
        `http://localhost:8080/login` ,
        user
      );
      console.info("==RESPONSE DATA START==")
      console.info(response)
      console.info(userData)
      console.info("==RESPONSE DATA END==")
      sessionStorage.setItem("userid", response.data.id);
      sessionStorage.setItem("login", response.data.login);
      sessionStorage.setItem("email", response.data.email);
    } catch (error) {
      console.error(error);
    }
    window.location.reload(); 
  };

  return (
    <div className="form-container">
      <Form onSubmit={handleSubmit}>
        <h2 className="form-title">Login</h2>
        <Form.Group controlId="login" className="mb-3">
          <Form.Label>Username</Form.Label>
          <Form.Control
            type="text"
            placeholder="Enter Username"
            name="login"
            value={userData.login}
            onChange={handleChange}
          />
        </Form.Group>

        <Form.Group controlId="password" className="mb-3">
          <Form.Label>Password</Form.Label>
          <Form.Control
            type="password"
            placeholder="Enter Password"
            name="password"
            value={userData.password}
            onChange={handleChange}
          />
        </Form.Group>

        {/* <Form.Group controlId="rememberMe" className="mb-3">
          <Form.Label>Stay Logged In?</Form.Label>
          <Form.Control
            type="checkbox"
            name="rememberMe"
            value={userData.rememberMe}
            onChange={handleChange}
          />
        </Form.Group> */}

        <Button variant="primary" type="submit" className="signin-btn">
          Submit
        </Button>

      </Form>
    </div>
  );
}

export default LoginForm;
