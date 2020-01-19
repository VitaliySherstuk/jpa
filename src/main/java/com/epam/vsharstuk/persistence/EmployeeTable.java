package com.epam.vsharstuk.persistence;

import com.epam.vsharstuk.model.Employee;

public interface EmployeeTable {

    boolean createEmployee(Employee employee);

    Employee findEmployeeById(long id);

    boolean deleteEmployeeById(long id);

    boolean updateEmployeeById(long id, Employee newEmployee);

    boolean assignEmployeeToProjectById(Employee employee, long projectId);



}
