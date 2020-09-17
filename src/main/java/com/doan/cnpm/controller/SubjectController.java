package com.doan.cnpm.controller;

import com.doan.cnpm.domain.Subject;
import com.doan.cnpm.domain.TutorDetails;
import com.doan.cnpm.repositories.SubjectRepository;
import com.doan.cnpm.service.SubjectService;
import com.doan.cnpm.service.dto.SubjectDTO;
import com.doan.cnpm.service.dto.TutorDetailsDTO;
import com.doan.cnpm.service.exception.TutorNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/api/edu")
@Transactional
public class SubjectController {
    private final Logger log = LoggerFactory.getLogger(SubjectController.class);
    private final SubjectRepository subjectRepository;
    private SubjectService subjectService;

    public SubjectController(SubjectRepository subjectRepository, SubjectService subjectService) {
        this.subjectRepository = subjectRepository;
        this.subjectService = subjectService;
    }
    
    @GetMapping(value="/v1/subject")
    public List<Subject> getALlSubject(){
        return subjectRepository.findAll();
    }

    @GetMapping("v1/subject/details")
    public ResponseEntity<Subject> getTutorDetails (HttpServletRequest request) {

        Long id = Long.parseLong( request.getHeader("id"));

        Subject data = subjectRepository.findOneById(id);

        return new ResponseEntity<>(data,HttpStatus.OK);
    }

    @PostMapping("v1/subject/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void createSubject (@RequestBody SubjectDTO subject, HttpServletRequest request){
        subjectService.CreateSubject(subject);
    }

    @PutMapping("v1/subject/update")
    @ResponseStatus(HttpStatus.UPGRADE_REQUIRED)
    public void updateSubject (@RequestBody SubjectDTO tutor, HttpServletRequest request)  {
        Long id = Long.parseLong( request.getHeader("id"));
        subjectService.UpdateSubject(tutor, id);
    }

    @DeleteMapping("v1/subject/delete")
    public void deleteSubject(HttpServletRequest request)
    {
        Long id = Long.parseLong( request.getHeader("id"));
        subjectService.DeleteSubject(id);
    }
}
