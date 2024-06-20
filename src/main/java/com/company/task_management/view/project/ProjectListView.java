package com.company.task_management.view.project;

import com.company.task_management.datatype.ProjectLabels;
import com.company.task_management.entity.Project;
import com.company.task_management.entity.User;
import com.company.task_management.view.main.MainView;
import com.company.task_management.view.projecttasksbrowser.ProjectTasksBrowser;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.router.Route;
import io.jmix.core.DataManager;
import io.jmix.core.UnconstrainedDataManager;
import io.jmix.core.security.CurrentAuthentication;
import io.jmix.flowui.DialogWindows;
import io.jmix.flowui.Notifications;
import io.jmix.flowui.component.grid.DataGrid;
import io.jmix.flowui.kit.action.ActionPerformedEvent;
import io.jmix.flowui.kit.component.button.JmixButton;
import io.jmix.flowui.model.CollectionContainer;
import io.jmix.flowui.view.*;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Route(value = "projects", layout = MainView.class)
@ViewController("tm_Project.list")
@ViewDescriptor("project-list-view.xml")
@LookupComponent("projectsDataGrid")
@DialogMode(width = "64em")
public class ProjectListView extends StandardListView<Project> {

    @Autowired
    private DialogWindows dialogWindows;
    @ViewComponent
    private DataGrid<Project> projectsDataGrid;
    @Autowired
    private UnconstrainedDataManager unconstrainedDataManager;
    @Autowired
    private CurrentAuthentication currentAuthentication;
    @ViewComponent
    private CollectionContainer<Project> projectsDc;
    @Autowired
    private DataManager dataManager;
    @Autowired
    private Notifications notifications;

    @Subscribe("projectsDataGrid.showTasksDialog")
    public void onProjectsDataGridShowTasksDialog(final ActionPerformedEvent event) {
        DialogWindow<ProjectTasksBrowser> window = dialogWindows.view(this, ProjectTasksBrowser.class)
                .build();

        window.getView().setProject(projectsDataGrid.getSingleSelectedItem());
        window.setResizable(true);
        window.open();
    }

    @Subscribe(id = "dmCreate", subject = "clickListener")
    public void onDmCreateClick(final ClickEvent<JmixButton> event) {
        Project project = unconstrainedDataManager.create(Project.class);
        project.setName("Project " + RandomStringUtils.randomAlphabetic(5));

        User user = (User) currentAuthentication.getUser();
        project.setManager(user);
        // Bean validation is invoked while saving entity instance
        project.setProjectLabels(new ProjectLabels(List.of("task", "enhancement", "bug")));

        Project saved = unconstrainedDataManager.save(project);
        projectsDc.getMutableItems().add(saved);
    }

    @Subscribe
    public void onBeforeShow(final BeforeShowEvent event) {
//        int newProjectsCount = dataManager.loadValue("select count(e) from tm_Project e" +
//                        " where :session_isManager = TRUE and e.status = 'A'", Integer.class)
//                .one();
//        if (newProjectsCount != 0) {
//            notifications.create("New projects", "Projects with NEW status: " + newProjectsCount)
//                    .withPosition(Notification.Position.TOP_END)
//                    .show();
//        }

        int newProjectsCount = dataManager.loadValue("select count(e) from tm_Project e " +
                                "where :session_isManager = TRUE and e.status = @enum(com.company.task_management.entity.ProjectStatus.NEW) " +
                                "and e.manager.id = :current_user_id",
                        Integer.class)
                .one();
        if (newProjectsCount != 0) {
            notifications.create("New projects", "You have projects with NEW status: " + newProjectsCount)
                    .withPosition(Notification.Position.TOP_END)
                    .show();
        }
    }
    
    
}