package com.company.task_management.view.task;

import com.company.task_management.app.TasksImportService;
import com.company.task_management.entity.Project;
import com.company.task_management.entity.Task;
import com.company.task_management.view.main.MainView;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.upload.Receiver;
import com.vaadin.flow.router.Route;
import io.jmix.core.DataManager;
import io.jmix.flowui.Notifications;
import io.jmix.flowui.component.upload.FileStorageUploadField;
import io.jmix.flowui.component.upload.receiver.FileTemporaryStorageBuffer;
import io.jmix.flowui.kit.component.button.JmixButton;
import io.jmix.flowui.kit.component.upload.event.FileUploadSucceededEvent;
import io.jmix.flowui.model.CollectionContainer;
import io.jmix.flowui.model.CollectionLoader;
import io.jmix.flowui.model.DataContext;
import io.jmix.flowui.upload.TemporaryStorage;
import io.jmix.flowui.view.*;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.UUID;

@Route(value = "tasks", layout = MainView.class)
@ViewController("tm_Task.list")
@ViewDescriptor("task-list-view.xml")
@LookupComponent("tasksDataGrid")
@DialogMode(width = "64em")
public class TaskListView extends StandardListView<Task> {

    @Autowired
    private TasksImportService tasksImportService;
    @ViewComponent
    private CollectionLoader<Task> tasksDl;
    @Autowired
    private TemporaryStorage temporaryStorage;
    @ViewComponent
    private DataContext dataContext;
    @ViewComponent
    private CollectionContainer<Task> tasksDc;
    @Autowired
    private DataManager dataManager;
    @Autowired
    private Notifications notifications;

    @Subscribe(id = "importTasksBtn", subject = "clickListener")
    public void onImportTasksBtnClick(final ClickEvent<JmixButton> event) {
        int imported = tasksImportService.importTasks();
        tasksDl.load();
    }

    @Subscribe("uploadTasksFromFileBtn")
    public void onUploadTasksFromFileBtnFileUploadSucceeded(final FileUploadSucceededEvent<FileStorageUploadField> event) throws IOException {
        Receiver receiver = event.getReceiver();

        if (receiver instanceof FileTemporaryStorageBuffer storageBuffer) {
            UUID fileId = storageBuffer.getFileData().getFileInfo().getId();
            File file = temporaryStorage.getFile(fileId);
            if (file != null) {
                processFife(file);
                temporaryStorage.deleteFile(fileId);
            }
        }
    }

    private void processFife(File file) throws IOException {
        List<String> lines = FileUtils.readLines(file, StandardCharsets.UTF_8);
        for (String line : lines) {
            Task task = dataContext.create(Task.class);
            dataContext.save();
            task.setName(line);
            task.setProject(tasksImportService.loadDefaultProject());
            tasksDc.getMutableItems().add(task);
        }
    }

    @Subscribe
    public void onInit(final InitEvent event) {
        Task task = dataManager.load(Task.class)
                .all()
                .one();

        Project project = task.getProject();

        notifications.show(project.getName());
    }
    
    
}
