public class Livro {

	protected String codigo;
	protected String autor;
	protected String titulo;
	protected String tipo;
	protected int quantidade;


	public Livro(String codigo, String autor, String titulo, String tipo, int quantidade) {
		this.codigo = codigo;
		this.autor = autor;
		this.titulo = titulo;
		this.tipo = tipo;
		this.quantidade = quantidade;
	}

	public String toString() {
		return ("CODIGO: " + codigo + "\n"
				+ "AUTOR: " + autor + "\n"
				+ "TITULO: " + titulo + "\n"
				+ "TIPO: " + tipo + "\n"
				+ "QUANTIDADE: " + quantidade + "\n");
	}

	public String novo() {
		return (codigo + ";" + autor + ";" + titulo + ";" + tipo + ";" + quantidade + ";");
	}

































	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
}


