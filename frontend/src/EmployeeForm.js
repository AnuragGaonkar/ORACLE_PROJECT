// src/EmployeeForm.js

import React, { useState } from 'react';
import axios from 'axios';

const EmployeeForm = () => {
  const [firstName, setFirstName] = useState('');
  const [lastName, setLastName] = useState('');
  const [email, setEmail] = useState('');
  const [message, setMessage] = useState('');

  // Handle form submission
  const handleSubmit = async (e) => {
    e.preventDefault();

    // Prepare the employee data
    const employeeData = {
      firstName,
      lastName,
      email,
    };

    try {
      // Make a POST request to the Spring Boot backend
      const response = await axios.post('http://localhost:8080/api/employee', employeeData);

      // Handle success
      if (response.status === 201) {
        setMessage('Employee created successfully!');
      }
    } catch (error) {
      // Handle error
      console.error('There was an error!', error);
      setMessage('Failed to create employee.');
    }
  };

  return (
    <div>
      <h2>Create New Employee</h2>
      <form onSubmit={handleSubmit}>
        <label>
          First Name:
          <input
            type="text"
            value={firstName}
            onChange={(e) => setFirstName(e.target.value)}
            required
          />
        </label>
        <br />
        <label>
          Last Name:
          <input
            type="text"
            value={lastName}
            onChange={(e) => setLastName(e.target.value)}
            required
          />
        </label>
        <br />
        <label>
          Email:
          <input
            type="email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
            required
          />
        </label>
        <br />
        <button type="submit">Submit</button>
      </form>
      {message && <p>{message}</p>}
    </div>
  );
};

export default EmployeeForm;
