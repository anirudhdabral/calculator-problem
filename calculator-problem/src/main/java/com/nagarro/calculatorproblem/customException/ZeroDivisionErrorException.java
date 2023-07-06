package com.nagarro.calculatorproblem.customException;

public class ZeroDivisionErrorException  extends Exception {
    public ZeroDivisionErrorException() {
        super();
    }

    public ZeroDivisionErrorException(String message) {
        super(message);
    }

    public ZeroDivisionErrorException(String message, Throwable cause) {
        super(message, cause);
    }
}