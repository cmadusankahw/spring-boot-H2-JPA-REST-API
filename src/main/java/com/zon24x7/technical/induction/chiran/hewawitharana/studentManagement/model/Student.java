package com.zon24x7.technical.induction.chiran.hewawitharana.studentManagement.model;

import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "student")
public class Student {
    private  final Long id;
    private  final String name;
    private  final Date dob;
    private final String address;

    public Student(Long id, String name, Date dob, String address) {
        this.id = id;
        this.name = name;
        this.dob = dob;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }

    public Date getDob() {
        return dob;
    }

    public String getAddress() {
        return address;
    }
}
