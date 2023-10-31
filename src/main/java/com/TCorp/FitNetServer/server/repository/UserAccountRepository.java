package com.TCorp.FitNetServer.server.repository;

import com.TCorp.FitNetServer.server.model.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {
    Optional<UserAccount> findUserAccountByEmailOrUsername(String email, String name);
}
