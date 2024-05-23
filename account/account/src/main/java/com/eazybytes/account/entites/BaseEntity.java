package com.eazybytes.account.entites;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass
@Setter
@Getter
@EntityListeners(AuditingEntityListener.class)
@ToString
public class BaseEntity {

    @Column(updatable = false)
    private LocalDateTime createdAt;
    @Column(updatable = false)
    private String createdBy;
    @Column(insertable = false)
    private LocalDateTime updatedAt;
    @Column(insertable = false)
    private String updatedBy;
}
