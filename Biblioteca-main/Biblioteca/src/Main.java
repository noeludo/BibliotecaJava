import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Biblioteca biblioteca = new Biblioteca();

        // Crear usuarios y libros de ejemplo
        Usuario usuario1 = new Usuario("Juan", "Perez", "123456789", "juan@example.com");
        Usuario usuario2 = new Usuario("Maria", "Gomez", "987654321", "maria@example.com");
        biblioteca.agregarUsuario(usuario1);
        biblioteca.agregarUsuario(usuario2);

        Libro libro1 = new Libro("El principito", "Antoine de Saint-Exupéry", "9780156012195");
        Libro libro2 = new Libro("Don Quijote de la Mancha", "Miguel de Cervantes", "9788424122609");
        biblioteca.agregarLibro(libro1);
        biblioteca.agregarLibro(libro2);

        int opcion;
        do {
            System.out.println("\nMenú de opciones:");
            System.out.println("1. Iniciar sesión en la biblioteca");
            System.out.println("2. Ver libros disponibles");
            System.out.println("3. Ver libros pendientes de devolver por un usuario");
            System.out.println("4. Pedir un libro");
            System.out.println("5. Devolver un libro");
            System.out.println("0. Salir");
            System.out.print("Ingrese el número de la opción deseada: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese su nombre de usuario: ");
                    String nombreUsuario = scanner.nextLine();
                    Usuario usuario = encontrarUsuario(biblioteca, nombreUsuario);
                    if (usuario != null) {
                        System.out.println("¡Bienvenido, " + usuario.getNombre() + "!");
                        biblioteca.agregarUsuarioColaReserva(usuario);
                    } else {
                        System.out.println("Usuario no encontrado.");
                    }
                    break;
                case 2:
                    System.out.println("Libros disponibles:");
                    for (Libro libro : biblioteca.getLibros()) {
                        if (libro.getUsuarioReservado() == null) {
                            System.out.println(libro.getTitulo() + " - " + libro.getAutor() + " - ISBN: " + libro.getIsbn());
                        }
                    }
                    break;
                case 3:
                    System.out.print("Ingrese su nombre de usuario: ");
                    String nombreUsuarioPendientes = scanner.nextLine();
                    Usuario usuarioPendientes = encontrarUsuario(biblioteca, nombreUsuarioPendientes);
                    if (usuarioPendientes != null) {
                        System.out.println("Libros pendientes de devolver por " + usuarioPendientes.getNombre() + ":");
                        for (Libro libro : usuarioPendientes.getLibrosPendientes()) {
                            System.out.println(libro.getTitulo() + " - " + libro.getAutor() + " - ISBN: " + libro.getIsbn());
                        }
                    } else {
                        System.out.println("Usuario no encontrado.");
                    }
                    break;
                case 4:
                    System.out.print("Ingrese su nombre de usuario: ");
                    String nombreUsuarioPrestamo = scanner.nextLine();
                    Usuario usuarioPrestamo = encontrarUsuario(biblioteca, nombreUsuarioPrestamo);
                    if (usuarioPrestamo != null) {
                        System.out.print("Ingrese el título del libro que desea pedir: ");
                        String tituloLibro = scanner.nextLine();
                        Libro libroPrestamo = encontrarLibro(biblioteca, tituloLibro);
                        if (libroPrestamo != null && libroPrestamo.getUsuarioReservado() == null) {
                            usuarioPrestamo.addLibroPendiente(libroPrestamo);
                            libroPrestamo.setUsuarioReservado(usuarioPrestamo);
                            System.out.println("El libro '" + libroPrestamo.getTitulo() + "' ha sido prestado correctamente a " + usuarioPrestamo.getNombre());
                        } else {
                            System.out.println("Libro no disponible.");
                        }
                    } else {
                        System.out.println("Usuario no encontrado.");
                    }
                    break;
                case 5:
                    System.out.print("Ingrese su nombre de usuario: ");
                    String nombreUsuarioDevolucion = scanner.nextLine();
                    Usuario usuarioDevolucion = encontrarUsuario(biblioteca, nombreUsuarioDevolucion);
                    if (usuarioDevolucion != null) {
                        System.out.print("Ingrese el título del libro que desea devolver: ");
                        String tituloLibroDevolucion = scanner.nextLine();
                        Libro libroDevolucion = encontrarLibro(usuarioDevolucion, tituloLibroDevolucion);
                        if (libroDevolucion != null) {
                            usuarioDevolucion.removeLibroPendiente(libroDevolucion);
                            libroDevolucion.setUsuarioReservado(null);
                            System.out.println("El libro '" + libroDevolucion.getTitulo() + "' ha sido devuelto correctamente por " + usuarioDevolucion.getNombre());

                            // Verificar si hay usuarios en la cola de reserva
                            if (!biblioteca.getColaReserva().isEmpty()) {
                                Usuario usuarioReserva = biblioteca.quitarUsuarioColaReserva();
                                libroDevolucion.setUsuarioReservado(usuarioReserva);
                                usuarioReserva.addLibroPendiente(libroDevolucion);
                                System.out.println("Libro prestado a " + usuarioReserva.getNombre() + " de la cola de reserva.");
                            }
                        } else {
                            System.out.println("Libro no encontrado.");
                        }
                    } else {
                        System.out.println("Usuario no encontrado.");
                    }
                    break;
                case 0:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
                    break;
            }
        } while (opcion != 0);

        scanner.close();
    }

    public static Usuario encontrarUsuario(Biblioteca biblioteca, String nombreUsuario) {
        for (Usuario usuario : biblioteca.getUsuarios()) {
            if (usuario.getNombre().equalsIgnoreCase(nombreUsuario)) {
                return usuario;
            }
        }
        return null;
    }

    public static Libro encontrarLibro(Biblioteca biblioteca, String titulo) {
        for (Libro libro : biblioteca.getLibros()) {
            if (libro.getTitulo().equalsIgnoreCase(titulo)) {
                return libro;
            }
        }
        return null;
    }

    public static Libro encontrarLibro(Usuario usuario, String titulo) {
        for (Libro libro : usuario.getLibrosPendientes()) {
            if (libro.getTitulo().equalsIgnoreCase(titulo)) {
                return libro;
            }
        }
        return null;
    }
}
