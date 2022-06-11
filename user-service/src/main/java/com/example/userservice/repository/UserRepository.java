package com.example.userservice.repository;

import com.example.userservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The User repository for working with entity {@link User}.
 *
 * @author Maxim Semenko
 * @version 0.0.1
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
