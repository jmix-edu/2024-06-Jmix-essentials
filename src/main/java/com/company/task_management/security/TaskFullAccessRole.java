package com.company.task_management.security;

import com.company.task_management.entity.Task;
import io.jmix.security.model.EntityAttributePolicyAction;
import io.jmix.security.model.EntityPolicyAction;
import io.jmix.security.role.annotation.EntityAttributePolicy;
import io.jmix.security.role.annotation.EntityPolicy;
import io.jmix.security.role.annotation.ResourceRole;
import io.jmix.securityflowui.role.annotation.MenuPolicy;
import io.jmix.securityflowui.role.annotation.ViewPolicy;

@ResourceRole(name = "TaskFullAccess", code = TaskFullAccessRole.CODE, scope = "UI")
public interface TaskFullAccessRole {
    String CODE = "task-full-access";

    @MenuPolicy(menuIds = "tm_Task.list")
    @ViewPolicy(viewIds = {"tm_Task.list", "tm_Task.detail"})
    void screens();

    @EntityAttributePolicy(entityClass = Task.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = Task.class, actions = EntityPolicyAction.ALL)
    void task();
}