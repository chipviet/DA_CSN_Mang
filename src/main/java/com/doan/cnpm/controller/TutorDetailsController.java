package com.doan.cnpm.controller;



import com.doan.cnpm.domain.TutorDetails;
import com.doan.cnpm.repositories.TutorDetailsRepository;
import com.doan.cnpm.service.exception.TutorNotFoundException;
import com.doan.cnpm.service.response.TutorDetailsResp;
import org.apache.tomcat.util.http.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/edu")
@Transactional
public class TutorDetailsController {

    private final Logger log = LoggerFactory.getLogger(TutorDetailsController.class);

    private final TutorDetailsRepository tutorDetailsRepository;

    public TutorDetailsController (TutorDetailsRepository tutorDetailsRepository) {
        this.tutorDetailsRepository = tutorDetailsRepository;
    };

    @GetMapping("v1/tutor")
    public List<TutorDetails> getAllTutorDetails() {
        System.out.println("Vaooodaayy");
        return tutorDetailsRepository.findAll();
    }

    @GetMapping("v1/tutor/details")
    public TutorDetails getTutorDetails (HttpServletRequest request) throws TutorNotFoundException {

        String efficency = "indigo";

        TutorDetails data = tutorDetailsRepository.findOneById(efficency);

        return data;
    }

}
