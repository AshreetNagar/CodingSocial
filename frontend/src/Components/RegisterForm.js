import React, { useState } from "react";
import { Form, Button } from "react-bootstrap";
import axios from "axios";

function RegisterForm() {
  const [userData, setUserData] = useState({
    login: "",
    password: "",
    email: "",
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
        `http://localhost:8080/register`,
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
        <h2 className="form-title">Register</h2>
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

        <Form.Group controlId="email" className="mb-3">
          <Form.Label>Email</Form.Label>
          <Form.Control
            type="email"
            placeholder="Enter Email"
            name="email"
            value={userData.email}
            onChange={handleChange}
          />
        </Form.Group>

        <Button variant="primary" type="submit" className="signup-btn">
          Submit
        </Button>

      </Form>
    </div>
  );
}

export default RegisterForm;