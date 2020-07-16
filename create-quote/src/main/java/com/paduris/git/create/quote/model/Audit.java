package com.paduris.git.create.quote.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDate;

/**
 * @author paduris
 * Audit abstract class
 */
@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(
        value = {"createdOn", "updatedOn"}, allowGetters = true
)
public abstract class Audit {

    @UpdateTimestamp
    @Column(name = "updated_on")
    @LastModifiedDate
    private LocalDate updatedOn;

    @CreationTimestamp
    @Column(name = "created_on", nullable = false, updatable = false)
    private LocalDate createdOn;
}