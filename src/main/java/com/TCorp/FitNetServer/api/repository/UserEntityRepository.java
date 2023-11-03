package com.TCorp.FitNetServer.api.repository;

import com.TCorp.FitNetServer.api.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserEntityRepository extends JpaRepository<UserEntity, Integer> {
    Optional<UserEntity> findUserEntityByEmailOrUsername(String email, String name);

    Optional<UserEntity> findUserEntityByUsername(String username);
}
