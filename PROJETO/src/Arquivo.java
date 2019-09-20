import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;


public class Arquivo{


    File file;
    PrintWriter writer;

    Arquivo(File file) throws IOException{
        this.file = file;
    }

    public ArrayList<LivroComum> importarLivrosComuns() throws IOException{

        ArrayList<LivroComum> livrosComuns = new ArrayList();
        String[] vetArq = retVetArq();

        for(int i = 0; i < (vetArq.length -1) ; i++){

            String codigo = vetArq[i++];
            String autor = vetArq[i++];
            String titulo = vetArq[i++];
            String tipo = vetArq[i++];
            int quantidade = Integer.parseInt(vetArq[i++]);
            String editora = vetArq[i++];
            String anoPub = (vetArq[i++]);
            String issn = vetArq[i];


            LivroComum livroComum = new LivroComum( codigo,autor,titulo,tipo,quantidade,editora,anoPub,issn);
            livrosComuns.add(livroComum);
        }

        return livrosComuns;
    }

    public ArrayList<LivroPeriodico> importarPeriodicos() throws IOException{

        ArrayList<LivroPeriodico> livrosPeriodicos = new ArrayList();
        String[] vetArq = retVetArq();

        for(int i = 0; i < (vetArq.length -1) ; i++){

            String codigo = vetArq[i++];
            String autor = vetArq[i++];
            String titulo = vetArq[i++];
            String tipo = vetArq[i++];
            int quantidade = Integer.parseInt(vetArq[i++]);
            double fatorImpacto = Double.parseDouble(vetArq[i]);


            LivroPeriodico livroPeriodico = new LivroPeriodico(codigo,autor,titulo,tipo,quantidade,fatorImpacto);
            livrosPeriodicos.add(livroPeriodico);
        }

        return livrosPeriodicos;
    }

    public ArrayList<Aluno> importarAlunos() throws IOException{

        ArrayList<Aluno> alunos = new ArrayList();
        String[] vetArq = retVetArq();

        for(int i = 0; i < (vetArq.length -1) ; i++){

            String matricula = vetArq[i++];
            String nome = vetArq[i++];
            String emp1 = vetArq[i++];
            String emp2 = vetArq[i++];
            String emp3 = vetArq[i++];
            String data1 = vetArq[i++];
            String data2 = vetArq[i++];
            String data3 = vetArq[i++];
            String endereco = vetArq[i++];
            String curso = vetArq[i++];
            String dataIng = vetArq[i++];
            double multa = Double.parseDouble(vetArq[i]);

            Aluno aluno = new Aluno(matricula,nome,endereco,curso,dataIng,multa);
            alunos.add(aluno);

            aluno.preencherArrays(emp1,emp2,emp3,data1,data2,data3);

        }

        return alunos;
    }

    public ArrayList<Professor> importarProfessores() throws IOException{

        ArrayList<Professor> professores = new ArrayList();
        String[] vetArq = retVetArq();

        for(int i = 0; i < (vetArq.length -1) ; i++){

            String matricula = vetArq[i++];
            String nome = vetArq[i++];
            String emp1 = vetArq[i++];
            String emp2 = vetArq[i++];
            String emp3 = vetArq[i++];
            String data1 = vetArq[i++];
            String data2 = vetArq[i++];
            String data3 = vetArq[i++];
            String endereco = vetArq[i++];
            String dataIng = vetArq[i++];
            String setor = vetArq[i];

            Professor professor = new Professor(matricula,nome,endereco,dataIng,setor);
            professores.add(professor);

            professor.preencherArrays(emp1,emp2,emp3,data1,data2,data3);
        }

        return professores;
    }

    public ArrayList<Funcionario> importarFuncionarios() throws IOException{

        ArrayList<Funcionario> funcionarios = new ArrayList();
        String[] vetArq = retVetArq();

        for(int i = 0; i < (vetArq.length -1) ; i++){

            String matricula = vetArq[i++];
            String nome = vetArq[i++];
            String emp1 = vetArq[i++];
            String emp2 = vetArq[i++];
            String emp3 = vetArq[i++];
            String data1 = vetArq[i++];
            String data2 = vetArq[i++];
            String data3 = vetArq[i++];
            String endereco = vetArq[i++];
            String dataIng = vetArq[i++];
            String setor = vetArq[i++];
            String login = vetArq[i++];
            String senha = vetArq[i];

            Funcionario funcionario = new Funcionario(matricula,nome,endereco,dataIng,setor,login,senha);
            funcionarios.add(funcionario);

            funcionario.preencherArrays(emp1,emp2,emp3,data1,data2,data3);
        }

        return funcionarios;
    }


    private String[] retVetArq() throws IOException{
        Scanner scan = new Scanner(file);
        String sArq = "";
        String[] vetArq;

        for(int i = 0 ; scan.hasNext(); i++ ){
            sArq += scan.nextLine().trim();
        }
        vetArq = sArq.split(";");
        scan.close();
        return vetArq;
    }

}
