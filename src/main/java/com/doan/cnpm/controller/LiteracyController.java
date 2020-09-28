package com.doan.cnpm.controller;

import com.doan.cnpm.domain.Literacy;
import com.doan.cnpm.domain.Subject;
import com.doan.cnpm.domain.User;
import com.doan.cnpm.repositories.CourseRepository;
import com.doan.cnpm.repositories.LiteracyRepository;
import com.doan.cnpm.service.exception.AccessDeniedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://haimai.ddns.net:9090", maxAge = 3600)
@RestController
@RequestMapping("/api/edu")
@Transactional
public class LiteracyController {

    private final Logger log = LoggerFactory.getLogger(LiteracyController.class);

    private final LiteracyRepository literacyRepository;

    public LiteracyController(LiteracyRepository literacyRepository){
        this.literacyRepository = literacyRepository;
    }

    @GetMapping(value="/v1/literacy")
    public List<Literacy> getALlSubject(HttpServletRequest request){
        return literacyRepository.findAll();
    }
}
