package com.example.rateUniversityApplication.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.rateUniversityApplication.entity.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer>{
public List<Course> findByName(String name);
}
