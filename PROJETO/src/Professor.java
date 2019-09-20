
public class Professor extends Usuario {

	protected String setor;

	public Professor(String matricula, String nome, String endereco, String dataIng, String setor) {
		super(matricula, nome, endereco, dataIng);
		this.setor = setor;
	}

	public String novo() {
		return (matricula + ";" + nome + ";" + "-" + ";" + "-" + ";" + "-" + ";" + "-" + ";" + "-" + ";" + "-" + ";"
				+ endereco + ";" + dataIng + ";" + setor + ";");
	}

	@Override
	public String toString() {
		return super.toString() + "SETOR: " + setor;
	}

	public String getSetor() {
		return setor;
	}

	public void setSetor(String setor) {
		this.setor = setor;
	}
}