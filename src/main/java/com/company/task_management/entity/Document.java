package com.company.task_management.entity;

import io.jmix.core.FileRef;
import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.metamodel.annotation.JmixEntity;
import io.jmix.core.metamodel.annotation.JmixProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;

import java.time.OffsetDateTime;
import java.util.UUID;

@JmixEntity(annotatedPropertiesOnly = true)
@Table(name = "TM_DOCUMENT", indexes = {
        @Index(name = "IDX_TM_DOCUMENT_PROJECT", columnList = "PROJECT_ID")
})
@Entity(name = "tm_Document")
public class Document {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    @JmixProperty
    private UUID id;

    @JoinColumn(name = "PROJECT_ID", nullable = false)
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JmixProperty
    private Project project;

    @Column(name = "FILE_", length = 1024)
    @JmixProperty
    private FileRef file;

    @Column(name = "NOTES")
    @Lob
    @JmixProperty
    private String notes;

    @CreatedBy
    @Column(name = "CREATED_BY")
    @JmixProperty
    private String createdBy;

    @CreatedDate
    @Column(name = "CREATED_DATE")
    @JmixProperty
    private OffsetDateTime createdDate;

    public OffsetDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(OffsetDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public FileRef getFile() {
        return file;
    }

    public void setFile(FileRef file) {
        this.file = file;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}