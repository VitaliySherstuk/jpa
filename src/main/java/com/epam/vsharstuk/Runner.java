package com.epam.vsharstuk;

import com.epam.vsharstuk.service.DispathcerService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;

public class Runner {

    private final static RowMapper<String> rowMapperEmployee = (resultSet, i) -> {
        String row = "id: %s; fisrt_name: %s; last_name: %s; external_employee: %s; age: %s; sex: %s; employee_status: %s; " +
                "country: %s; state: %s; city: %s; " +
                "street: %s; house: %s; flat: %s; ";

        return String.format(row, resultSet.getInt("id"), resultSet.getString("fisrt_name"),
                resultSet.getString("last_name"), resultSet.getString("external_employee"),
                resultSet.getString("age"), resultSet.getString("sex"), resultSet.getString("employee_status"),
                resultSet.getString("country"), resultSet.getString("state"), resultSet.getString("city"),
                resultSet.getString("street"), resultSet.getString("house"), resultSet.getString("flat"));
    };


    private final static RowMapper<String> rowMapperProject = (resultSet, i) -> {
        String row = "id: %s; projectTitle: %s;";
        return String.format(row, resultSet.getInt(1), resultSet.getString(2));
    };

    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext("com.epam.vsharstuk");
        DispathcerService dispathcerService = applicationContext.getBean(DispathcerService.class);
        dispathcerService.run();

        //for testing
        StringBuilder builder = new StringBuilder();
        JdbcTemplate jdbcTemplate = applicationContext.getBean(JdbcTemplate.class);
        String sqlEmployee = "SELECT * FROM employee LIMIT 1";
        List<String> queryEmployee = jdbcTemplate.query(sqlEmployee, rowMapperEmployee);
        System.out.println("RESULT EMPLOYEE TABLE: " + queryEmployee);

        String sqlProject = "SELECT * FROM project LIMIT 1";
        List<String> queryProject = jdbcTemplate.query(sqlProject, rowMapperProject);
        System.out.println("RESULT PROJECT TABLE: " + queryProject);
    }
}
