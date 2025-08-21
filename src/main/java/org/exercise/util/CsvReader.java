package org.exercise.util;

import org.exercise.record.Employee;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.exercise.util.Constant.DATE_FORMATTER;

public class CsvReader {



    public List<Employee> readEmployeesFromCSV(String filePath) throws IOException {
        List<Employee> empleados = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean firstLine = true;

            while ((line = br.readLine()) != null) {
                if (firstLine) {
                    firstLine = false; // Saltar headers
                    continue;
                }

                String[] campos = line.split(Constant.DELIMITER);
                if (campos.length == 8) {
                    try {
                        Employee empleado = new Employee(
                                campos[0].trim(),
                                campos[1].trim(),
                                campos[2].trim(),
                                campos[3].trim(),
                                Double.parseDouble(campos[4].trim()),
                                Double.parseDouble(campos[5].trim()),
                                Double.parseDouble(campos[6].trim()),
                                LocalDate.parse(campos[7].trim(), DATE_FORMATTER)
                        );
                        empleados.add(empleado);
                    } catch (Exception e) {
                        System.err.println("Error parsing line: " + line);
                    }
                }
            }
        }
        return empleados;
    }

}
