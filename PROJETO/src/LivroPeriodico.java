
public class LivroPeriodico extends Livro {

	private double fatorImpacto;

	public LivroPeriodico(String codigo, String autor, String titulo, String tipo, int quantidade, double fatorImpacto) {
		super(codigo, autor, titulo, tipo, quantidade);
		this.fatorImpacto = fatorImpacto;
	}

	@Override
	public String toString() {
		return super.toString() +"FATOR DE IMPACTO: "+fatorImpacto+"\n";
	}

	@Override
	public String novo() {
		return super.novo() + fatorImpacto + ";";
	}



}