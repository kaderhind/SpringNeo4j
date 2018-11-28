package com.guidezmoi.repository;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.repository.CrudRepository;

import com.guidezmoi.entity.Student;

public interface StudentRepository extends CrudRepository<Student, String> {
   @Query("Match (student:Student) Where student.name = {0} return student")	
   Student getStudentByName(String name);
   @Query("match (student:Student)-[:SENIOR]-> seniorStudents where student.name= {0} return seniorStudents")
   Iterable<Student> getSeniorsByStudentName(String name);
}
