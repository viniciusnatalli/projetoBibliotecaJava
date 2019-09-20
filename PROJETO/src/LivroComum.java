
public class LivroComum extends Livro{

	private String editora;
	private String anoPub;
	private String issn;


	public LivroComum(String codigo, String autor, String titulo, String tipo, int quantidade, String editora, String anoPub, String issn) {
		super(codigo, autor, titulo, tipo, quantidade);
		this.editora = editora;
		this.anoPub = anoPub;
		this.issn = issn;
	}

	@Override
	public String toString() {

		String acrescimo = "EDITORA: "+editora +"\n"
				+ "ANO DE PUBLICACAO: "+anoPub +"\n"
				+ "ISSN: "+issn +"\n" + "\n" + "|||||||||||||||||||||" + "\n";
		return super.toString() + acrescimo;
	}

	@Override
	public String novo() {
		return super.novo() + editora + ";" + anoPub + ";" + issn + ";";
	}
}




