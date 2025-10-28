import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

function StudentLogin() {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [message, setMessage] = useState('');
  const navigate = useNavigate();

  const handleLogin = async () => {
    try {
      const response = await axios.post('http://localhost:8080/api/student/login', {
        email,
        password
      });
      if (response.data.status === "success") {
        localStorage.setItem("student", JSON.stringify(response.data.student));
        navigate('/student/dashboard');
      } else {
        setMessage(response.data.message);
      }
    } catch (error) {
      setMessage("❌ Server error");
    }
  };

  return (
    <div className="container">
      <h3>Student Login</h3>
      <input placeholder="Email" value={email} onChange={e => setEmail(e.target.value)} />
      <input placeholder="Password" type="password" value={password} onChange={e => setPassword(e.target.value)} />
      <button onClick={handleLogin}>Login</button>
      <p>{message}</p>
    </div>
  );
}

export default StudentLogin;
