package com.evg.otus.service.impl;

import com.evg.otus.exception.DataNotFoundException;
import com.evg.otus.repository.UserRepository;
import com.evg.otus.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.logstash.logback.encoder.org.apache.commons.lang.StringUtils;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import java.util.Objects;


@Service
@Slf4j(topic = "AUTH_SERVICE")
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    @Override
    public void checkAuth(String authToken, Long userIdFromRequest) {

        if(StringUtils.isBlank(authToken)) {
            throw new AuthenticationCredentialsNotFoundException("Credentials empty");
        }

        String emailFromToken = authToken.split(":")[0];
        Long userIdFromToken = Long.valueOf(authToken.split(":")[1]);

        if (!Objects.equals(userIdFromToken, userIdFromRequest)) {
            throw new BadCredentialsException("This is not your profile data. Forbidden!");
        }

        if(!StringUtils.equals(userRepository.findByIdOrElseThrow(userIdFromRequest).getEmail(), emailFromToken)) {
            throw new BadCredentialsException("This is not your profile data. Forbidden!");
        }

    }
}
