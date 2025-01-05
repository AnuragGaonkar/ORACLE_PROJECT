// src/App.js

import React from 'react';
import EmployeeForm from './EmployeeForm';
import EmployeeList from './EmployeeList';
import './App.css';

function App() {
  return (
    <div className="App">
      <h1>Employee Management System</h1>
      <EmployeeForm />
      <EmployeeList />
    </div>
  );
}

export default App;
