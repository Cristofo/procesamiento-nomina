package org.exercise.util;

import org.exercise.record.Employee;

import java.time.LocalDate;
import java.util.*;

import static org.exercise.util.Constant.SALARIO_MINIMO;

public class EmployeeValidator {


    public ValidationResult validarEmpleados(List<Employee> empleados) {
        ValidationResult resultado = new ValidationResult();

        //Lista Set para valores únicos
        Set<String> rutsUnicos = new HashSet<>();

        for (int i = 0; i < empleados.size(); i++) {
            Employee empleado = empleados.get(i);
            List<String> erroresEmpleado = new ArrayList<>();

            // Aplicar todas las validaciones
            validarRUT(empleado, rutsUnicos, erroresEmpleado);
            validarSalarioBase(empleado, erroresEmpleado);
            validarBonos(empleado, erroresEmpleado);
            validarDescuentos(empleado, erroresEmpleado);
            validarFechaIngreso(empleado, erroresEmpleado);

            if (erroresEmpleado.isEmpty()) {
                resultado.empleadosValidos.add(empleado);
                rutsUnicos.add(empleado.rut());
            } else {
                resultado.empleadosInvalidos.add(empleado);
                resultado.errores.add("Empleado " + (i + 1) + " (" + empleado.nombre() + " " +
                        empleado.apellido() + "): " + String.join(", ", erroresEmpleado));
            }
        }

        return resultado;
    }

    // 4. Métodos de validación individuales
    private static void validarRUT(Employee empleado, Set<String> rutsUnicos, List<String> errores) {
        String rut = empleado.rut();

        if (Objects.isNull(rut) || rut.trim().isEmpty()) {
            errores.add("RUT no puede estar vacío");
            return;
        }

        if (rutsUnicos.contains(rut)) {
            errores.add("RUT duplicado");
        }
    }

    private static void validarSalarioBase(Employee empleado, List<String> errores) {
        if (empleado.salarioBase() < SALARIO_MINIMO) {
            errores.add("Salario base inferior a $" + SALARIO_MINIMO);
        }
    }

    private static void validarBonos(Employee empleado, List<String> errores) {
        if (empleado.bonos() < 0) errores.add("Bonos negativo");
        else if (empleado.bonos() > empleado.salarioBase() * 0.5) {
            errores.add("Bonos > 50% salario base");
        }
    }

    private static void validarDescuentos(Employee empleado, List<String> errores) {
        if (empleado.descuentos() < 0) errores.add("Descuentos negativo");
        else if (empleado.descuentos() > empleado.salarioBase()) {
            errores.add("Descuentos > salario base");
        }
    }

    private static void validarFechaIngreso(Employee empleado, List<String> errores) {
        if (empleado.fechaIngreso() == null) {
            errores.add("Fecha inválida");
        } else if (empleado.fechaIngreso().isAfter(LocalDate.now())) {
            errores.add("Fecha futura");
        }
    }
}
