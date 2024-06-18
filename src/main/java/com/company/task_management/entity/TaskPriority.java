package com.company.task_management.entity;

import io.jmix.core.metamodel.datatype.EnumClass;

import org.springframework.lang.Nullable;


public enum TaskPriority implements EnumClass<Integer> {

    LOW(10),
    MEDIUM(20),
    HIGH(30);

    private final Integer id;

    TaskPriority(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    @Nullable
    public static TaskPriority fromId(Integer id) {
        for (TaskPriority at : TaskPriority.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}