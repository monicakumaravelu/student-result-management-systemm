import React, { useEffect, useState } from 'react';
import axios from 'axios';

function StudentDashboard() {
  const [data, setData] = useState(null);
  const student = JSON.parse(localStorage.getItem("student"));

  useEffect(() => {
    const fetchMarks = async () => {
      const response = await axios.get(`http://localhost:8080/api/student/${student.studentId}/marks`);
      setData(response.data);
    };
    fetchMarks();
  }, [student.studentId]);

  if (!data) return <p>Loading...</p>;

  return (
    <div className="container">
      <h3>Welcome, {data.student.name}</h3>
      <p>Department: {data.student.department}</p>
      <p>Average: {data.average.toFixed(2)}%</p>

      <h4>Marks:</h4>
      <table border="1" width="100%">
        <thead>
          <tr><th>Subject</th><th>Marks</th></tr>
        </thead>
        <tbody>
          {data.marks.map((m) => (
            <tr key={m.id}>
              <td>{m.subject}</td>
              <td>{m.marks}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

export default StudentDashboard;
