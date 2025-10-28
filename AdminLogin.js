import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

function AdminLogin() {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [message, setMessage] = useState('');
  const navigate = useNavigate();

  const handleLogin = async () => {
    try {
      const response = await axios.post('http://localhost:8080/api/admin/login', {
        username,
        password
      });
      if (response.data.includes("✅")) {
        navigate('/admin/dashboard');
      } else {
        setMessage(response.data);
      }
    } catch (error) {
      setMessage("❌ Server error");
    }
  };

  return (
    <div className="container">
      <h3>Admin Login</h3>
      <input placeholder="Username" value={username} onChange={e => setUsername(e.target.value)} />
      <input placeholder="Password" type="password" value={password} onChange={e => setPassword(e.target.value)} />
      <button onClick={handleLogin}>Login</button>
      <p>{message}</p>
    </div>
  );
}

export default AdminLogin;
