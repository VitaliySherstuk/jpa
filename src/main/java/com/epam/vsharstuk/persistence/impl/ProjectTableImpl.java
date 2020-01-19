package com.epam.vsharstuk.persistence.impl;

import com.epam.vsharstuk.model.Employee;
import com.epam.vsharstuk.model.Project;
import com.epam.vsharstuk.persistence.ProjectTable;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceUnit;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ProjectTableImpl implements ProjectTable {

    private static final Logger LOG = Logger.getLogger(ProjectTableImpl.class);
    private static final String UNABLE_TO_DELETE_PROJECT_DOESNOT_EXIST = "Unable to delete project, project with id = %s doesn't exist";
    private static final String UNABLE_TO_UPDATE_PROJECT_DOESNOT_EXIST = "Unable to update project, project with id = %s doesn't exist";
    private static final String FIELD_WITH_EMPLOYEES_IN_PROJECT_CLASS = "employeeSet";

    @PersistenceUnit
    private EntityManagerFactory entityManagerFactory;

    @Override
    public boolean createProject(Project project) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();
            entityManager.persist(project);
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
    public Project findProjectById(long projectId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        return entityManager.find(Project.class, projectId);
    }

    @Override
    public boolean deleteProjectById(long projectId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        Project projectToRemove = entityManager.find(Project.class, projectId);
        if (projectToRemove == null) {
            entityManager.close();
            LOG.warn(String.format(UNABLE_TO_DELETE_PROJECT_DOESNOT_EXIST, projectId));
            return false;
        }
        try {
            entityTransaction.begin();
            entityManager.remove(projectToRemove);
            entityTransaction.commit();

        } catch (Exception e) {
            LOG.warn(e.toString());
        } finally {
            entityManager.close();
        }

        return true;
    }

    @Override
    public boolean updateProjectById(long projectId, Project newProject) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        Project projectToUpdate = entityManager.find(Project.class, projectId);
        if (projectToUpdate == null) {
            entityManager.close();
            LOG.warn(String.format(UNABLE_TO_UPDATE_PROJECT_DOESNOT_EXIST, projectId));
            return false;
        }
        try {
            entityTransaction.begin();
            updateEachField(projectToUpdate, newProject);
            entityManager.merge(projectToUpdate);
            entityTransaction.commit();
        } catch (Exception e) {
            LOG.warn(e.toString());
        } finally {
            entityManager.close();
        }
        return true;
    }

    private void updateEachField(Project projectToUpdate, Project newProject) {
        projectToUpdate.setProjectTitle(newProject.getProjectTitle());
        projectToUpdate.getEmployeeSet().forEach(employee -> employee.getProjects().remove(projectToUpdate));
        projectToUpdate.setEmployeeSet(newProject.getEmployeeSet());
        projectToUpdate.getEmployeeSet().forEach(employee -> employee.getProjects().add(projectToUpdate));
    }

    @Override
    public List<Project> getProjectWhereEmployeesNotExternal() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Project> criteriaQuery = criteriaBuilder.createQuery(Project.class);
        Root<Project> projectRoot = criteriaQuery.from(Project.class);
        projectRoot.fetch(FIELD_WITH_EMPLOYEES_IN_PROJECT_CLASS);
        criteriaQuery.select(projectRoot);
        List<Project> allProjects = entityManager.createQuery(criteriaQuery).getResultList();
        return allProjects.stream().filter(project -> project.getEmployeeSet()
                .stream().filter(Employee::isExternal).collect(Collectors.toList()).isEmpty())
                .collect(Collectors.toList());
    }
}
