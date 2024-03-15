import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Biblioteca {
    private ArrayList<Usuario> usuarios;
    private ArrayList<Libro> libros;
    private Queue<Usuario> colaReserva;

    public Biblioteca() {
        this.usuarios = new ArrayList<>();
        this.libros = new ArrayList<>();
        this.colaReserva = new LinkedList<>();
    }

    public void agregarUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }

    public void agregarLibro(Libro libro) {
        libros.add(libro);
    }

    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }

    public ArrayList<Libro> getLibros() {
        return libros;
    }

    public Queue<Usuario> getColaReserva() {
        return colaReserva;
    }

    public void agregarUsuarioColaReserva(Usuario usuario) {
        colaReserva.add(usuario);
    }

    public Usuario quitarUsuarioColaReserva() {
        return colaReserva.poll();
    }
}
