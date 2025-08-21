package org.exercise;


import org.exercise.record.Employee;
import org.exercise.util.*;

import java.util.List;

public class ProcesadorNomina {
    public static void main(String[] args) {
        String inputFile = "src/main/resources/input/empleados.csv";
        try {
            // 1. Leer CSV
            CsvReader csvReader = new CsvReader();
            List<Employee> empleados = csvReader.readEmployeesFromCSV(inputFile);

            // 2. Validar
            EmployeeValidator employeeValidator = new EmployeeValidator();
            ValidationResult resultado = employeeValidator.validarEmpleados(empleados);

            // 3. Generar archivos de salida
            CsvMaker csvMaker = new CsvMaker();
            csvMaker.generarArchivoValidos(resultado.empleadosValidos);
            csvMaker.generarArchivoInvalidos(resultado.empleadosInvalidos, resultado.errores);

            // 4. Mostrar estadísticas por consola
            EstadisticaCalculator estadisticaCalculator = new EstadisticaCalculator();
            estadisticaCalculator.mostrarEstadisticas(resultado);

        } catch (Exception e) {
            System.err.println("Error en la aplicación: " + e.getMessage());
            System.exit(1);
        }
    }
}