package com.epam.vsharstuk.config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class BirhrdayConfig implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Bean(name = "birthday1")
    public Date getBirthday1() {
        return new Date(132456789123L);
    }

    @Bean(name = "birthday2")
    public Date getBirthday2() {
        return new Date(432456789123L);
    }

    @Bean(name = "birthday3")
    public Date getBirthday3() {
        return new Date(532456789123L);
    }

    @Bean(name = "birthday4")
    public Date getBirthday4() {
        return new Date(632456789123L);
    }

    @Bean(name = "birthday5")
    public Date getBirthday5() {
        return new Date(732456789123L);
    }

    @Bean(name = "birthday6")
    public Date getBirthday6() {
        return new Date(832456789123L);
    }

    @Bean(name = "birthday7")
    public Date getBirthday7() {
        return new Date(932456789123L);
    }

    @Bean(name = "birthday8")
    public Date getBirthday8() {
        return new Date(752456789123L);
    }

    @Bean(name = "birthday9")
    public Date getBirthday9() {
        return new Date(862456789123L);
    }

    @Bean(name = "birthday10")
    public Date getBirthday10() {
        return new Date(182456789123L);
    }

    @Bean(name = "listBirthDays")
    public List<Date> getListNames() {
        List<Date> list = new ArrayList<>();
        list.add(applicationContext.getBean("birthday1", Date.class));
        list.add(applicationContext.getBean("birthday2", Date.class));
        list.add(applicationContext.getBean("birthday3", Date.class));
        list.add(applicationContext.getBean("birthday4", Date.class));
        list.add(applicationContext.getBean("birthday5", Date.class));
        list.add(applicationContext.getBean("birthday6", Date.class));
        list.add(applicationContext.getBean("birthday7", Date.class));
        list.add(applicationContext.getBean("birthday8", Date.class));
        list.add(applicationContext.getBean("birthday9", Date.class));
        list.add(applicationContext.getBean("birthday10", Date.class));

        return list;
    }

}
