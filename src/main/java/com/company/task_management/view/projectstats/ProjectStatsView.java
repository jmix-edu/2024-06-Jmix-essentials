package com.company.task_management.view.projectstats;


import com.company.task_management.app.ProjectStatsService;
import com.company.task_management.entity.ProjectStats;
import com.company.task_management.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.core.LoadContext;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Route(value = "project-stats-view", layout = MainView.class)
@ViewController("tm_ProjectStatsView")
@ViewDescriptor("project-stats-view.xml")
public class ProjectStatsView extends StandardView {

    @Autowired
    private ProjectStatsService projectStatsService;

    @Install(to = "projectStatsDl", target = Target.DATA_LOADER)
    private List<ProjectStats> projectStatsDlLoadDelegate(final LoadContext<ProjectStats> loadContext) {
        return projectStatsService.fetchProjectStatistics();
    }
}