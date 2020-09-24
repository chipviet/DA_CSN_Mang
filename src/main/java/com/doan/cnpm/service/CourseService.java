package com.doan.cnpm.service;

import com.doan.cnpm.domain.Course;
import com.doan.cnpm.repositories.CourseRepository;
import com.doan.cnpm.service.dto.CourseDTO;
import org.springframework.stereotype.Service;

@Service
public class CourseService {
    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public Course CreateCourse(CourseDTO course){
        Course newCourse = new Course();
        newCourse.setIdNeed(newCourse.getIdNeed());
        newCourse.setIdTutor(newCourse.getIdTutor());

        courseRepository.save(newCourse);
        return newCourse;
    }

    public Course UpdateCourse(CourseDTO course, Long id){
        Course course1 = courseRepository.findOneById(id);

        course1.setIdNeed(course1.getIdNeed());
        course1.setIdTutor(course1.getIdTutor());
        courseRepository.save(course1);
        return course1;
    }

    public void DeleteCourse(Long id){
        courseRepository.deleteById(id);
    }
}
