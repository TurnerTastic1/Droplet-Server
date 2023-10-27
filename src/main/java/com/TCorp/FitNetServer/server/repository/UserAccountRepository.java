package com.TCorp.FitNetServer.server.repository;

import com.TCorp.FitNetServer.server.model.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {

}
