import java.util.*;

import static java.util.stream.Collectors.*;

public class TrabajoStreams {

    public record Alumno(String nombre, double nota, String curso) {}
    public record Producto(String nombre, String categoria, double precio, int stock) {}

    // --- CASO PRACTICO 1 (Alumnos) ---

    public static List<String> alumnosAprobadosMayusOrdenados(List<Alumno> alumnos) {
        return alumnos.stream()
                .filter(a -> a.nota() >= 7.0)
                .map(a -> a.nombre().toUpperCase(Locale.ROOT))
                .distinct()
                .sorted()
                .collect(toList());
    }

    public static OptionalDouble promedioGeneralNotas(List<Alumno> alumnos) {
        return alumnos.stream()
                .mapToDouble(Alumno::nota)
                .average();
    }


    public static Map<String, List<Alumno>> agruparPorCurso(List<Alumno> alumnos) {
        return alumnos.stream()
                .collect(groupingBy(Alumno::curso));
    }


    public static List<Alumno> top3AlumnosPorNota(List<Alumno> alumnos) {
        return alumnos.stream()
                .sorted(Comparator.comparingDouble(Alumno::nota).reversed())
                .limit(3)
                .collect(toList());
    }

    // --- CASO PRACTICO 2 (Productos) ---


    public static List<Producto> productosPrecioMayor100OrdenadosDesc(List<Producto> productos) {
        return productos.stream()
                .filter(p -> p.precio() > 100.0)
                .sorted(Comparator.comparingDouble(Producto::precio).reversed())
                .collect(toList());
    }


    public static Map<String, Integer> stockTotalPorCategoria(List<Producto> productos) {
        return productos.stream()
                .collect(groupingBy(Producto::categoria,
                        summingInt(Producto::stock)));
    }


    public static String productosNombreYPrecioJoin(List<Producto> productos) {
        return productos.stream()
                .map(p -> String.format("%s:%.2f", p.nombre(), p.precio()))
                .collect(joining(" ; "));
    }


    public static OptionalDouble promedioPrecioGeneral(List<Producto> productos) {
        return productos.stream()
                .mapToDouble(Producto::precio)
                .average();
    }


    public static Map<String, Double> promedioPrecioPorCategoria(List<Producto> productos) {
        return productos.stream()
                .collect(groupingBy(Producto::categoria,
                        averagingDouble(Producto::precio)));
    }
    // --- CASO PRACTICO 3 (Libros) ---

    public record Libro(String titulo, String autor, int paginas, double precio) {}


    public static List<String> titulosLibrosMasDe300Paginas(List<Libro> libros) {
        return libros.stream()
                .filter(l -> l.paginas() > 300)
                .map(Libro::titulo)
                .sorted()
                .collect(toList());
    }


    public static OptionalDouble promedioPaginas(List<Libro> libros) {
        return libros.stream()
                .mapToInt(Libro::paginas)
                .average();
    }


    public static Map<String, Long> contarLibrosPorAutor(List<Libro> libros) {
        return libros.stream()
                .collect(groupingBy(Libro::autor, counting()));
    }

    public static Optional<Libro> libroMasCaro(List<Libro> libros) {
        return libros.stream()
                .max(Comparator.comparingDouble(Libro::precio));
    }
// --- CASO PRACTICO 4 (Empleados) ---

    public record Empleado(String nombre, String departamento, double salario, int edad) {}


    public static List<Empleado> empleadosSalarioMayorA2000(List<Empleado> empleados) {
        return empleados.stream()
                .filter(e -> e.salario() > 2000)
                .sorted(Comparator.comparingDouble(Empleado::salario).reversed())
                .collect(toList());
    }

    public static OptionalDouble salarioPromedioGeneral(List<Empleado> empleados) {
        return empleados.stream()
                .mapToDouble(Empleado::salario)
                .average();
    }

    public static Map<String, Double> sumaSalariosPorDepartamento(List<Empleado> empleados) {
        return empleados.stream()
                .collect(groupingBy(Empleado::departamento,
                        summingDouble(Empleado::salario)));
    }

    public static List<String> dosEmpleadosMasJovenes(List<Empleado> empleados) {
        return empleados.stream()
                .sorted(Comparator.comparingInt(Empleado::edad))
                .limit(2)
                .map(Empleado::nombre)
                .collect(toList());
    }

