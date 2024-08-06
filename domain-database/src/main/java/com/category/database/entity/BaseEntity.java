package com.category.database.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass
public abstract class BaseEntity {
    @Comment("생성일시")
    @CreationTimestamp
    @Column(updatable = false, columnDefinition = "TIMESTAMP(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6)")
    private LocalDateTime creationDtm;

    @Comment("생성ID")
    @Column(updatable = false, columnDefinition = "VARCHAR(20) NOT NULL DEFAULT 'SYSTEM'")
    private String creationId = "SYSTEM";

    @Comment("수정일시")
    @UpdateTimestamp
    @Column(columnDefinition = "TIMESTAMP(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6)")
    private LocalDateTime modifyDtm;

    @Comment("수정")
    @Column(columnDefinition = "VARCHAR(20) NOT NULL DEFAULT 'SYSTEM'")
    private String modifyId = "SYSTEM";
}

