package com.lunatic.examportalbackend.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseModule {

    @Column(name = "createdDate")
    @CreatedDate
    private Date createdDate;

    @Column(name = "modifiedDate")
    @LastModifiedDate
    private Date modifiedDate;
}
