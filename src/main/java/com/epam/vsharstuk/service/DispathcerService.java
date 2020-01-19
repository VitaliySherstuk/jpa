package com.epam.vsharstuk.service;

import com.epam.vsharstuk.model.Employee;
import com.epam.vsharstuk.model.EmployeeStatus;
import com.epam.vsharstuk.model.Project;
import com.epam.vsharstuk.model.Unit;
import com.epam.vsharstuk.persistence.EmployeeTable;
import com.epam.vsharstuk.persistence.ProjectTable;
import com.epam.vsharstuk.persistence.UnitTable;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DispathcerService {

    private static final Logger LOG = Logger.getLogger(DispathcerService.class);
    private static final String EMPLOYEE_WAS_ASSIGNED_TO_PROJECT = "employee %s was assigned to Project %s result ==  %s";
    private static final String EMPLOYEE_WAS_ADDED_TO_UNIT = "employee %s was added to unit %s result ==%s";
    private static final String UNIT_WAS_UPDATED = "unit %s was updated result == %s";
    private static final String PROJECT_WAS_UPDATED = "project %s was updated result == %s";

    @Autowired
    private EmployeeTable employeeTable;

    @Autowired
    private UnitTable unitTable;

    @Autowired
    private ProjectTable projectTable;

    @Autowired
    private EmployeeGeneratorService employeeGenerator;

    @Autowired
    private ProjectGeneratorService projectGeneratorService;

    @Autowired
    private UnitGeneratorService unitGeneratorService;

    public void run() {
        initializeTables();
        showEmployeeApi();
        showUnitApi();
        showProjectApi();
    }

    private void initializeTables() {
        addToEmployeeTableNextQtyEmployees(20);
        addToProjectTableNextQtyUnits(10);
        addToUnitTableNextQtyUnits(10);

    }

    private void addToUnitTableNextQtyUnits(int i) {
        while (i > 0) {
            LOG.info(String.format("unit added &s", unitTable.createUnit(unitGeneratorService.createUnit())));
            i--;
        }
    }

    private void addToProjectTableNextQtyUnits(int i) {
        while (i > 0) {
            LOG.info(String.format("project added %s", projectTable.createProject(projectGeneratorService.createProject())));
            i--;
        }
    }

    private void addToEmployeeTableNextQtyEmployees(int i) {
        while (i > 0) {
            Employee employee = employeeGenerator.createEmployee();
            employeeTable.createEmployee(employee);
            LOG.info("employee added: " + employee);
            i--;
        }
    }

    private void showEmployeeApi() {
        LOG.info(String.format("employee with id = %s deleted %s", 1, employeeTable.deleteEmployeeById(1)));
        Employee employeeId2 = employeeTable.findEmployeeById(2);
        employeeId2.setFirstName("JUST NAME");
        Employee employee = employeeTable.findEmployeeById(9);
        employee.setEmployeeStatus(EmployeeStatus.PRODUCTION);
        employeeTable.updateEmployeeById(9, employee);
        LOG.info(String.format("employee with id = %s updated %s", 3, employeeTable.updateEmployeeById(3, employeeId2)));
        LOG.info(String.format(EMPLOYEE_WAS_ASSIGNED_TO_PROJECT, employeeId2, projectTable.findProjectById(4),
                employeeTable.assignEmployeeToProjectById(employeeId2, 4)));
    }

    private void showUnitApi() {
        LOG.info(String.format("unit with id = %s deleted %s", 1, unitTable.deleteUnitById(1)));
        updateUnitWithId("INTERNET OF THINGS", 2, 20, 3);
        updateUnitWithId("UpdatedUnit", 4, 19, 18);
        updateUnitWithId("UpdatedUnit", 4); //delete unit from employee 18, 19
        LOG.info(String.format(EMPLOYEE_WAS_ADDED_TO_UNIT, employeeTable.findEmployeeById(14), unitTable.findUnitById(7),
                unitTable.addEmployeeToUnit(employeeTable.findEmployeeById(14), 7)));
    }

    private void updateUnitWithId(String newUnitTitle, int unitId, Integer... employeeIds) {
        Unit unit = unitTable.findUnitById(unitId);
        unit.setTitle(newUnitTitle);
        List<Employee> employeeList = new ArrayList<>();
        Arrays.asList(employeeIds).forEach(employeeId -> employeeList.add(employeeTable.findEmployeeById(employeeId)));
        unit.setEmployeeList(employeeList);
        LOG.info(String.format(UNIT_WAS_UPDATED, unit, unitTable.updateUnitById(unitId, unit)));
    }

    private void showProjectApi() {
        LOG.info(String.format("project with id = %s deleted %s", 1, projectTable.deleteProjectById(1)));
        updateProjectWithId("AUTOMOTIVE", 2, 10, 11);
        updateProjectWithId("AddedEmployeeToProjectByUpdated", 5, 12, 13);
        updateProjectWithId("DeletedEmployeeToProjectByUpdated", 5); //delete all employee from project with id == 5
        projectTable.getProjectWhereEmployeesNotExternal().forEach(project -> LOG.info(project.toString()));
    }

    private void updateProjectWithId(String newProjectTitle, int projectId, Integer... employeeIds) {
        Project project = projectTable.findProjectById(projectId);
        project.setProjectTitle(newProjectTitle);
        Set<Employee> employeeSet = new HashSet<>();
        Arrays.stream(employeeIds).forEach(employeeId -> employeeSet.add(employeeTable.findEmployeeById(employeeId)));
        project.setEmployeeSet(employeeSet);
        LOG.info(String.format(PROJECT_WAS_UPDATED, project, projectTable.updateProjectById(projectId, project)));
    }

}
