package com.company.task_management.view.projecttasksbrowser;


import com.company.task_management.entity.Project;
import com.company.task_management.entity.Task;
import com.company.task_management.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.model.CollectionLoader;
import io.jmix.flowui.view.*;
import org.springframework.lang.Nullable;

@Route(value = "project-tasks-browser", layout = MainView.class)
@ViewController("tm_ProjectTasksBrowser")
@ViewDescriptor("project-tasks-browser.xml")
@DialogMode(width = "50em")
public class ProjectTasksBrowser extends StandardView {

    @ViewComponent
    private CollectionLoader<Task> tasksDl;

    public void setProject(@Nullable Project project) {
        if (project != null) {
            tasksDl.setParameter("passedId", project.getId());
        } else {
            tasksDl.removeParameter("passedId");
        }
        tasksDl.load();
    }
}