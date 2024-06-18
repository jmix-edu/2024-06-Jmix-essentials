package com.company.task_management.view.timeentry;

import com.company.task_management.entity.TimeEntry;
import com.company.task_management.entity.User;
import com.company.task_management.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.core.security.CurrentAuthentication;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

@Route(value = "timeEntries/:id", layout = MainView.class)
@ViewController("tm_TimeEntry.detail")
@ViewDescriptor("time-entry-detail-view.xml")
@EditedEntityContainer("timeEntryDc")
public class TimeEntryDetailView extends StandardDetailView<TimeEntry> {

    @Autowired
    private CurrentAuthentication currentAuthentication;

    @Subscribe
    public void onInitEntity(final InitEntityEvent<TimeEntry> event){
        final User user = (User) currentAuthentication.getUser();
        TimeEntry timeEntry = event.getEntity();

        timeEntry.setUser(user);

        timeEntry.setEntryDate(LocalDateTime.now().withHour(12).withMinute(0));
    }

}