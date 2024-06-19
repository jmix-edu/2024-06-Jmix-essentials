package com.company.task_management.security;

import com.company.task_management.entity.Document;
import io.jmix.security.model.EntityAttributePolicyAction;
import io.jmix.security.model.EntityPolicyAction;
import io.jmix.security.role.annotation.EntityAttributePolicy;
import io.jmix.security.role.annotation.EntityPolicy;
import io.jmix.security.role.annotation.ResourceRole;
import io.jmix.securityflowui.role.annotation.MenuPolicy;
import io.jmix.securityflowui.role.annotation.ViewPolicy;

@ResourceRole(name = "DocumentFullAccess", code = DocumentFullAccessRole.CODE, scope = "UI")
public interface DocumentFullAccessRole {
    String CODE = "document-full-access";

    @EntityAttributePolicy(entityClass = Document.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = Document.class, actions = EntityPolicyAction.ALL)
    void document();

    @MenuPolicy(menuIds = "tm_Document.list")
    @ViewPolicy(viewIds = {"tm_Document.list", "tm_Document.detail"})
    void screens();
}