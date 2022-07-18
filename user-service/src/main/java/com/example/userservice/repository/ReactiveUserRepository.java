package com.example.userservice.repository;

import com.example.userservice.entity.User;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ReactiveUserRepository
        extends ReactiveCrudRepository<User, Long>,
        ReactiveSortingRepository<User, Long> {

    @Query("select * from users where users.title like :title")
    Flux<User> findAllByTitle(String title);

    @Query("select count(1) from users where users.title like :title")
    Mono<Long> countAllByTitle(String title);

}
