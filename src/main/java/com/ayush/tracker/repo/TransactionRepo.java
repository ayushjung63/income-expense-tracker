package com.ayush.tracker.repo;

import com.ayush.tracker.model.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.*;
import org.springframework.stereotype.*;

import java.util.*;

@Repository
public interface TransactionRepo extends JpaRepository<Transaction,Integer> {

    @Query("select t from Transaction t where t.date BETWEEN :from AND :to")
    List<Transaction> findByDateFromTo(@Param("from") String from,@Param("to") String to);

    @Query("Select t from Transaction t where t.date= :today")
    List<Transaction> findByToday(@Param("today") String today);
}
