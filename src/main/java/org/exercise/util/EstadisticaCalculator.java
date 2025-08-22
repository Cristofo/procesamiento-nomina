package org.exercise.util;

import org.exercise.record.Employee;

import java.util.List;

public class EstadisticaCalculator {

    public void mostrarEstadisticas(ValidationResult resultado) {
        List<Employee> validos = resultado.empleadosValidos;

        System.out.println("\n📊 === ESTADÍSTICAS ===");
        System.out.println("Total empleados válidos: " + validos.size());
        System.out.println("Total empleados inválidos: " + resultado.empleadosInvalidos.size());

        if (!validos.isEmpty()) {
            // Promedio salario final
            double promedioSalario = validos.stream()
                    .mapToDouble(Employee::calcularSalarioFinal)
                    .average()
                    .orElse(0.0);
            System.out.println("Promedio salario final: $" + String.format("%,.0f", promedioSalario));

            // Antigüedad promedio
            double promedioAntiguedad = validos.stream()
                    .mapToInt(Employee::calcularAntiguedad)
                    .average()
                    .orElse(0.0);
            System.out.println("Antigüedad promedio: " + String.format("%.1f", promedioAntiguedad) + " años");
        }
    }
}