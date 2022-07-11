package com.example.userservice.repository;

import com.example.userservice.entity.OauthUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OauthUserRepository extends JpaRepository<OauthUser, Long> {
}
