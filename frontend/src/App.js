import {
  BrowserRouter as Router,
  Route,
  Routes,
} from "react-router-dom";

import logo from './logo.svg';
import './App.css';
import Login from "./Pages/LoginPage/Login";
import Register from "./Pages/RegisterPage/Register";

function App() {
  return (
    <Router>
      <Routes>
        {/* <Route path="/" exact component={Home} /> */}
        <Route exact path="/login" element={<Login/>} />
        <Route exact path="/register" element={<Register/>  } />
      </Routes>
    </Router>
  );
}

export default App;
