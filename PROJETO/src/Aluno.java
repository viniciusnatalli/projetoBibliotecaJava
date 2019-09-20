import javax.swing.*;
import java.text.ParseException;
import java.util.ArrayList;

public class Aluno extends Usuario{

    private double multa;
    protected String[] livrosEmp = {"-","-","-"};
	protected String[] datasEmp = {"-","-","-"};
	protected String curso;

    public Aluno(String matricula, String nome, String endereco, String curso , String dataIng, double multa) {
        super(matricula, nome, endereco, dataIng);
        this.multa = multa;
        this.curso = curso;
    }



    public String novo() {

		String a , b ,c , d , e , f;

		a = this.livrosEmp[0];
		b = this.livrosEmp[1];
		c = this.livrosEmp[2];
		d = this.datasEmp[0];
		e = this.datasEmp[1];
		f = this.datasEmp[2];

		return (matricula + ";" + nome + ";" + a +";" + b +";" + c +";" + d +";" + e +";" + f +";" +  endereco + ";" + curso + ";" + dataIng + ";" + multa + ";");
	}
    
    public String toString(){
		return("MATRICULA: "+matricula +"\n"
				+"NOME: "+nome +"\n"
				+ "ENDERECO: "+endereco +"\n"
				+ "CURSO: "+curso +"\n"
				+ "DATA DE INGRESSO: "+dataIng +"\n"
				+ "|||||||||||||||||||||||||||||||||||||||" + "\n");
	}

    @Override
    public int retCondicao() throws ParseException {

        int ret = 1;

        if(multa > 0){
            JOptionPane.showMessageDialog(null, ("Aluno Indisponível para emprestimo.\n" +
                    "MOTIVO: Multa: "+multa));
            ret = -3;
        }
        else{
            ret = super.retCondicao();
        }

        return ret;
    }

    public double getMulta() {
        return multa;
    }

    public void setMulta(double multa) {
        this.multa = multa;
    }

    public ArrayList<String> quitarDivida(){

        ArrayList<String> lista = new ArrayList<>();

        int qtdEmp = qtdEmprestimos();

        this.livrosEmp[0] = "-";
        this.livrosEmp[1] = "-";
        this.livrosEmp[2] = "-";
        this.datasEmp[0] = "-";
        this.datasEmp[1] = "-";
        this.datasEmp[2] = "-";
        this.multa = 0;

        return lista;
    }

}

