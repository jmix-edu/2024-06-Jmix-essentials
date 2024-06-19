package com.company.task_management.security;

import com.company.task_management.entity.User;
import io.jmix.security.model.EntityAttributePolicyAction;
import io.jmix.security.model.EntityPolicyAction;
import io.jmix.security.role.annotation.EntityAttributePolicy;
import io.jmix.security.role.annotation.EntityPolicy;
import io.jmix.security.role.annotation.ResourceRole;
import io.jmix.securityflowui.role.annotation.MenuPolicy;
import io.jmix.securityflowui.role.annotation.ViewPolicy;

@ResourceRole(name = "UserReadUpdate", code = UserReadUpdateRole.CODE, scope = "UI")
public interface UserReadUpdateRole {
    String CODE = "user-read-update";

    @MenuPolicy(menuIds = "tm_User.list")
    @ViewPolicy(viewIds = {"tm_User.list", "tm_User.detail"})
    void screens();

    @EntityAttributePolicy(entityClass = User.class, attributes = {"email", "lastName", "firstName"}, action = EntityAttributePolicyAction.MODIFY)
    @EntityAttributePolicy(entityClass = User.class, attributes = {"username", "active"}, action = EntityAttributePolicyAction.VIEW)
    @EntityPolicy(entityClass = User.class, actions = {EntityPolicyAction.READ, EntityPolicyAction.UPDATE})
    void user();
}