package com.higortavares.WebFluxCourse.service;

import com.higortavares.WebFluxCourse.entity.User;
import com.higortavares.WebFluxCourse.mapper.UserMapper;
import com.higortavares.WebFluxCourse.model.request.UserRequest;
import com.higortavares.WebFluxCourse.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Arrays;
import java.util.Objects;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private UserRepository repository;
    @Mock
    private UserMapper mapper;
    @InjectMocks
    private UserService service;

    @Test
    void testSave() {
        UserRequest request = new UserRequest("paulo","paulo@yahoo.com", "1234");
        User entity = User.builder().build();
        when(mapper.toEntity(any(UserRequest.class))).thenReturn(entity);
        when(repository.save(any(User.class))).thenReturn(Mono.just(User.builder().build()));

        Mono<User> result = service.save(request);

        StepVerifier.create(result)
                .expectNextMatches(Objects::nonNull)
                .expectComplete()
                .verify();
    }

    @Test
    void testFind() {
        when(repository.find(any())).thenReturn(Mono.just(User.builder().build()));
        Mono<User> result = service.find("1234");

        StepVerifier.create(result)
                .expectNextMatches(Objects::nonNull)
                .expectComplete()
                .verify();

        verify(repository).find(any());
    }

    @Test
    void testFindAll() {
        User user1 = User.builder().build();
        User user2 = User.builder().build();
        User user3 = User.builder().build();
        when(repository.findAll()).thenReturn(Flux.fromIterable(Arrays.asList(user1, user2, user3)));

        Flux<User> result = service.findAll();

        StepVerifier.create(result)
                .expectNextCount(3)
                .expectComplete()
                .verify();

        verify(repository).findAll();
    }
}
