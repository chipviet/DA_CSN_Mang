package com.doan.cnpm.service;

import com.doan.cnpm.domain.Authority;
import com.doan.cnpm.domain.User;
import com.doan.cnpm.domain.enumeration.UserStatus;
import com.doan.cnpm.repositories.AuthorityRepository;
import com.doan.cnpm.repositories.UserRepository;
import com.doan.cnpm.security.AuthoritiesConstants;
import com.doan.cnpm.service.dto.RegisterUserDTO;
import com.doan.cnpm.service.exception.UserIsInactiveException;
import com.doan.cnpm.service.exception.UserNotFoundException;
import com.doan.cnpm.service.exception.UsernameAlreadyUsedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;


@Service
@Transactional
public class UserService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    private AuthorityRepository authorityRepository;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthorityRepository authorityRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.authorityRepository = authorityRepository;
    }

    public User registerUser(RegisterUserDTO dto) {

        System.out.println("registerUser");
        Optional<User> existingUser = userRepository.findOneByUsername(dto.getUsername().toLowerCase());

       if(existingUser.isPresent()) {
           throw new UsernameAlreadyUsedException();
       }

        User newUser = new User();
        newUser.setUsername(dto.getUsername().toLowerCase());
        String encryptedPassword = passwordEncoder.encode(dto.getPassword());
        newUser.setPassword(encryptedPassword);
        newUser.setFirstName(dto.getFirstName());
        newUser.setLastName(dto.getLastName());
        newUser.setEmail(dto.getEmail());
        newUser.setGender(dto.getGender());
        newUser.setPhoto(dto.getPhoto());
        newUser.setAddress(dto.getAddress());
        newUser.setLatitude(dto.getLatitude());
        newUser.setLongitude(dto.getLongitude());
        newUser.setIntroduction(dto.getIntroduction());
        newUser.setPhoneNumber(dto.getPhoneNumber());
        newUser.setDateOfBirth(dto.getDateOfBirth());
        newUser.setStatus(UserStatus.INACTIVE);
        newUser.setCreationDate(LocalDate.now());
        Set<Authority> authorities = new HashSet<>();
        authorityRepository.findById(dto.getAuthority()).ifPresent(authorities::add);
        newUser.setAuthorities(authorities);
        userRepository.save(newUser);
        System.out.println("Created information for ewallet User");
        return  newUser;
    }

    public User getUserDetails(String username) throws UserNotFoundException, UserIsInactiveException {

        User user = userRepository.findOneByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found with username:" + username));

        return user;
    }

    public List<User> getAllUser() throws UserNotFoundException, UserIsInactiveException {

        List<User> listUser = userRepository.findAll();
        return listUser;
    }
}

