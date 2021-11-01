package com.ayush.tracker.repo;

import com.ayush.tracker.model.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

import java.util.*;

@Repository
public interface TransactionRepo extends JpaRepository<Transaction,Integer> {
    List<Transaction> findByType(Transaction.TranscationType t);
}
