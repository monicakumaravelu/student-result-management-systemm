import React, { useEffect, useState } from 'react';
import axios from 'axios';
import Average from './Average';

function AdminDashboard() {
  const [students, setStudents] = useState([]);
  const [newStudent, setNewStudent] = useState({
    name: '',
    email: '',
    department: '',
    password: ''
  });

  // ✅ Load all students
  useEffect(() => {
    loadStudents();
  }, []);

  const loadStudents = async () => {
    const res = await axios.get('http://localhost:8080/api/student/all');
    setStudents(res.data);
  };

  // ✅ Add Student
  const addStudent = async (e) => {
    e.preventDefault();
    try {
      await axios.post('http://localhost:8080/api/student/add', newStudent);
      alert('✅ Student added successfully!');
      setNewStudent({ name: '', email: '', department: '', password: '' });
      loadStudents();
    } catch (err) {
      alert('⚠️ Error adding student!');
    }
  };

  // ✅ Delete Student
  const deleteStudent = async (id) => {
    if (window.confirm('Are you sure you want to delete this student?')) {
      await axios.delete(`http://localhost:8080/api/student/delete/${id}`);
      alert('🗑️ Student deleted!');
      loadStudents();
    }
  };

  return (
    <div className="container">
      <h3>Admin Dashboard</h3>
      <h4>All Students</h4>

      {/* ✅ Add New Student Form */}
      <form onSubmit={addStudent} style={{ marginBottom: '20px' }}>
        <input
          type="text"
          placeholder="Name"
          value={newStudent.name}
          onChange={(e) => setNewStudent({ ...newStudent, name: e.target.value })}
          required
        />
        <input
          type="email"
          placeholder="Email"
          value={newStudent.email}
          onChange={(e) => setNewStudent({ ...newStudent, email: e.target.value })}
          required
        />
        <input
          type="text"
          placeholder="Department"
          value={newStudent.department}
          onChange={(e) => setNewStudent({ ...newStudent, department: e.target.value })}
          required
        />
        <input
          type="password"
          placeholder="Password"
          value={newStudent.password}
          onChange={(e) => setNewStudent({ ...newStudent, password: e.target.value })}
          required
        />
        <button type="submit">Add Student</button>
      </form>

      {/* ✅ Student Table */}
      <table border="1" width="100%">
        <thead>
          <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Email</th>
            <th>Department</th>
            <th>Average (%)</th>
            <th>Action</th>
          </tr>
        </thead>

        <tbody>
          {students.map((s) => (
            <tr key={s.studentId}>
              <td>{s.studentId}</td>
              <td>{s.name}</td>
              <td>{s.email}</td>
              <td>{s.department}</td>
              <td><Average studentId={s.studentId} /></td>
              <td>
                <button
                  style={{ backgroundColor: 'red' }}
                  onClick={() => deleteStudent(s.studentId)}
                >
                  Delete
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

export default AdminDashboard;


