package com.company.task_management.security;

import io.jmix.security.role.annotation.ResourceRole;

@ResourceRole(name = "CombinedProjectManagerRole", code = CombinedProjectManagerRole.CODE, scope = "UI")
public interface CombinedProjectManagerRole extends
        DocumentFullAccessRole,
        TaskFullAccessRole,
        ProjectFullAccessRole,
        TimeEntryFullAccessRole,
        UserReadUpdateRole,
        UiMinimalRole {
    String CODE = "combined-project-manager-role";
}