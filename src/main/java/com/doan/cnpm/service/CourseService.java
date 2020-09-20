package com.doan.cnpm.service;

import com.doan.cnpm.domain.Course;
import com.doan.cnpm.domain.Subject;
import com.doan.cnpm.repositories.CourseRepository;
import com.doan.cnpm.repositories.SubjectRepository;
import com.doan.cnpm.service.dto.CourseDTO;
import com.doan.cnpm.service.dto.SubjectDTO;
import org.springframework.stereotype.Service;

@Service
public class CourseService {
    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public Course CreateCourse(CourseDTO course){
        Course newCourse = new Course();
        newCourse.setIdStudent(course.getIdStudent());
        newCourse.setIdSubject(course.getIdSubject());
        newCourse.setLevel(course.getLevel());
        newCourse.setBasicTuition(course.getBasicTuition());
        newCourse.setIdTutor(course.getIdTutor());
        newCourse.setIdSchedule(course.getIdSchedule());
        courseRepository.save(newCourse);
        return newCourse;
    }

    public Course UpdateCourse(CourseDTO course, Long id){
        Course course1 = courseRepository.findOneById(id);

        course1.setIdStudent(course.getIdStudent());
        course1.setIdSubject(course.getIdSubject());
        course1.setLevel(course.getLevel());
        course1.setBasicTuition(course.getBasicTuition());
        course1.setIdTutor(course.getIdTutor());
        course1.setIdSchedule(course.getIdSchedule());
        courseRepository.save(course1);
        return course1;
    }

    public void DeleteCourse(Long id){
        courseRepository.deleteById(id);
    }
}
