package com.epam.vsharstuk.config;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UnitConfig {

    @Bean(name = "unit1")
    public String getUnit1() {
        return "Java";
    }

    @Bean(name = "unit2")
    public String getUnit2() {
        return "C#";
    }

    @Bean(name = "unit3")
    public String getUnit3() {
        return "C";
    }

    @Bean(name = "unit4")
    public String getUnit4() {
        return "PYTHON";
    }

    @Bean(name = "unit5")
    public String getUnit5() {
        return "Kotlin";
    }

    @Bean(name = "unit6")
    public String getUnit6() {
        return "SQL";
    }

    @Bean(name = "unit7")
    public String getUnit7() {
        return "Pascal";
    }

    @Bean(name = "unit8")
    public String getUnit8() {
        return "Deplhi";
    }

    @Bean(name = "unit9")
    public String getUnit9() {
        return "NodeJs";
    }

    @Bean(name = "unit10")
    public String getUnit10() {
        return "PHP";
    }


    @Bean(name = "unitTitles")
    public List<String> getListNames() {
        List<String> list = new ArrayList<>();
        list.add(getUnit1());
        list.add(getUnit2());
        list.add(getUnit3());
        list.add(getUnit4());
        list.add(getUnit5());
        list.add(getUnit6());
        list.add(getUnit7());
        list.add(getUnit8());
        list.add(getUnit9());
        list.add(getUnit10());
        return list;
    }

}
