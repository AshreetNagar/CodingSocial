import React, { useState } from "react";
import { Form, Button } from "react-bootstrap";
import axios from "axios";

function LoginForm() {
  const [userData, setUserData] = useState({
    login: "",
    password: "",
  });

  const handleChange = (event) => {
    const { name, value } = event.target;
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
      console.info("==RESPONSE DATA END==")
    } catch (error) {
      console.error(error);
    }
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

        <Button variant="primary" type="submit" className="signin-btn">
          Submit
        </Button>

      </Form>
    </div>
  );
}

export default LoginForm;
