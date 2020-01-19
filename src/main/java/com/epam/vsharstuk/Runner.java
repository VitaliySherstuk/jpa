package com.epam.vsharstuk;

import com.epam.vsharstuk.service.DispathcerService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Runner {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext("com.epam.vsharstuk");
        DispathcerService dispathcerService = applicationContext.getBean(DispathcerService.class);
        dispathcerService.run();
    }

}
