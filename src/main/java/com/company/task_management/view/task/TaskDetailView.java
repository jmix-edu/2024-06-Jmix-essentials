package com.company.task_management.view.task;

import com.company.task_management.entity.Project;
import com.company.task_management.entity.Task;
import com.company.task_management.view.main.MainView;
import com.vaadin.flow.component.AbstractField;
import com.vaadin.flow.component.combobox.dataview.ComboBoxListDataView;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.component.combobox.JmixComboBox;
import io.jmix.flowui.component.valuepicker.EntityPicker;
import io.jmix.flowui.model.InstanceContainer;
import io.jmix.flowui.view.*;

import java.util.ArrayList;

@Route(value = "tasks/:id", layout = MainView.class)
@ViewController("tm_Task.detail")
@ViewDescriptor("task-detail-view.xml")
@EditedEntityContainer("taskDc")
public class TaskDetailView extends StandardDetailView<Task> {

    @ViewComponent
    private JmixComboBox<String> labelField;

    // Объект для операций над элементами доступных опций (items).
    private ComboBoxListDataView<String> labelsDataView;

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


    @Subscribe
    public void onInit(final InitEvent event) {
        // Добавляем пустой лист, чтобы потом когда будут доступны опции,
        // добавить их в лист.
        labelsDataView = labelField.setItems(new ListDataProvider<>(new ArrayList<>()));
    }

    @Subscribe
    public void onBeforeShow(final BeforeShowEvent event) {
        Project project = getEditedEntity().getProject();
        if (project != null && project.getProjectLabels() != null) {
            labelsDataView.addItems(project.getProjectLabels().getLabels());
        }
    }


}