import java.util.ArrayList;

public class Biblioteca {
    private ArrayList<Usuario> usuarios;
    private ArrayList<Libro> libros;

    public Biblioteca() {
        this.usuarios = new ArrayList<>();
        this.libros = new ArrayList<>();
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
}
