package com.company.task_management.security;

import io.jmix.security.role.annotation.ResourceRole;

@ResourceRole(name = "CominedDeveloperRole", code = CominedDeveloperRole.CODE, scope = "UI")
public interface CominedDeveloperRole extends
        TaskFullAccessRole,
        TimeEntryFullAccessRole,
        ProjectsReadUpdateRole,
        UiMinimalRole {
    String CODE = "comined-developer-role";
}