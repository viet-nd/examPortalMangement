package com.lunatic.examportalbackend.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Getter
@Setter
@ToString
@Entity
@Table(name = "roles")
public class Role implements Serializable {

    @Id
    @Column(name="role_name")
    private String roleName;

    @Column(name="role_description")
    private String roleDescription;
}
