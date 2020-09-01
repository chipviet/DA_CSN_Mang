package com.doan.cnpm.controller;

import com.doan.cnpm.domain.User;
import com.doan.cnpm.repositories.UserRepository;
import com.doan.cnpm.security.jwt.JWTFilter;
import com.doan.cnpm.security.jwt.TokenProvider;
import com.doan.cnpm.service.UserService;
import com.doan.cnpm.service.dto.LoginDTO;
import com.doan.cnpm.service.dto.RegisterUserDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.Valid;
import javax.validation.constraints.Size;

@RestController
@RequestMapping("/api/auth")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    private final TokenProvider tokenProvider;

    private UserService userService;

    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    public UserController(TokenProvider tokenProvider, AuthenticationManagerBuilder authenticationManagerBuilder) {
        this.tokenProvider = tokenProvider;
        this.authenticationManagerBuilder = authenticationManagerBuilder;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/getToken")
    public ResponseEntity<JWTToken> getToken (@Valid @RequestBody LoginDTO dto){
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(dto.getUsername(),dto.getPassword());
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate((authenticationToken));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = tokenProvider.createToken(authentication,false);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JWTFilter.AUTHORIZATION_HEADER, "Bearer" + jwt);
        return new ResponseEntity<>(new JWTToken(jwt),httpHeaders, HttpStatus.OK);
    }

    @PostMapping("/v1/user/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void registerUser(@RequestBody RegisterUserDTO dto) {
        userService.registerUser(dto);
    }


    class JWTToken {

        private String authToken;

        public JWTToken(String authToken) {
            this.authToken = authToken;
        }

        @JsonProperty("authToken")
        String getAuthToken() {
            return authToken;
        }

        void setAuthToken(String authToken) {
            this.authToken = authToken;
        }
    }

}
