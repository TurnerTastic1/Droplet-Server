package com.TCorp.FitNetServer.api.repository;

import com.TCorp.FitNetServer.api.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserAccountRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findUserAccountByEmailOrUsername(String email, String name);
}
