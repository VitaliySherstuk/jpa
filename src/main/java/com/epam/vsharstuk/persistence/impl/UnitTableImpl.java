package com.epam.vsharstuk.persistence.impl;

import com.epam.vsharstuk.model.Employee;
import com.epam.vsharstuk.model.Unit;
import com.epam.vsharstuk.persistence.EmployeeTable;
import com.epam.vsharstuk.persistence.UnitTable;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceUnit;

@Repository
public class UnitTableImpl implements UnitTable {

    private static final Logger LOG = Logger.getLogger(UnitTableImpl.class);
    private static final String UNABLE_TO_DELETE_UNIT_DOESNOT_EXIST = "Unable to delete unit, unit with id = [%d] doesn't exist";
    private static final String UNABLE_TO_UPDATE_UNIT_DOESNOT_EXIST = "Unable to update unit, unit with id = [%d] doesn't exist";
    private static final String UNABLE_TO_ADD_PERSON_TO_UNIT_DOESNOT_EXIST = "Unable to add person = [%s] unit, unit with id = [%d] doesn't exist";

    @PersistenceUnit
    private EntityManagerFactory entityManagerFactory;

    @Autowired
    private EmployeeTable employeeTableDao;

    @Override
    public boolean createUnit(Unit unit) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();
            entityManager.persist(unit);
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
    public Unit findUnitById(long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        return entityManager.find(Unit.class, id);
    }

    @Override
    public boolean deleteUnitById(long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        Unit unitToRemove = entityManager.find(Unit.class, id);
        if (unitToRemove == null) {
            entityManager.close();
            LOG.warn(String.format(UNABLE_TO_DELETE_UNIT_DOESNOT_EXIST, id));
            return false;
        }
        entityTransaction.begin();
        entityManager.remove(unitToRemove);
        entityTransaction.commit();
        entityManager.close();
        return true;
    }

    @Override
    public boolean updateUnitById(long id, Unit newUnit) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {

            Unit unit = entityManager.find(Unit.class, id);
            if (unit == null) {
                LOG.warn(String.format(UNABLE_TO_UPDATE_UNIT_DOESNOT_EXIST, id));
                return false;
            }
            entityTransaction.begin();
            updateEachField(unit, newUnit);
            entityManager.merge(unit);
            entityTransaction.commit();
        } catch (Exception e) {
            entityTransaction.rollback();
            LOG.warn(e.toString());
            return false;
        } finally {
            entityManager.close();
        }

        return true;
    }

    private void updateEachField(Unit unit, Unit newUnit) {
        unit.setTitle(newUnit.getTitle());
        unit.getEmployeeList().forEach(employee -> employee.setUnit(null));
        unit.setEmployeeList(newUnit.getEmployeeList());
        unit.getEmployeeList().forEach(employee -> {
            employee.setUnit(unit);
            employeeTableDao.updateEmployeeById(employee.getId(), employee);
        });

    }

    @Override
    public boolean addEmployeeToUnit(Employee employee, long unitId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {

            Unit unit = entityManager.find(Unit.class, unitId);
            if (unit == null) {
                LOG.warn(String.format(UNABLE_TO_ADD_PERSON_TO_UNIT_DOESNOT_EXIST, employee, unitId));
                return false;
            }
            entityTransaction.begin();
            employee.setUnit(findUnitById(unitId));
            entityManager.merge(employee);
            entityTransaction.commit();
        } catch (Exception e) {
            entityTransaction.rollback();
            LOG.warn(e.toString());
            return false;
        } finally {
            entityManager.close();
        }

        return true;
    }

}
