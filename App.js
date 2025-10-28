import React from 'react';
import { BrowserRouter as Router, Routes, Route, Link } from 'react-router-dom';
import AdminLogin from './components/AdminLogin';
import StudentLogin from './components/StudentLogin';
import AdminDashboard from './components/AdminDashboard';
import StudentDashboard from './components/StudentDashboard';
import './App.css';

function App() {
  return (
    <Router>
      <div className="navbar">
        <h2>🎓 Sathyabama Student Result Portal - full stack project </h2>
        <nav>
          <Link to="/admin">Admin Login</Link>
          <Link to="/student">Student Login</Link>
        </nav>
      </div>

      <Routes>
        <Route path="/admin" element={<AdminLogin />} />
        <Route path="/student" element={<StudentLogin />} />
        <Route path="/admin/dashboard" element={<AdminDashboard />} />
        <Route path="/student/dashboard" element={<StudentDashboard />} />
      </Routes>
    </Router>
  );
}

export default App;

