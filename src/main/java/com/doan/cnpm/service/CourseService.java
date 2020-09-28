package com.doan.cnpm.service;

import com.doan.cnpm.domain.*;
import com.doan.cnpm.repositories.CourseRepository;
import com.doan.cnpm.repositories.NeedRepository;
import com.doan.cnpm.repositories.UserRepository;
import com.doan.cnpm.service.dto.CourseDTO;
import com.doan.cnpm.service.dto.TutorDetailsDTO;
import com.doan.cnpm.service.exception.JoinCourseDeniedException;
import com.doan.cnpm.service.exception.SubjectNotFoundException;
import com.doan.cnpm.service.exception.TutorNotFoundException;
import com.doan.cnpm.service.exception.UserNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CourseService {
    private final CourseRepository courseRepository;

    private final NeedRepository needRepository;

    private final UserRepository userRepository;

    public CourseService(CourseRepository courseRepository, NeedRepository needRepository, UserRepository userRepository) {
        this.courseRepository = courseRepository;
        this.needRepository = needRepository;
        this.userRepository = userRepository;
    }

    public Course CreateCourse(Long idNeed, Long idUser){

        Need need = needRepository.findOneById(idNeed);
        Course newCourse = new Course();
        newCourse.setIdNeed(idNeed);
        if(need.getType().equals("OPEN_NEED")) {
            newCourse.setIdTutor(idUser);
            User user = userRepository.getOne(need.getIdUser());
            if(user == null){
                user = new User();
                user.setCourses(new HashSet<>());
                try {
                    throw  new UserNotFoundException();
                } catch (UserNotFoundException e) {
                    e.printStackTrace();
                }
            }
            newCourse.addStudent(user);
        }
        else if(need.getType().equals("OPEN_COURSE")){
            User user = userRepository.getOne(idUser);
            if(user == null){
                user = new User();
                user.setCourses(new HashSet<>());
                try {
                    throw  new UserNotFoundException();
                } catch (UserNotFoundException e) {
                    e.printStackTrace();
                }
            }
            newCourse.setIdTutor(need.getIdUser());
            newCourse.addStudent(user);

        }
        courseRepository.save(newCourse);
        return newCourse;
    }

    public Course UpdateCourse(CourseDTO course, Long id){
        Course course1 = courseRepository.findOneById(id);
        User tutor = userRepository.getOne(course.getIdTutor());
        if (tutor.getAuthorities().equals("ROLE_TUTOR")){
            course1.getStudent().clear();
            course1.setIdNeed(course1.getIdNeed());
            course1.setIdTutor(course1.getIdTutor());
            if(course1.getStudent()==null){
                course1.setStudent(new HashSet<>());
            }
            course.getStudent().stream().forEach(idStudent->{
                Optional<User> student = userRepository.findById(Long.parseLong(idStudent));
                if(student.get().getAuthorities().equals("ROLE_STUDENT")){
                   course1.addStudent(student.get());
                }
            });

            courseRepository.save(course1);
        }
        return course1;
    }

    public Course JoinCourse(Long idCourse,Long idStudent)
    {
        Course course1 = courseRepository.findOneById(idCourse);
        boolean check = true;
        User student = userRepository.getOne(idStudent);
        if(course1.getStudent().size()<5 && course1.getStudent().equals(student)){
           course1.addStudent(student);
        }
        else  throw new JoinCourseDeniedException();
        courseRepository.save(course1);
        return course1;
    }

    public Course OutCourse(Long idCourse,Long idStudent){

        Course course1 = courseRepository.findOneById(idCourse);
        course1.getStudent().stream().forEach(hasAlready ->{
            if (hasAlready.getId() != idStudent) return;
            User student = userRepository.getOne(idStudent);
            course1.removeStudent(student);
        });
        courseRepository.save(course1);
        return course1;
    }


    public String DeleteCourse(Long id){
        Course course = courseRepository.findOneById(id);
        if(course != null){
            course.removeStudent();
            courseRepository.deleteById(id);
            return "Delete success course with id "+ id +" !";
        }
        return "Delete fail !";
    }

    public List<CourseDTO> getAllCourse(){
        List<CourseDTO> data = new ArrayList<>();
        List<Course> courses = courseRepository.findAll();
        for (Course course : courses) {
            CourseDTO course1 = new CourseDTO();
            course1.setId(course.getId());
            course1.setIdNeed(course.getIdNeed());
            course1.setIdTutor(course.getIdTutor());
            course1.setStudent(course.getStudent().stream().map(User::getUsername).collect(Collectors.toSet()));
            data.add(course1);
        }
        return data;
    }

    public CourseDTO getCourseDetails(Long id)throws TutorNotFoundException{
        CourseDTO data = new CourseDTO();
        Course course = courseRepository.findOneById(id);
        if(course ==null)
        {
            throw new TutorNotFoundException();
        }
        data.setId(course.getId());
        data.setIdTutor(course.getIdTutor());
        data.setIdNeed(course.getIdNeed());
        data.setStudent(course.getStudent().stream().map(User::getUsername).collect(Collectors.toSet()));
        return  data;
    }

}
