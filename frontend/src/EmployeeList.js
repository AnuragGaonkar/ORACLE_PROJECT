// In your React component, you can fetch the employees like this:

import React, { useState, useEffect } from "react";

const EmployeeList = () => {
    const [employees, setEmployees] = useState([]);

    useEffect(() => {
        // Fetch the list of employees when the component mounts
        fetch("http://localhost:8080/api/employee")
            .then((response) => response.json())
            .then((data) => setEmployees(data))
            .catch((error) => console.error("Error fetching employees:", error));
    }, []);

    return (
        <div>
            <h2>Employee List</h2>
            <ul>
                {employees.map((employee) => (
                    <li key={employee.email}>
                        {employee.firstName} {employee.lastName} ({employee.email})
                    </li>
                ))}
            </ul>
        </div>
    );
};

export default EmployeeList;
