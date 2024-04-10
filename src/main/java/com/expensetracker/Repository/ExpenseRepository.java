package com.expensetracker.Repository;

import com.expensetracker.Entity.Expense;
import com.expensetracker.Enum.ExpenseType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.OffsetDateTime;
import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense,Integer> {
    List<Expense> findByUser_UserId(int userId);
    List<Expense> findAllByCreatedTimeBetween(OffsetDateTime startDate, OffsetDateTime endTime);
    List<Expense> findAllByExpenseType(ExpenseType type);
}
