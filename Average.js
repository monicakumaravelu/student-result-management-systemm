import React, { useEffect, useState } from "react";
import axios from "axios";

function Average({ studentId }) {
  const [average, setAverage] = useState(null);

  useEffect(() => {
    axios
      .get(`http://localhost:8080/api/student/average/${studentId}`)
      .then((res) => setAverage(res.data.toFixed(2)))
      .catch(() => setAverage("N/A"));
  }, [studentId]);

  return <span>{average ? `${average}%` : "Loading..."}</span>;
}

export default Average;
