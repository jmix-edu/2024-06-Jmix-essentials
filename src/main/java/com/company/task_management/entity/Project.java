package com.company.task_management.entity;

import com.company.task_management.datatype.ProjectLabels;
import com.company.task_management.validaion.ProjectLabelsSize;
import com.company.task_management.validaion.ValidDatesProject;
import io.jmix.core.annotation.DeletedBy;
import io.jmix.core.annotation.DeletedDate;
import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.metamodel.annotation.Composition;
import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@ValidDatesProject
@JmixEntity
@Table(name = "TM_PROJECT", indexes = {
        @Index(name = "IDX_TM_PROJECT_MANAGER", columnList = "MANAGER_ID"),
        @Index(name = "IDX_TM_PROJECT_UNQ_NAME", columnList = "NAME")
})
@Entity(name = "tm_Project")
public class Project {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;

    @JoinColumn(name = "MANAGER_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private User manager;

    @InstanceName
    @Column(name = "NAME", nullable = false)
    @NotNull
    private String name;

    @Column(name = "DESCRIPTION")
    @Lob
    private String description;

    @Column(name = "STATUS")
    private String status;

//    @Enumerated(EnumType.ORDINAL)
//    @Enumerated(EnumType.STRING)
//    private ProjectStatus projectStatus;

//    @JmixProperty
//    @Transient
//    private String transientAttribute;

    @Column(name = "DEFAULT_PROJECT")
    private Boolean defaultProject;

    @Column(name = "DEFAULT_TASK_PRIORITY")
    private Integer defaultTaskPriority;

    @JoinTable(name = "TM_PROJECT_USER_LINK",
            joinColumns = @JoinColumn(name = "PROJECT_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "USER_ID", referencedColumnName = "ID"))
    @ManyToMany
    private List<User> participants;

    @Composition
    @OneToMany(mappedBy = "project")
    private List<Task> tasks;

    //  @PropertyDatatype("projectLabels")
    @Column(name = "PROJECT_LABELS")
//    @Convert(converter = ProjectLabelsConverter.class)
    @ProjectLabelsSize(min = 3, max = 5)
    private ProjectLabels projectLabels;

    @DeletedBy
    @Column(name = "DELETED_BY")
    private String deletedBy;

    @DeletedDate
    @Column(name = "DELETED_DATE")
    private OffsetDateTime deletedDate;

    @Column(name = "START_DATE")
    private LocalDate startDate;

    @Column(name = "END_DATE")
    private LocalDate endDate;

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public OffsetDateTime getDeletedDate() {
        return deletedDate;
    }

    public void setDeletedDate(OffsetDateTime deletedDate) {
        this.deletedDate = deletedDate;
    }

    public String getDeletedBy() {
        return deletedBy;
    }

    public void setDeletedBy(String deletedBy) {
        this.deletedBy = deletedBy;
    }

    public ProjectLabels getProjectLabels() {
        return projectLabels;
    }

    public void setProjectLabels(ProjectLabels projectLabels) {
        this.projectLabels = projectLabels;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }


    public User getManager() {
        return manager;
    }

    public List<User> getParticipants() {
        return participants;
    }

    public void setParticipants(List<User> participants) {
        this.participants = participants;
    }

    public void setManager(User manager) {
        this.manager = manager;
    }

    public TaskPriority getDefaultTaskPriority() {
        return defaultTaskPriority == null ? null : TaskPriority.fromId(defaultTaskPriority);
    }

    public void setDefaultTaskPriority(TaskPriority defaultTaskPriority) {
        this.defaultTaskPriority = defaultTaskPriority == null ? null : defaultTaskPriority.getId();
    }

    public Boolean getDefaultProject() {
        return defaultProject;
    }

    public void setDefaultProject(Boolean defaultProject) {
        this.defaultProject = defaultProject;
    }

    public ProjectStatus getStatus() {
        return status == null ? null : ProjectStatus.fromId(status);
    }

    public void setStatus(ProjectStatus status) {
        this.status = status == null ? null : status.getId();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @PostConstruct
    public void postConstruct() {
        setDefaultTaskPriority(TaskPriority.MEDIUM);
    }
}