package com.expensetracker.Exception;

import org.springframework.web.bind.annotation.ExceptionHandler;

public class ExpenseException extends RuntimeException{
    int statusCode;
    String message;

    public ExpenseException(String message) {
        this.message = message;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
