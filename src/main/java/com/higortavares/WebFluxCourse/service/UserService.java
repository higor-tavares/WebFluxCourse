package com.higortavares.WebFluxCourse.service;

import com.higortavares.WebFluxCourse.entity.User;
import com.higortavares.WebFluxCourse.exception.UserNotFoundException;
import com.higortavares.WebFluxCourse.mapper.UserMapper;
import com.higortavares.WebFluxCourse.model.request.UserRequest;
import com.higortavares.WebFluxCourse.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper mapper;

    public Mono<User> save(final UserRequest userRequest) {
        return userRepository.save(mapper.toEntity(userRequest));
    }

    public Mono<User> find(final String id) {
        return userRepository.find(id)
                .switchIfEmpty(Mono.error(new UserNotFoundException(id)));
    }
}
