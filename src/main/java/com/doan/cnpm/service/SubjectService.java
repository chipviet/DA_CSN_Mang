package com.doan.cnpm.service;

import com.doan.cnpm.domain.Subject;
import com.doan.cnpm.repositories.SubjectRepository;
import com.doan.cnpm.service.dto.SubjectDTO;
import org.springframework.stereotype.Service;

@Service
public class SubjectService {
    private final SubjectRepository subjectRepository;

    public SubjectService(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    public Subject CreateSubject(SubjectDTO subject){
        Subject newSubject = new Subject();
        newSubject.setNameSubject(subject.getNameSubject());
        subjectRepository.save(newSubject);
        return newSubject;
    }

    public Subject UpdateSubject(SubjectDTO subject, Long id){
        Subject subject1 = subjectRepository.findOneById(id);

        subject1.setNameSubject(subject.getNameSubject());
        subjectRepository.save(subject1);
        return subject1;
    }

    public void DeleteSubject(Long id){
        subjectRepository.deleteById(id);
    }
}
