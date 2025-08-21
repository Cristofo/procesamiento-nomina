package org.exercise.util;

import org.exercise.record.Employee;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.exercise.util.Constant.DATE_FORMATTER;


public class CsvMaker {

    private static final Path OUTPUT_PATH = Paths.get("src/main/resources/output/");

    public void generarArchivoValidos(List<Employee> empleadosValidos) throws IOException {
        Path archivoValidos = OUTPUT_PATH.resolve("empleados_validos.csv");

        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(archivoValidos.toFile())))) {
            // Header
            writer.println("Nombre;Apellido;RUT;Cargo;SalarioBase;Bonos;Descuentos;FechaIngreso;Antiguedad;BonificacionAntiguedad;SalarioFinal");

            // Datos
            for (Employee emp : empleadosValidos) {
                double bonificacionAntiguedad = emp.calcularBonificacionAntiguedad();
                writer.printf("%s;%s;%s;%s;%.0f;%.0f;%.0f;%s;%d;%.0f;%.0f%n",
                        escapeCSV(emp.nombre()),
                        escapeCSV(emp.apellido()),
                        escapeCSV(emp.rut()),
                        escapeCSV(emp.cargo()),
                        emp.salarioBase(),
                        emp.bonos(),
                        emp.descuentos(),
                        emp.fechaIngreso().format(DATE_FORMATTER),
                        emp.calcularAntiguedad(),
                        bonificacionAntiguedad,
                        (emp.salarioBase() + emp.bonos() + bonificacionAntiguedad) - emp.descuentos());
            }
        }

        System.out.println("✅ Archivo empleados válidos: " + empleadosValidos.size() + " registros");
    }

    public void generarArchivoInvalidos(List<Employee> empleadosInvalidos, List<String> errores) throws IOException {
        Path archivoInvalidos = OUTPUT_PATH.resolve("empleados_invalidos.csv");


        //Optimización de la escrirtura
        // FileWriter escribe en disco 1 por 1
        // BUfferWriter junta un numero de operaciones para ser escrita
        // PrinterWriter convenincia para el formateo de datos,  flush automatico
        // Cierra los elementos al terminar la ejecución por lo tanto se escriben en el disco
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(archivoInvalidos.toFile())))) {

            // Header
            writer.println("Nombre;Apellido;RUT;Cargo;SalarioBase;Bonos;Descuentos;FechaIngreso;MotivoError");

            // Datos
            for (int i = 0; i < empleadosInvalidos.size(); i++) {
                Employee emp = empleadosInvalidos.get(i);
                //Se utiliza printf para agregar el formateo de cada campo %s tesxto, %.0f double sin decimales, \n salto de linea
                writer.printf("%s;%s;%s;%s;%.0f;%.0f;%.0f;%s;%s%n",
                        escapeCSV(emp.nombre()),
                        escapeCSV(emp.apellido()),
                        escapeCSV(emp.rut()),
                        escapeCSV(emp.cargo()),
                        emp.salarioBase(),
                        emp.bonos(),
                        emp.descuentos(),
                        emp.fechaIngreso() != null ? emp.fechaIngreso().format(Constant.DATE_FORMATTER) : "N/A",
                        escapeCSV(errores.get(i)));
            }
        }

        System.out.println("❌ Archivos empleados invalidos: " + empleadosInvalidos.size() + " registros");
    }

    //para evitar caracteres que puedan afectar la delimitación
    private String escapeCSV(String value) {
        if (value == null) return "";
        if (value.contains("\"") || value.contains(";") || value.contains("\n")) {
            return "\"" + value.replace("\"", "\"\"") + "\"";
        }
        return value;
    }
}


