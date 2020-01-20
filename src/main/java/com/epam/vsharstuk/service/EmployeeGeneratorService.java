package com.epam.vsharstuk.service;

import com.epam.vsharstuk.model.Address;
import com.epam.vsharstuk.model.Employee;
import com.epam.vsharstuk.model.EmployeePersonalInfo;
import com.epam.vsharstuk.model.EmployeeStatus;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
public class EmployeeGeneratorService implements ApplicationContextAware {

    private static final Random RANDOM = new Random();

    private List<String> firstNames;
    private List<String> lastNames;
    private List<Date> birthdays;


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        firstNames = applicationContext.getBean("listNames", List.class);
        lastNames = applicationContext.getBean("listLastNames", List.class);
        birthdays = applicationContext.getBean("listBirthDays", List.class);
    }

    public Employee createEmployee() {
        Employee employee = new Employee();
        employee.setFirstName(firstNames.get(RANDOM.nextInt(firstNames.size())));
        employee.setLastName(lastNames.get(RANDOM.nextInt(firstNames.size())));
        employee.setEmployeePersonalInfo(getPersonalInfo());
        employee.setExternal(RANDOM.nextBoolean());
        employee.setSex(generateSex());
        employee.setAge(RANDOM.nextInt(100));
        employee.setEmployeeStatus(generateEmployeeStatus());
        employee.setAddress(getAddress());
        return employee;
    }

    private char generateSex() {
        if (RANDOM.nextBoolean()) {
            return 'W';
        }
        return 'M';
    }

    private EmployeePersonalInfo getPersonalInfo() {
        EmployeePersonalInfo employeePersonalInfo = new EmployeePersonalInfo();
        employeePersonalInfo.setPassportData(BigDecimal.valueOf(RANDOM.nextInt(50000)));
        employeePersonalInfo.setBirthday(birthdays.get(RANDOM.nextInt(birthdays.size())));
        return employeePersonalInfo;
    }

    private EmployeeStatus generateEmployeeStatus() {
        int number = RANDOM.nextInt(3);
        switch (number) {
            case 0:
                return EmployeeStatus.STUDENT;
            case 1:
                return EmployeeStatus.PRODUCTION;
            case 2:
                return EmployeeStatus.PREPRODUCTION;
            case 3:
                return EmployeeStatus.NONPRODUCTION;
        }
        return EmployeeStatus.STUDENT;
    }

    private Address getAddress() {
        Address address = new Address();
        address.setCountry("Belarus");
        address.setCity("Minsk");
        address.setStreet("Nezavisimosti");
        address.setHouse(186);
        address.setFlat(308);
        return address;
    }
}
