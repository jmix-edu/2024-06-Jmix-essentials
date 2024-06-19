package com.company.task_management.security;

import com.company.task_management.entity.TimeEntry;
import io.jmix.security.model.EntityAttributePolicyAction;
import io.jmix.security.model.EntityPolicyAction;
import io.jmix.security.role.annotation.EntityAttributePolicy;
import io.jmix.security.role.annotation.EntityPolicy;
import io.jmix.security.role.annotation.ResourceRole;
import io.jmix.securityflowui.role.annotation.MenuPolicy;
import io.jmix.securityflowui.role.annotation.ViewPolicy;

@ResourceRole(name = "TimeEntryFullAccess", code = TimeEntryFullAccessRole.CODE, scope = "UI")
public interface TimeEntryFullAccessRole {
    String CODE = "time-entry-full-access";

    @MenuPolicy(menuIds = "tm_TimeEntry.list")
    @ViewPolicy(viewIds = {"tm_TimeEntry.list", "tm_TimeEntry.detail"})
    void screens();

    @EntityAttributePolicy(entityClass = TimeEntry.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = TimeEntry.class, actions = EntityPolicyAction.ALL)
    void timeEntry();
}