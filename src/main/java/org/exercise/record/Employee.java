package org.exercise.record;

import java.time.LocalDate;

public record Employee(
        String nombre,
        String apellido,
        String rut,
        String cargo,
        double salarioBase,
        double bonos,
        double descuentos,
        LocalDate fechaIngreso
) {

    public int calcularAntiguedad() {
        return java.time.Period.between(fechaIngreso, LocalDate.now()).getYears();
    }

    public double calcularBonificacionAntiguedad() {
        int antiguedad = calcularAntiguedad();
        if (antiguedad > 5) return salarioBase * 0.10;
        else if (antiguedad >= 3) return salarioBase * 0.05;
        else return 0.0;
    }

    public double calcularSalarioFinal() {
        return (salarioBase + bonos + calcularBonificacionAntiguedad()) - descuentos;
    }
}