    //MAIN
    public static void main(String[] args) {
        // --- Caso 1: Alumnos ---
        List<Alumno> alumnos = List.of(
                new Alumno("Ana", 8.5, "A1"),
                new Alumno("Carlos", 6.0, "A1"),
                new Alumno("Beatriz", 9.0, "B1"),
                new Alumno("Diego", 7.0, "A1"),
                new Alumno("Elena", 8.0, "B1"),
                new Alumno("Fede", 5.5, "C1")
        );

        System.out.println("=== CASO 1 - ALUMNOS ===");
        System.out.println("1) Aprobados (mayus, ordenados): " + alumnosAprobadosMayusOrdenados(alumnos));
        promedioGeneralNotas(alumnos).ifPresentOrElse(
                avg -> System.out.printf("2) Promedio general de notas: %.2f%n", avg),
                () -> System.out.println("2) Promedio general de notas: no hay alumnos")
        );

        System.out.println("3) Agrupación por curso:");
        agruparPorCurso(alumnos).forEach((curso, lista) -> {
            System.out.printf("   Curso %s -> %s%n", curso,
                    lista.stream().map(Alumno::nombre).collect(toList()));
        });

        System.out.println("4) Top 3 alumnos por nota:");
        top3AlumnosPorNota(alumnos).forEach(a ->
                System.out.printf("   %s - %.2f (curso %s)%n", a.nombre(), a.nota(), a.curso())
        );

        // --- Caso 2: Productos ---
        List<Producto> productos = List.of(
                new Producto("Televisor", "Electrónica", 450.0, 5),
                new Producto("Cargador", "Electrónica", 25.0, 50),
                new Producto("Licuadora", "Hogar", 150.0, 10),
                new Producto("Cafetera", "Hogar", 120.0, 4),
                new Producto("Libro Java", "Libros", 80.0, 20),
                new Producto("Auriculares", "Electrónica", 110.0, 15)
        );

        System.out.println("\n=== CASO 2 - PRODUCTOS ===");
        System.out.println("1) Productos precio > 100 ordenados desc:");
        productosPrecioMayor100OrdenadosDesc(productos).forEach(p ->
                System.out.printf("   %s - %.2f (cat %s) stock=%d%n",
                        p.nombre(), p.precio(), p.categoria(), p.stock())
        );

        System.out.println("2) Stock total por categoría:");
        stockTotalPorCategoria(productos).forEach((cat, stock) ->
                System.out.printf("   %s -> %d%n", cat, stock)
        );

        System.out.println("3) String productos nombre:precio (join ';'):");
        System.out.println("   " + productosNombreYPrecioJoin(productos));

        promedioPrecioGeneral(productos).ifPresentOrElse(
                avg -> System.out.printf("4a) Precio promedio general: %.2f%n", avg),
                () -> System.out.println("4a) Precio promedio general: no hay productos")
        );

        System.out.println("4b) Precio promedio por categoría:");
        promedioPrecioPorCategoria(productos).forEach((cat, avg) ->
                System.out.printf("   %s -> %.2f%n", cat, avg)
        );
        // --- Caso 3: LIBROS ---
        List<Libro> libros = List.of(
                new Libro("El Quijote", "Cervantes", 500, 25.0),
                new Libro("Cien Años de Soledad", "García Márquez", 350, 30.0),
                new Libro("Rebelión en la Granja", "Orwell", 150, 15.0),
                new Libro("Rayuela", "Cortázar", 400, 28.0),
                new Libro("1984", "Orwell", 320, 20.0)
        );

        System.out.println("\n=== CASO 3 - LIBROS ===");
        System.out.println("1) Títulos > 300 páginas: " + titulosLibrosMasDe300Paginas(libros));

        promedioPaginas(libros).ifPresent(avg ->
                System.out.printf("2) Promedio de páginas: %.2f%n", avg));

        System.out.println("3) Cantidad de libros por autor:");
        contarLibrosPorAutor(libros).forEach((autor, cant) ->
                System.out.printf("   %s -> %d%n", autor, cant));

        libroMasCaro(libros).ifPresent(l ->
                System.out.printf("4) Libro más caro: %s (%.2f)%n", l.titulo(), l.precio()));

        // --- Caso 4: EMPLEADOS ---
        List<Empleado> empleados = List.of(
                new Empleado("Ana", "Ventas", 2500, 28),
                new Empleado("Carlos", "IT", 3200, 35),
                new Empleado("Beatriz", "RRHH", 1800, 30),
                new Empleado("Diego", "IT", 2800, 25),
                new Empleado("Elena", "Ventas", 2100, 22)
        );

        System.out.println("\n=== CASO 4 - EMPLEADOS ===");
        System.out.println("1) Empleados con salario > 2000:");
        empleadosSalarioMayorA2000(empleados).forEach(e ->
                System.out.printf("   %s - %.2f (%s)%n", e.nombre(), e.salario(), e.departamento()));

        salarioPromedioGeneral(empleados).ifPresent(avg ->
                System.out.printf("2) Salario promedio general: %.2f%n", avg));

        System.out.println("3) Suma de salarios por departamento:");
        sumaSalariosPorDepartamento(empleados).forEach((dep, suma) ->
                System.out.printf("   %s -> %.2f%n", dep, suma));

        System.out.println("4) Dos empleados más jóvenes: " + dosEmpleadosMasJovenes(empleados));


    }
}