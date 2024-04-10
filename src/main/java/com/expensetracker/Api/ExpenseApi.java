package com.expensetracker.Api;

import com.expensetracker.DTO.ExpenseDTO;
import com.expensetracker.Enum.ExpenseType;
import com.expensetracker.Service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.OffsetDateTime;
import java.util.List;

@RestController
public class ExpenseApi {

    @Autowired
    private ExpenseService expenseService;

    @MutationMapping
    public ExpenseDTO addExpense(@Argument("expenseDto") ExpenseDTO expenseDTO) throws Exception {
        return expenseService.addExpense(expenseDTO);
    }

    @SchemaMapping(typeName = "Query", field = "getExpenseByUserId")
    public List<ExpenseDTO> getExpenseByUserId(@Argument("userId") int userId) {
        return expenseService.getExpenseByUserId(userId);
    }

    @SchemaMapping(typeName = "Mutation", field = "deleteExpenseById")
    public String deleteExpenseById(@Argument("expenseId") int expenseId){
        return expenseService.deleteExpenseById(expenseId);
    }

    @MutationMapping
    public ExpenseDTO updateExpenses(@Argument("expenseDTO") ExpenseDTO expenseDTO) {
        return expenseService.updateExpenses(expenseDTO);
    }

    @QueryMapping
    public ExpenseDTO getExpenseById(@Argument("expenseId") int expenseId){
        return expenseService.getExpenseById(expenseId);
    }

    @QueryMapping
    public List<ExpenseDTO> getExpenseBetweenDates(@Argument("startDate") OffsetDateTime startDate,
                                                   @Argument("endDate") OffsetDateTime endDate) {
        return expenseService.getExpenseBetweenDates(startDate,endDate);
    }

    @QueryMapping
    public List<ExpenseDTO> getExpenseByType(@Argument("expenseType") ExpenseType expenseType) {
        return expenseService.getExpenseByType(expenseType);
    }
}
