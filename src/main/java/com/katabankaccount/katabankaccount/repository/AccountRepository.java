package com.katabankaccount.katabankaccount.repository;


import com.katabankaccount.katabankaccount.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
    Optional<Account> findById(Integer id);

    List<Account> findAccountsByClientId(String clientId);

}
