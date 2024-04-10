package com.expensetracker.Service;

import com.expensetracker.DTO.ExpenseDTO;
import com.expensetracker.Entity.Category;
import com.expensetracker.Entity.Expense;
import com.expensetracker.Entity.User;
import com.expensetracker.Enum.ExpenseType;
import com.expensetracker.Exception.ExpenseException;
import com.expensetracker.Repository.CategoryRepository;
import com.expensetracker.Repository.ExpenseRepository;
import com.expensetracker.Repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@PreAuthorize("isAuthenticated()")
public class ExpenseService {

    private static final Logger logger = LoggerFactory.getLogger(ExpenseService.class);

    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private UserRepository userRepository;

    public ExpenseDTO addExpense(ExpenseDTO expenseDTO) throws Exception{
        System.out.println("Inside expense service");
        Optional<User> user = userRepository.findById(expenseDTO.getUser().getUserId());
        if(user.isEmpty()){
            throw new ExpenseException("Unable to add expense. User details not correct");
        }
        Expense expense = ExpenseDTO.prepareExpense(expenseDTO);
        if(expenseDTO.getCategory().getCategoryId()!=null) {
            Optional<Category> category = categoryRepository.findById(expenseDTO.getCategory().getCategoryId());
            if(category.isPresent()){
                expense.setCategory(category.get());
            }
        } else {
            Category category = expense.getCategory();
            Category persistedCategory = categoryRepository.save(category);
            expense.setCategory(persistedCategory);
        }
        Expense persistedExpense = expenseRepository.save(expense);
        ExpenseDTO result = Expense.prepareExpenseDTO(persistedExpense);
        return result;
    }

    public List<ExpenseDTO> getExpenseByUserId(int userId) {
        List<ExpenseDTO> result = new ArrayList<>();
        List<Expense> expenses = expenseRepository.findByUser_UserId(userId);
        if(expenses.size()==0){
            logger.info("No expense found for given user");
        }
        expenses.stream().forEach(expense -> {
            result.add(Expense.prepareExpenseDTO(expense));
        });
        return result;
    }

    public ExpenseDTO getExpenseById(int expenseId){
        Optional<Expense> expense = expenseRepository.findById(expenseId);
        if(expense.isEmpty()){
            throw new ExpenseException("No expense found under given ExpenseId");
        }
        Expense expenseResult = expense.get();
        ExpenseDTO expenseDTO = Expense.prepareExpenseDTO(expenseResult);
        return expenseDTO;
    }

    public String deleteExpenseById(int expenseId) {
        Optional<Expense> expense = expenseRepository.findById(expenseId);
        if(expense.isEmpty()){
            throw new ExpenseException("No expense founder under category id");
        }
        expenseRepository.deleteById(expenseId);
        return "Expense with id " + expenseId + " deleted";
    }

    public ExpenseDTO updateExpenses(ExpenseDTO expenseDTO) {
        System.out.println(expenseDTO.getId());
        if(expenseDTO.getId() == null) {
            throw new ExpenseException("Expense id is null");
        }
        Optional<Expense> expense = expenseRepository.findById(expenseDTO.getId());
        Expense expenseToBePersisted = expense.get();
        expenseToBePersisted.setId(expenseDTO.getId());
        expenseToBePersisted.setUser(expenseDTO.getUser());
        expenseToBePersisted.setCategory(expenseDTO.getCategory());
        expenseToBePersisted.setAmount(expenseDTO.getAmount());
        expenseToBePersisted.setDescription(expenseDTO.getDescription());
        expenseToBePersisted.setCreatedTime(expenseDTO.getCreatedTime());
        expenseToBePersisted.setExpenseType(expenseDTO.getExpenseType());
        categoryRepository.save(expenseToBePersisted.getCategory());
        Expense result = expenseRepository.save(expenseToBePersisted);
        return Expense.prepareExpenseDTO(result);
    }

    public List<ExpenseDTO> getExpenseBetweenDates(OffsetDateTime startDate, OffsetDateTime endDate) {
        List<Expense> expenseList = expenseRepository.findAllByCreatedTimeBetween(startDate,endDate);
        if(expenseList.isEmpty()){
            throw new ExpenseException("No Expense found between these dates");
        }
        List<ExpenseDTO> expenseDTOList = expenseList.stream().map(Expense::prepareExpenseDTO).toList();
        return expenseDTOList;
    }

    public List<ExpenseDTO> getExpenseByType(ExpenseType expenseType) {
        List<Expense> expenseList = expenseRepository.findAllByExpenseType(expenseType);
        if(expenseList.isEmpty()){
            throw new ExpenseException("No Expense found");
        }
        List<ExpenseDTO> expenseDTOList = expenseList.stream().map(Expense::prepareExpenseDTO).toList();
        return expenseDTOList;
    }
}
