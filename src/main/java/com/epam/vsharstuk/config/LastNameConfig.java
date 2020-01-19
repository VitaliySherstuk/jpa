package com.epam.vsharstuk.config;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class LastNameConfig {

    @Bean(name = "surname1")
    public String getSurName1() {
        return "Smith";
    }

    @Bean(name = "surname2")
    public String getSurName2() {
        return "Doe";
    }

    @Bean(name = "surname3")
    public String getSurName3() {
        return "Brown";
    }

    @Bean(name = "surname4")
    public String getSurName4() {
        return "Red";
    }

    @Bean(name = "surname5")
    public String getSurName5() {
        return "Black";
    }

    @Bean(name = "surname6")
    public String getSurName6() {
        return "White";
    }

    @Bean(name = "surname7")
    public String getSurName7() {
        return "Prudent";
    }

    @Bean(name = "surname8")
    public String getSurName8() {
        return "Unfair";
    }

    @Bean(name = "surname9")
    public String getSurName9() {
        return "Stupid";
    }

    @Bean(name = "surname10")
    public String getSurName10() {
        return "Developer";
    }

    @Bean(name = "listLastNames")
    public List<String> getListNames() {
        List<String> list = new ArrayList<>();
        list.add(getSurName1());
        list.add(getSurName2());
        list.add(getSurName3());
        list.add(getSurName4());
        list.add(getSurName5());
        list.add(getSurName6());
        list.add(getSurName7());
        list.add(getSurName8());
        list.add(getSurName9());
        list.add(getSurName10());
        return list;
    }

}
