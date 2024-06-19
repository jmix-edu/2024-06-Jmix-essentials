package com.company.task_management.security;

import com.company.task_management.entity.Project;
import io.jmix.security.model.EntityAttributePolicyAction;
import io.jmix.security.model.EntityPolicyAction;
import io.jmix.security.role.annotation.EntityAttributePolicy;
import io.jmix.security.role.annotation.EntityPolicy;
import io.jmix.security.role.annotation.ResourceRole;
import io.jmix.securityflowui.role.annotation.MenuPolicy;
import io.jmix.securityflowui.role.annotation.ViewPolicy;

@ResourceRole(name = "ProjectFullAccess", code = ProjectFullAccessRole.CODE, scope = "UI")
public interface ProjectFullAccessRole {
    String CODE = "project-full-access";

    @MenuPolicy(menuIds = "tm_Project.list")
    @ViewPolicy(viewIds = {"tm_Project.list", "tm_Project.detail"})
    void screens();

    @EntityAttributePolicy(entityClass = Project.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = Project.class, actions = EntityPolicyAction.ALL)
    void project();
}