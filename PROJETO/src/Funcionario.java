
public class Funcionario extends Usuario {

	private String setor;
	private String login;
	private String senha;

	public Funcionario(String matricula, String nome, String endereco, String dataIng, String setor, String login,
			String senha) {
		super(matricula, nome, endereco, dataIng);
		this.setor = setor;
		this.login = login;
		this.senha = senha;
	}

	public String novo() {
		return (matricula + ";" + nome + ";" + "-" + ";" + "-" + ";" + "-" + ";" + "-" + ";" + "-" + ";"  + "-"
				+ ";" + endereco + ";" + dataIng + ";" + setor + ";" + login + ";" + senha + ";");
	}

	@Override
	public String toString() {
		return super.toString() + "SETOR: " + setor + "\n" + "||||||||||||||||||||||||||||||||||||" + "\n";
	}

	public String getSetor() {
		return setor;
	}

	public void setSetor(String setor) {
		this.setor = setor;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
}
