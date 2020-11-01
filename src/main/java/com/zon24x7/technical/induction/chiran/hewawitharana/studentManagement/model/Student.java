package com.zon24x7.technical.induction.chiran.hewawitharana.studentManagement.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "student")
public class Student {
    private Long id;
    private String name;
    private Date dob;
    private String address;

    public Student(String name, Date dob, String address) {
        this.name = name;
        this.dob = dob;
        this.address = address;
    }

    public Student() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(long Id) {
        this.id = Id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
