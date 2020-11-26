package com.katabankaccount.katabankaccount.repository;


import com.katabankaccount.katabankaccount.model.Operation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;


@Repository
public interface OperationRepository extends JpaRepository<Operation, Integer> {

    Optional<Operation> findById(Integer id);

    List<Operation> findOperationsByAccountIdAndDateBetweenOrderByDateDesc(Integer accountId,
                                                                           Instant start,
                                                                           Instant end);
}
