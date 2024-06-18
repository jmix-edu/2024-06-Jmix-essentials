package com.company.task_management.app;

import com.company.task_management.entity.Project;
import io.jmix.core.DataManager;
import io.jmix.core.validation.group.UiComponentChecks;
import io.jmix.core.validation.group.UiCrossFieldChecks;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.groups.Default;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Validated(value = {Default.class, UiComponentChecks .class, UiCrossFieldChecks.class})
@Component("tm_ProjectsService")
public class ProjectsService {


    @Autowired
    private DataManager dataManager;

    public void save(@NotNull @Valid Project project) {
        dataManager.save(project);
    }
}