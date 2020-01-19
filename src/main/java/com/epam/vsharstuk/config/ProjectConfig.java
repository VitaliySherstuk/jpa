package com.epam.vsharstuk.config;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ProjectConfig {

    @Bean(name = "project1")
    public String getProject1() {
        return "ASCET";
    }

    @Bean(name = "project2")
    public String getProject2() {
        return "Scode";
    }

    @Bean(name = "project3")
    public String getProject3() {
        return "INCA";
    }

    @Bean(name = "project4")
    public String getProject4() {
        return "Bench";
    }

    @Bean(name = "project5")
    public String getProject5() {
        return "Tomson";
    }

    @Bean(name = "project6")
    public String getProject6() {
        return "RD";
    }

    @Bean(name = "projectTitles")
    public List<String> getListNames() {
        List<String> list = new ArrayList<>();
        list.add(getProject1());
        list.add(getProject2());
        list.add(getProject3());
        list.add(getProject4());
        list.add(getProject5());
        list.add(getProject6());
        return list;
    }

}
