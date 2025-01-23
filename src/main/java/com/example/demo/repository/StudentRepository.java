package com.example.demo.repository;

import com.example.demo.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {

    Optional<Student> findStudentByEmail(String email);

    @Query(value = "SELECT * FROM students WHERE first_name = ?1 AND age >= ?2", nativeQuery = true)
    List<Student> findStudentsByFistNameEqualsAndAgeEquals(String firstName, Integer age);

    @Query(value = "SELECT * FROM students WHERE first_name = :firstName AND age >= :age", nativeQuery = true)
    List<Student> findStudentsByFistNameEqualsAndAgeEqualsParam(
            @Param("firstName") String firstName,
            @Param("age") Integer age);

    @Transactional
    @Modifying
    @Query("DELETE FROM students u WHERE u.id = ?1")
    int deleteStudentById(Long id);

}
