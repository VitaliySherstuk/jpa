package com.epam.vsharstuk.config;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class FirstNameConfig {

    @Bean(name = "name1")
    public String getName1() {
        return "Jonh";
    }

    @Bean(name = "name2")
    public String getName2() {
        return "Joe";
    }

    @Bean(name = "name3")
    public String getName3() {
        return "Andrew";
    }

    @Bean(name = "name4")
    public String getName4() {
        return "Adam";
    }

    @Bean(name = "name5")
    public String getName5() {
        return "Eva";
    }

    @Bean(name = "name6")
    public String getName6() {
        return "Lisa";
    }

    @Bean(name = "name7")
    public String getName7() {
        return "Kate";
    }

    @Bean(name = "name8")
    public String getName8() {
        return "Philip";
    }

    @Bean(name = "name9")
    public String getName9() {
        return "Michael";
    }

    @Bean(name = "name10")
    public String getName10() {
        return "Alice";
    }

    @Bean(name = "listNames")
    public List<String> getListNames() {
        List<String> list = new ArrayList<>();
        list.add(getName1());
        list.add(getName2());
        list.add(getName3());
        list.add(getName4());
        list.add(getName5());
        list.add(getName6());
        list.add(getName7());
        list.add(getName8());
        list.add(getName9());
        list.add(getName10());
        return list;
    }

}
