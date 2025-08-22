# 📊 Procesador Nomina

Una aplicación Java para procesar, validar y analizar archivos CSV de empleados con cálculos avanzados de salarios y antigüedad.

## 🚀 Características

- **📁 Procesamiento de CSV**: Lee archivos CSV con delimitador personalizado (`,`)
- **✅ Validaciones completas**: RUT único, salarios mínimos, bonos, descuentos y fechas
- **🧮 Cálculos automáticos**: Antigüedad, bonificaciones y salarios finales
- **📊 Generación de reportes**: Archivos separados para registros válidos e inválidos
- **📈 Estadísticas**: Promedios y totales por consola
- **⚡ Java puro**: Sin dependencias externas - sólo BufferedReader, FileWriter y PrintWriter

## 📋 Validaciones Implementadas

1. **🆔 RUT único**: Sin duplicados
2. **💰 Salario base mínimo**: ≥ $400.000
3. **🎁 Bonos**: Máximo 50% del salario base
4. **➖ Descuentos**: Máximo 100% del salario base
5. **📅 Fecha de ingreso**: Formato válido (yyyy-MM-dd) y no futura

## 🧮 Cálculos Realizados

1. **⏳ Antigüedad**: Años desde la fecha de ingreso
2. **🎯 Bonificación por antigüedad**:
   - >5 años: +10% salario base
   - 3-5 años: +5% salario base
   - <3 años: Sin bonificación
3. **💵 Salario final**: `(SalarioBase + Bonos + BonificaciónAntigüedad) - Descuentos`

## 🛠️ Requisitos Previos

- **Java 21+**

## 📥 Instalación y Ejecución

### 1. Clonar o descargar el proyecto

```bash
git clone https://github.com/Cristofo/procesamiento-nomina.git
cd procesamiento-nomina
```

### 2. Compilar el código Java

```utilizando powerSHell de windows, estando en la raiz del proyecto C://.../procesamiento-nomina

javac -d build/classes src/main/java/org/exercise/record/*.java src/main/java/org/exercise/util/*.java src/main/java/org/exercise/ProcesadorNomina.java
```

### 3. Preparar archivo de entrada

Crear un archivo `empleados.csv` con el formato (usando coma como delimitador), este archivo debe agregarse en la ruta resources/input:

```csv
Nombre,Apellido,RUT,Cargo,SalarioBase,Bonos,Descuentos,FechaIngreso
Juan,Pérez,12345678-9,Analista,500000,100000,50000,2019-03-15
Maria,González,98765432-1,Desarrollador,600000,80000,40000,2020-05-20
```

### 4. Ejecutar la aplicación

Al ejecutar el comando los archivos resultantes se encontrarán en la ruta resources/output

```bash
java -cp build/classes org.exercise.ProcesadorNomina
```


## 📁 Estructura del Proyecto

procesador-nomina/
│
├── 📁 src/
│   └── 📁 main/
│       ├── 📁 java/
│       │   └── 📁 org/
│       │       └── 📁 exercise/
│       │           ├── 📁 record/
│       │           │   └── 🟢 Employee.java
│       │           ├── 📁 util/
│       │           │   ├── 🟢 Constant.java
│       │           │   ├── 🟢 CsvMaker.java
│       │           │   ├── 🟢 CsvReader.java
│       │           │   ├── 🟢 EmployeeValidator.java
│       │           │   ├── 🟢 EstadisticaCalculator.java
│       │           │   └── 🟢 ValidationResult.java
│       │           └── 🟢 ProcesadorNomina.java
│       └── 📁 resources/
│           ├── 📁 input/
│           │   └── 📄 empleados.csv
│           └── 📁 output/
│               ├── 📄 empleados_validos.csv
│               └── 📄 empleados_invalidos.csv


## 📊 Formato de Entrada CSV

El archivo debe tener las siguientes columnas (delimitador `,`):

| Columna | Tipo | Descripción | Ejemplo |
|---------|------|-------------|---------|
| Nombre | String | Nombre del empleado | Juan |
| Apellido | String | Apellido del empleado | Pérez |
| RUT | String | RUT con formato | 12345678-9 |
| Cargo | String | Posición del empleado | Analista |
| SalarioBase | Double | Salario base | 500000 |
| Bonos | Double | Bonos adicionales | 100000 |
| Descuentos | Double | Descuentos aplicados | 50000 |
| FechaIngreso | Date | Fecha de ingreso (yyyy-MM-dd) | 2019-03-15 |

## 📤 Salidas Generadas

### 1. Archivo de empleados válidos
`empleados_validos.csv`
- Incluye columnas adicionales: Antigüedad, Bonificación, SalarioFinal

### 2. Archivo de empleados inválidos  
`empleados_invalidos.csv`
- Incluye columna con motivo del error

### 3. Reporte por consola
```
=== ESTADÍSTICAS ===
Total empleados válidos: 310880
Total empleados inválidos: 689120
Promedio salario final: $395,029
Antigüedad promedio: 1.8 años
```

## 🎨 Personalización

### Modificar salario mínimo
```java
private static final double SALARIO_MINIMO = 450000.0; // Cambiar valor
```

### Modificar porcentajes de bonificación
```java
// En el método calcularBonificacionAntiguedad()
if (antiguedad > 5) return salarioBase * 0.15; // 15% en lugar de 10%
```

### Cambiar delimitador CSV (si es necesario)
```java
private static final String DELIMITER = ","; // Ya está configurado como coma
```

## 🔍 Implementación Técnica

El proyecto utiliza sólo las clases nativas de Java:
- `BufferedReader` para leer el archivo CSV línea por línea
- `FileWriter` y `PrintWriter` para escribir los archivos de salida
- `String.split()` para dividir las columnas usando la coma como delimitador
- Manejo manual de parsing y validación de datos

## 📝 Ejemplo de Uso

1. **Preparar datos**: Crear `empleados.csv` con el formato correcto
2. **Compilar en raiz del proyecto**: javac -d build/classes src/main/java/org/exercise/record/*.java src/main/java/org/exercise/util/*.java src/main/java/org/exercise/ProcesadorNomina.java

3. **Ejecutar**: `java -cp build/classes org.exercise.ProcesadorNomina`
4. **Resultados**: 
   - Archivos generados: `empleados_validos.csv` y `empleados_invalidos.csv`
   - Estadísticas mostradas en consola

