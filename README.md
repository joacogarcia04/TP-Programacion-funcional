# TP Programación Funcional - Joaquin Garcia

## Descripción
Trabajo práctico realizado en **Java**, aplicando el paradigma de **programación funcional** con **Streams, Lambdas y Collectors**.

El proyecto contiene varios casos prácticos que procesan listas de objetos (alumnos, productos, libros y empleados) usando operaciones funcionales.

---

## Casos prácticos

### Alumnos
- Nombres de alumnos aprobados (nota ≥ 7) en mayúsculas y ordenados.
- Promedio general de notas.
- Agrupar por curso (`Collectors.groupingBy`).
- Obtener los 3 mejores promedios.

### Productos
- Productos con precio > 100, ordenados por precio descendente.
- Agrupar por categoría y calcular stock total.
- Generar cadena con nombre y precio (`map + joining`).
- Calcular precio promedio general y por categoría.

### Libros
- Títulos con más de 300 páginas, ordenados alfabéticamente.
- Promedio de páginas.
- Agrupar por autor y contar libros.
- Libro más caro.

### Empleados
- Empleados con salario > 2000, ordenados por salario.
- Promedio de salarios.
- Agrupar por departamento y sumar salarios.
- Dos empleados más jóvenes.

---

## Tecnologías utilizadas
- Java 17
- Paradigma Funcional
- API Streams
- Expresiones Lambda
- Collectors (`groupingBy`, `averagingDouble`, `joining`, etc.)

---

## Conceptos aplicados

| Concepto | Aplicación en el proyecto |
|-----------|---------------------------|
| **Stream** | Flujo de datos para aplicar transformaciones (`map`, `filter`, `sorted`). |
| **Collectors** | Agrupar, contar, promediar y unir datos. |
| **Expresiones Lambda + Streams** | Permiten procesar colecciones con código simple y limpio. |
| **Operaciones terminales** | `collect`, `forEach`, `max`, `average`, `limit` para resultados finales. |
| **Inmutabilidad** | Uso de `List.of()` para evitar modificaciones. |

---

## Autor
**Joaquin Garcia**  
3° Año - Ingeniería en Sistemas  
Materia: *Desarrollo de Software III*

