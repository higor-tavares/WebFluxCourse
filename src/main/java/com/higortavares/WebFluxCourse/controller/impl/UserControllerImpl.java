package com.higortavares.WebFluxCourse.controller.impl;

import com.higortavares.WebFluxCourse.controller.UserController;
import com.higortavares.WebFluxCourse.mapper.UserMapper;
import com.higortavares.WebFluxCourse.model.request.UserRequest;
import com.higortavares.WebFluxCourse.model.response.UserResponse;
import com.higortavares.WebFluxCourse.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserControllerImpl implements UserController {
    private final UserService userService;
    private final UserMapper userMapper;
    @Override
    public ResponseEntity<Mono<Void>> save(UserRequest userRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(userRequest).then());
    }

    @Override
    public ResponseEntity<Mono<UserResponse>> find(String id) {
        return ResponseEntity.ok(userService.find(id).map(userMapper::toResponse));
    }

    @Override
    public ResponseEntity<Flux<UserResponse>> findAll() {
        return ResponseEntity.ok(userService.findAll().map(userMapper::toResponse));
    }

    @Override
    public ResponseEntity<Mono<UserResponse>> update(String id, UserRequest userRequest) {
        return ResponseEntity.accepted().body(userService.update(id, userRequest).map(userMapper::toResponse));
    }

    @Override
    public ResponseEntity<Mono<Void>> delete(String id) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(userService.delete(id).then());
    }
}
