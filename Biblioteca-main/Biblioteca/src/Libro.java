public class Libro {
    private String titulo;
    private String autor;
    private String isbn;
    private Usuario usuarioReservado;

    public Libro(String titulo, String autor, String isbn) {
        this.titulo = titulo;
        this.autor = autor;
        this.isbn = isbn;
        this.usuarioReservado = null;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public String getIsbn() {
        return isbn;
    }

    public Usuario getUsuarioReservado() {
        return usuarioReservado;
    }

    public void setUsuarioReservado(Usuario usuarioReservado) {
        this.usuarioReservado = usuarioReservado;
    }
}
