package com.company.task_management.view.task;

import com.company.task_management.entity.Project;
import com.company.task_management.entity.Task;
import com.company.task_management.view.main.MainView;
import com.vaadin.flow.component.AbstractField;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.component.valuepicker.EntityPicker;
import io.jmix.flowui.model.InstanceContainer;
import io.jmix.flowui.view.*;

@Route(value = "tasks/:id", layout = MainView.class)
@ViewController("tm_Task.detail")
@ViewDescriptor("task-detail-view.xml")
@EditedEntityContainer("taskDc")
public class TaskDetailView extends StandardDetailView<Task> {

    @Subscribe("projectField")
    public void onProjectFieldComponentValueChange(final AbstractField.ComponentValueChangeEvent<EntityPicker<Project>, Project> event) {
        if (event.isFromClient()) {
            Project project = event.getValue();
            if (project != null) {
                getEditedEntity().setPriority(project.getDefaultTaskPriority());
            }
        }
    }

//    @Subscribe(id = "taskDc", target = Target.DATA_CONTAINER)
//    public void onTaskDcItemPropertyChange(final InstanceContainer.ItemPropertyChangeEvent<Task> event) {
//        if ("project".equals(event.getProperty())) {
//            Project otherProject = (Project) event.getValue();
//
//            if (otherProject != null) {
//                event.getItem().setPriority(otherProject.getDefaultTaskPriority());
//            }
//        }
//    }


}