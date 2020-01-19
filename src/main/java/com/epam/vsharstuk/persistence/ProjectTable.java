package com.epam.vsharstuk.persistence;

import com.epam.vsharstuk.model.Project;

import java.util.List;

public interface ProjectTable {

    boolean createProject(Project project);

    Project findProjectById(long projectId);

    boolean deleteProjectById(long projectId);

    boolean updateProjectById(long projectId, Project newProject);

    List<Project> getProjectWhereEmployeesNotExternal();

}
