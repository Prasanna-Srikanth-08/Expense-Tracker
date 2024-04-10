package com.expensetracker.Entity;

import com.expensetracker.DTO.ExpenseDTO;
import com.expensetracker.Enum.ExpenseType;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.OffsetDateTime;

@Entity
@Data
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "userId", name="fk_userid")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "categoryId", name = "fk_categoryid")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Category category;
    private Float amount;
    private String description;
    private OffsetDateTime createdTime;
    private ExpenseType expenseType;

    public static ExpenseDTO prepareExpenseDTO(Expense expense){
        ExpenseDTO expenseDTO = new ExpenseDTO();
        expenseDTO.setId(expense.getId());
        expenseDTO.setUser(expense.getUser());
        expenseDTO.setCategory(expense.getCategory());
        expenseDTO.setAmount(expense.getAmount());
        expenseDTO.setDescription(expense.getDescription());
        expenseDTO.setCreatedTime(expense.getCreatedTime());
        expenseDTO.setExpenseType(expense.getExpenseType());
        return expenseDTO;
    }
}
