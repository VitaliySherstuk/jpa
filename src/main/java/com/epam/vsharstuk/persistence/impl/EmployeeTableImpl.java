package com.epam.vsharstuk.persistence.impl;

import com.epam.vsharstuk.model.Employee;
import com.epam.vsharstuk.model.Project;
import com.epam.vsharstuk.persistence.EmployeeTable;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceUnit;
import java.util.Set;

@Repository
public class EmployeeTableImpl implements EmployeeTable {

    private static final Logger LOG = Logger.getLogger(EmployeeTableImpl.class);
    private static final String UNABLE_TO_DELETE_EMPLOYEE_DOESNOT_EXIST = "Unable to delete employee, employee with id = %s doesn't exist";
    private static final String UNABLE_TO_UPDATE_EMPLOYEE_DOESNOT_EXIST = "Unable to update employee, employee with id = %s doesn't exist";
    private static final String UNABLE_TO_ADD_PROJECT_TO_EMPLOYEE_PROJECT_DOESNOT_EXIST = "Unable to add project to employee %s, project with id = %s doesn't exist";

    @PersistenceUnit
    private EntityManagerFactory entityManagerFactory;

    @Override
    public boolean createEmployee(Employee employee) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();
            entityManager.persist(employee);
            entityTransaction.commit();
            entityManager.close();
            return true;
        } catch (Exception e) {
            LOG.warn(e.toString());
        } finally {
            entityManager.close();
        }
        return false;
    }

    @Override
    public Employee findEmployeeById(long employeeId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            return entityManager.find(Employee.class, employeeId);
        } finally {
            entityManager.close();
        }

    }

    @Override
    public boolean deleteEmployeeById(long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        Employee employeeToRemove = entityManager.find(Employee.class, id);
        if (employeeToRemove == null) {
            entityManager.close();
            LOG.warn(String.format(UNABLE_TO_DELETE_EMPLOYEE_DOESNOT_EXIST, id));
            return false;
        }
        entityTransaction.begin();
        entityManager.remove(employeeToRemove);
        entityTransaction.commit();
        entityManager.close();
        return true;
    }

    @Override
    public boolean updateEmployeeById(long id, Employee newEmployee) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        Employee employeeToUpdate = entityManager.find(Employee.class, id);
        if (employeeToUpdate == null) {
            entityManager.close();
            LOG.warn(String.format(UNABLE_TO_UPDATE_EMPLOYEE_DOESNOT_EXIST, id));
            return false;
        }
        entityTransaction.begin();
        updateEachField(employeeToUpdate, newEmployee);
        entityManager.merge(employeeToUpdate);
        entityTransaction.commit();
        entityManager.close();
        return true;
    }

    private void updateEachField(Employee employeeToUpdate, Employee newEmployee) {
        employeeToUpdate.setAge(newEmployee.getAge());
        employeeToUpdate.setUnit(newEmployee.getUnit());
        employeeToUpdate.setAddress(newEmployee.getAddress());
        employeeToUpdate.setEmployeePersonalInfo(newEmployee.getEmployeePersonalInfo());
        employeeToUpdate.setExternal(newEmployee.isExternal());
        employeeToUpdate.setFirstName(newEmployee.getFirstName());
        employeeToUpdate.setLastName(newEmployee.getLastName());
        employeeToUpdate.setSex(newEmployee.getSex());
        employeeToUpdate.setProjects(newEmployee.getProjects());
        employeeToUpdate.setEmployeeStatus(newEmployee.getEmployeeStatus());
    }

    @Override
    public boolean assignEmployeeToProjectById(Employee employee, long projectId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        Project project = entityManager.find(Project.class, projectId);
        if (project == null) {
            entityManager.close();
            LOG.warn(String.format(UNABLE_TO_ADD_PROJECT_TO_EMPLOYEE_PROJECT_DOESNOT_EXIST, employee, projectId));
            return false;
        }
        entityTransaction.begin();
        Set<Project> projectSet = employee.getProjects();
        projectSet.add(project);
        employee.setProjects(projectSet);
        entityManager.merge(employee);
        entityTransaction.commit();
        entityManager.close();
        return true;
    }
}
