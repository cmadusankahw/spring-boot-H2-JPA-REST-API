package com.zon24x7.technical.induction.chiran.hewawitharana.studentManagement.repository;

import com.zon24x7.technical.induction.chiran.hewawitharana.studentManagement.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query("SELECT e FROM Student e WHERE e.name LIKE %?1%")
    public List<Student> findByName(String name);

}
