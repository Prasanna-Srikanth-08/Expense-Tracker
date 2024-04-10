package com.expensetracker.DTO;

import com.expensetracker.Entity.Category;
import com.expensetracker.Entity.Expense;
import com.expensetracker.Entity.User;
import com.expensetracker.Enum.ExpenseType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Data
public class ExpenseDTO {
    private Integer id;
    private User user;
    private Category category;
    private Float amount;
    private String description;
    private OffsetDateTime createdTime;
    private ExpenseType expenseType;

    public static Expense prepareExpense(ExpenseDTO expenseDTO){
        Expense expense = new Expense();
        expense.setId(expenseDTO.getId());
        expense.setUser(expenseDTO.getUser());
        expense.setCategory(expenseDTO.getCategory());
        expense.setAmount(expenseDTO.getAmount());
        expense.setDescription(expenseDTO.getDescription());
        expense.setCreatedTime(expenseDTO.getCreatedTime());
        expense.setExpenseType(expenseDTO.getExpenseType());
        return  expense;
    }
}
