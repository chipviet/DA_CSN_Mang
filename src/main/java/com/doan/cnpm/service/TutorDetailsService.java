package com.doan.cnpm.service;

import com.doan.cnpm.domain.Subject;
import com.doan.cnpm.domain.TutorDetails;
import com.doan.cnpm.domain.User;
import com.doan.cnpm.repositories.SubjectRepository;
import com.doan.cnpm.repositories.TutorDetailsRepository;
import com.doan.cnpm.repositories.UserRepository;
import com.doan.cnpm.service.dto.TutorDetailsDTO;
import com.doan.cnpm.service.exception.SubjectNotFoundException;
import com.doan.cnpm.service.exception.TutorNotFoundException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TutorDetailsService {

    private final TutorDetailsRepository tutorDetailsRepository;

    private final UserRepository userRepository;

    private final SubjectRepository subjectRepository ;

//    public TutorDetailsService(TutorDetailsRepository tutorDetailsRepository, UserRepository userRepository, SubjectRepository subjectRepository) {
//        this.tutorDetailsRepository = tutorDetailsRepository;
//        this.userRepository = userRepository;
//        this.subjectRepository = subjectRepository;
//    }

    public TutorDetailsService(TutorDetailsRepository tutorDetailsRepository, UserRepository userRepository, SubjectRepository subjectRepository)
    {
        this.tutorDetailsRepository = tutorDetailsRepository;
        this.userRepository = userRepository;
        this.subjectRepository = subjectRepository;
    }

    public TutorDetails CreateTutorDetails(TutorDetailsDTO tutor, String username)
    {
        System.out.println("vao day");
        System.out.println(username);
        //User user = userRepository.findOneByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found with username:" + username));
        TutorDetails newTutor = new TutorDetails();
        newTutor.setEfficency(tutor.getEfficency());
        newTutor.setLiteracy(tutor.getLiteracy());
        newTutor.setUsername(username);

        System.out.println("toi day");

        newTutor.setSubject(new HashSet<>());
        System.out.println("toi set subject");
        tutor.getSubject().stream().forEach(idSubject ->{
            Subject subject = subjectRepository.findOneById((Long.parseLong(idSubject)));
            System.out.println("subject");
            System.out.println(subject);
            if(subject == null){
                subject = new Subject();
                subject.setTutorDetails(new HashSet<>());
                try {
                    throw  new SubjectNotFoundException("Not found Subject with name: "+idSubject);
                } catch (SubjectNotFoundException e) {
                    e.printStackTrace();
                }
            }

            newTutor.addSubject(subject);
        });
        System.out.println("new TUtour");
        System.out.println(newTutor.getSubject());
        tutorDetailsRepository.save(newTutor);
//        TutorDetailsDTO tutorDTO = new TutorDetailsDTO();
//        tutorDTO.setEfficency(newTutor.getEfficency());
//        tutorDTO.setLiteracy(newTutor.getLiteracy());
//        tutorDTO.setSubject(newTutor.getSubject().stream().map(Subject::getNameSubject).collect(Collectors.toSet()));

        return newTutor;
    }
    public TutorDetails UpdateTutorDetails(TutorDetailsDTO tutor,String username){
        TutorDetails Tutor = tutorDetailsRepository.findOneByUsername(username);

        System.out.println("Tutor");
        System.out.println(Tutor);
        Tutor.setEfficency(tutor.getEfficency());
        Tutor.setLiteracy(tutor.getLiteracy());
        tutorDetailsRepository.save(Tutor);

        return Tutor;
    }

    public void DeleteTutorDetails(String username){
        tutorDetailsRepository.deleteByUsername(username);
    }


    public List<TutorDetailsDTO> getAllTutorDetails(){
        List<TutorDetailsDTO> data = new ArrayList<>();
        List<TutorDetails> tutors = tutorDetailsRepository.findAll();
        for (TutorDetails tutorDetail : tutors) {
            TutorDetailsDTO tutor = new TutorDetailsDTO();
            tutor.setId(tutorDetail.getId());
            tutor.setUsername(tutorDetail.getUsername());
            tutor.setLiteracy(tutorDetail.getLiteracy());
            tutor.setEfficency(tutorDetail.getEfficency());
            tutor.setSubject(tutorDetail.getSubject().stream().map(Subject::getNameSubject).collect(Collectors.toSet()));
            data.add(tutor);

        }
        return data;
    }

    public TutorDetailsDTO getTutorDetails(String username)throws TutorNotFoundException{
        TutorDetailsDTO data = new TutorDetailsDTO();
        TutorDetails tutor = tutorDetailsRepository.findOneByUsername(username);
        if(tutor ==null)
        {
            throw new TutorNotFoundException();
        }
        data.setId(tutor.getId());
        data.setUsername(tutor.getUsername());
        data.setLiteracy(tutor.getLiteracy());
        data.setEfficency(tutor.getEfficency());
        data.setSubject(tutor.getSubject().stream().map(Subject::getNameSubject).collect(Collectors.toSet()));
        return  data;
    }
}
