package com.epam.vsharstuk.persistence;

import com.epam.vsharstuk.model.Employee;
import com.epam.vsharstuk.model.Unit;

public interface UnitTable {

    boolean createUnit(Unit unit);

    Unit findUnitById(long id);

    boolean deleteUnitById(long id);

    boolean updateUnitById(long id, Unit newUnit);

    boolean addEmployeeToUnit(Employee employee, long unitId);


}
