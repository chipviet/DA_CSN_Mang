package com.doan.cnpm.service;

import com.doan.cnpm.domain.Need;
import com.doan.cnpm.repositories.NeedRepository;
import com.doan.cnpm.service.dto.NeedDTO;
import org.springframework.stereotype.Service;

@Service
public class NeedService {
    private final NeedRepository needRepository;

    public NeedService(NeedRepository needRepository) {
        this.needRepository = needRepository;
    }

    public Need CreateNeed(NeedDTO need, Long idUser,String type){
        Need newNeed = new Need();
        newNeed.setIdUser(idUser);
        newNeed.setLevel(need.getLevel());
        newNeed.setSubject(need.getSubject());
        newNeed.setPlace(need.getPlace());
        newNeed.setSchedule(need.getSchedule());
        newNeed.setStatus(false);
        newNeed.setType(type);

        needRepository.save(newNeed);
        return newNeed;
    }

    public Need UpdateNeed(NeedDTO need, Long id){
        Need need1 = needRepository.findOneById(id);

        need1.setIdUser(need1.getIdUser());
        need1.setLevel(need1.getLevel());
        need1.setSubject(need1.getSubject());
        need1.setPlace(need1.getPlace());
        need1.setSchedule(need1.getSchedule());
        need1.setStatus(need1.getStatus());
        needRepository.save(need1);
        return need1;
    }

    public void DeleteNeed(Long id){
        needRepository.deleteById(id);
    }
}
