package com.doan.cnpm.service;

import com.doan.cnpm.repositories.UserAuthorityRepository;
import com.doan.cnpm.service.exception.UserNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserAuthorityService {

    private UserAuthorityRepository userAuthorityRepository;

    public UserAuthorityService(UserAuthorityRepository userAuthorityRepository) {
        this.userAuthorityRepository = userAuthorityRepository;
    }

    public String getAuthority(String userId) {
        String authority = userAuthorityRepository.getAuthorialUserId(userId);
        if(authority != null) {
            return authority;
        }
        else {
            throw new UserNotFoundException();
        }
    }
}
