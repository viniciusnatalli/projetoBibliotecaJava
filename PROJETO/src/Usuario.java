import javax.swing.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Usuario {

	protected String matricula;
	protected String nome;
	protected String[] livrosEmp = {"-","-","-"};
	protected String[] datasEmp = {"-","-","-"};
	protected String endereco;
	protected String dataIng;

	public Usuario(String matricula, String nome, String endereco, String dataIng) {
		this.matricula = matricula;
		this.nome = nome;
		this.endereco = endereco;
		this.dataIng = dataIng;

	}

	public String novo() {

		String a , b ,c , d , e , f;

		a = livrosEmp[0];
		b = livrosEmp[1];
		c = livrosEmp[2];
		d = datasEmp[0];
		e = datasEmp[1];
		f = datasEmp[2];

		return (matricula + ";" + nome + ";" + a +";" + b +";" + c +";" + d +";" + e +";" + f +";" +  endereco + ";" + dataIng + ";" + "0.0" + ";");
	}

	public String toEmprestimo(){
		return "MATRICULA: "+matricula +"\n"
				+"NOME: "+nome;
	}

	public String toString(){
		return("MATRICULA: "+matricula +"\n"
				+"NOME: "+nome +"\n"
				+ "ENDERECO: "+endereco +"\n"
				+ "DATA DE INGRESSO: "+dataIng +"\n"
				+ "|||||||||||||||||||||||||||||||||||||||" + "\n");
	}

	public String retStringLivrosEmprestados(){
		String content = "";
		int qtdEmp = qtdEmprestimos();

		for (int i = 0 ; i < qtdEmp ; i++){
			content+= (i+1) +". Livro: "+livrosEmp[i] +"\n" +
					"   Data Emprestimo: "+datasEmp[i]+"\n";
		}

		return content;
	}

	public void devolver(int n){
		organizaVetores();

		datasEmp[n] = "-";
		livrosEmp[n] = "-";
		organizaVetores();
	}

	public int qtdEmprestimos(){
		organizaVetores();
		int cont = 0;

		for(int i = 0; i < livrosEmp.length ; i++){

			if(this.getLivrosEmp()[i].equalsIgnoreCase("-")){
				continue;
			}else {
				cont++;
			}
		}

		return cont;
	}

	public ArrayList<String> retLivrosComAtraso() throws ParseException {

		ArrayList<String> livrosComAtraso = new ArrayList<>();

		Date dataHoje = new Date();
		long tempHoje = dataHoje.getTime();

		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");


		for(int i = 0 ; i < livrosComAtraso.size() ; i++) {

			String sData = this.datasEmp[i];

			Date dataEmp = dateFormat.parse(sData);
			long tempEmprestimo = dataEmp.getTime() + (864000 * 7);

			long diasAtraso = (tempHoje - tempEmprestimo) / 864000;  //1dia = 864000milisegundos.

			if(diasAtraso > 7){
				livrosComAtraso.add(livrosComAtraso.get(i));
			}

		}
		return livrosComAtraso;
	}

	public String retStringLivrosComAtraso() throws ParseException {

		ArrayList<String> livrosComAtraso = retLivrosComAtraso();
		String a = "";

		for (int i = 0; i < retLivrosComAtraso().size(); i++){

			a += livrosComAtraso.get(i) +"\n";
		}

		return a;
	}



	public int retCondicao() throws ParseException {                           //1 = disponivel , //-1 = atraso //-2 = quantidade limite emprestimos atingida

		int condi = 1;

		if(retLivrosComAtraso().size() > 0){

			JOptionPane.showMessageDialog(null, ("Usuário Indisponível para Empréstimo!\n" +
					"LIVROS COM ATRASO: \n" +
					retStringLivrosComAtraso()));
			return -1;
		}

		else if(qtdEmprestimos() == 3){
			JOptionPane.showMessageDialog(null, "Usuário Indisponível para Empréstimo!\n" +
					"Quantidade Limite de empréstimos atingida!");
			return -2;
		}
		else{
			JOptionPane.showMessageDialog(null, "Usuário Disponível!");
		}


		return condi;
	}

	public void pegarLivro(Livro livro){

		int i = qtdEmprestimos();
		this.livrosEmp[i] = livro.getTitulo();

		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		this.getDatasEmp()[i] = dateFormat.format(date);

	}

	public void preencherArrays(String a, String b, String c , String d, String e, String f){

		this.livrosEmp[0] = a;
		this.livrosEmp[1] = b;
		this.livrosEmp[2] = c;

		this.datasEmp[0] = d;
		this.datasEmp[1] = e;
		this.datasEmp[2] = f;
	}

	public void organizaVetores(){

		for(int i = 0 ; i < livrosEmp.length -1 ; i++){
			if(this.livrosEmp[i].equalsIgnoreCase("-")){
				this.livrosEmp[i]  = this.livrosEmp[i+1];
				this.livrosEmp[i+1] = "-";
			}
		}

		for(int i = 0 ; i < datasEmp.length -1 ; i++){
			if(this.datasEmp[i].equalsIgnoreCase("-")){
				this.datasEmp[i]  = this.datasEmp[i+1];
				this.datasEmp[i+1] = "-";
			}
		}
	}

	public String retStringEmprestimos(){

		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		String user = toEmprestimo();
		String content = "";

		int qtdEmp = qtdEmprestimos();

		switch (qtdEmp) {

			case 1:
				content = user+ "\n" +
						"Livros Emprestados: \n" +
						livrosEmp[0] + "      " + datasEmp[0];
				break;

			case 2:
				if (datasEmp[0] == datasEmp[1]) {
					content = user+ "\n" +
							"Livros Emprestados em "+datasEmp[0] +": "+"\n" +
							livrosEmp[0]+"\n" +
							livrosEmp[1];
				}

				else {
					content = user+ "\n" +
							"Livros Emprestados em "+datasEmp[0] +": "+"\n" +
							livrosEmp[0]+"\n";

					content+= user+ "\n" +
							"Livros Emprestados em "+datasEmp[1] +": "+"\n" +
							livrosEmp[1]+"\n";
				}
				break;

			case 3:

				if(datasEmp[0] == datasEmp[1]) {

					content = user + "\n" +
							"Livros Emprestados em " + datasEmp[0] + ": " + "\n" +
							livrosEmp[0] + "\n" +
							livrosEmp[1] + "\n";

					if (datasEmp[1] == datasEmp[2]) {
						content += livrosEmp[2] + "\n";
					} else {
						content += user + "\n" +
								"Livros Emprestados em " + datasEmp[2] + ": " + "\n" +
								livrosEmp[2] + "\n";
					}

				}else{
					content = user + "\n" +
							"Livros Emprestados em " + datasEmp[0] + ": " + "\n" +
							livrosEmp[0] + "\n";

					if(datasEmp[1] == datasEmp[2]){

						content += user + "\n" +
								"Livros Emprestados em " + datasEmp[1] + ": " + "\n" +
								livrosEmp[1] + "\n" +
								livrosEmp[2] + "\n";
					}
					else {
						content = user + "\n" +
								"Livros Emprestados em " + datasEmp[1] + ": " + "\n" +
								livrosEmp[1] + "\n";

						content = user + "\n" +
								"Livros Emprestados em " + datasEmp[2] + ": " + "\n" +
								livrosEmp[2] + "\n";
					}
				}




		}

		return content;
	}




























	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String[] getLivrosEmp() {
		return livrosEmp;
	}

	public void setLivrosEmp(String[] livrosEmp) {
		this.livrosEmp = livrosEmp;
	}

	public String[] getDatasEmp() {
		return datasEmp;
	}

	public void setDatasEmp(String[] datasEmp) {
		this.datasEmp = datasEmp;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getDataIng() {
		return dataIng;
	}

	public void setDataIng(String dataIng) {
		this.dataIng = dataIng;
	}
}