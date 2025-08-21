package org.exercise.util;

import org.exercise.record.Employee;

import java.util.ArrayList;
import java.util.List;

public class ValidationResult {
    public List<Employee> empleadosValidos;
    public List<String> errores;
    public List<Employee> empleadosInvalidos;

    public ValidationResult() {
        this.empleadosValidos = new ArrayList<>();
        this.errores = new ArrayList<>();
        this.empleadosInvalidos = new ArrayList<>();
    }

}