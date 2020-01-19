package com.epam.vsharstuk.service;

import com.epam.vsharstuk.model.Project;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class ProjectGeneratorService implements ApplicationContextAware {

    private static final Random RANDOM = new Random();
    private List<String> projectTitles;


    @Override
    public void setApplicationContext(ApplicationContext applicationContext)  {
        projectTitles = applicationContext.getBean("projectTitles", List.class);
    }


    public Project createProject() {
        Project project = new Project();
        project.setProjectTitle(projectTitles.get(RANDOM.nextInt(projectTitles.size())));
        return project;
    }

}
