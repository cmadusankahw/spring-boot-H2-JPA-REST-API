package com.zon24x7.technical.induction.chiran.hewawitharana.studentManagement.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "teacher")
public class Teacher implements TableEntity {
    private Long id;
    private String name;
    private Date dob;
    private String address;

    public Teacher(String name, Date dob, String address) {
        this.name = name;
        this.dob = dob;
        this.address = address;
    }

    public Teacher(Long id, String name, Date dob, String address) {
        this.id = id;
        this.name = name;
        this.dob = dob;
        this.address = address;
    }

    public Teacher() {
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

    @Override
    public String toString() {
        return "Teacher [id=" + id + ", name=" + name + ", dob=" + dob + ", address=" + address + "]";
    }
}
