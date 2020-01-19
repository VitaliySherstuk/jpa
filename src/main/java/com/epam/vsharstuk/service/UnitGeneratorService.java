package com.epam.vsharstuk.service;

import com.epam.vsharstuk.model.Unit;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class UnitGeneratorService implements ApplicationContextAware {

    private static final Random RANDOM = new Random();
    private List<String> unitTitles;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        unitTitles = applicationContext.getBean("unitTitles", List.class);
    }

    public Unit createUnit() {
        Unit unit = new Unit();
        unit.setTitle(unitTitles.get(RANDOM.nextInt(unitTitles.size())));
        return unit;
    }

}
