package com.company.task_management.security;

import com.company.task_management.entity.Project;
import io.jmix.security.model.EntityAttributePolicyAction;
import io.jmix.security.model.EntityPolicyAction;
import io.jmix.security.role.annotation.EntityAttributePolicy;
import io.jmix.security.role.annotation.EntityPolicy;
import io.jmix.security.role.annotation.ResourceRole;
import io.jmix.securityflowui.role.annotation.MenuPolicy;
import io.jmix.securityflowui.role.annotation.ViewPolicy;

@ResourceRole(name = "ProjectsReadUpdate", code = ProjectsReadUpdateRole.CODE, scope = "UI")
public interface ProjectsReadUpdateRole {
    String CODE = "projects-read-update";

    @EntityAttributePolicy(entityClass = Project.class, attributes = "tasks", action = EntityAttributePolicyAction.MODIFY)
    @EntityAttributePolicy(entityClass = Project.class, attributes = {"id", "manager", "name", "totalEstimatedEfforts", "description", "status", "defaultProject", "defaultTaskPriority", "participants", "projectLabels", "deletedBy", "deletedDate", "startDate", "endDate", "ownerId", "owner"}, action = EntityAttributePolicyAction.VIEW)
    @EntityPolicy(entityClass = Project.class, actions = {EntityPolicyAction.READ, EntityPolicyAction.UPDATE})
    void project();

    @MenuPolicy(menuIds = "tm_Project.list")
    @ViewPolicy(viewIds = {"tm_Project.list", "tm_Project.detail"})
    void screens();
}