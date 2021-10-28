package com.ayush.tracker.repo;

import com.ayush.tracker.model.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

@Repository
public interface ExpenseRepo extends JpaRepository<Expense, Integer> {
}
