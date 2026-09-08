package com.invex.employee.modulo.application.exception;

public class EmployeeNotFoundException extends RuntimeException {
    public EmployeeNotFoundException(final String msj) {
        super(msj);
    }
}
