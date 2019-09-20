import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Biblioteca {

    Scanner scan = new Scanner(System.in);

    private ArrayList<Usuario> usuarios = new ArrayList<>();
    private ArrayList<Livro> livros = new ArrayList<>();

    private ArrayList<LivroComum> livroComums;
    private ArrayList<LivroPeriodico> livroPeriodicos;
    private ArrayList<Aluno> alunos;
    private ArrayList<Professor> professores;
    private ArrayList<Funcionario> funcionarios;

    private File fileLivros;
    private File filePeriodicos;
    private File fileAlunos;
    private File fileProfessores;
    private File fileFuncionarios;

    private Arquivo arqLivros;
    private Arquivo arqPeriodicos;
    private Arquivo arqAlunos;
    private Arquivo arqProfessores;
    private Arquivo arqFuncionarios;

    Biblioteca(String dir1, String dir2, String dir3, String dir4, String dir5) throws IOException{
        this.fileLivros = new File(dir1);
        this.filePeriodicos = new File(dir2);
        this.fileAlunos = new File(dir3);
        this.fileProfessores = new File(dir4);
        this.fileFuncionarios = new File(dir5);

        this.arqLivros = new Arquivo(fileLivros);
        this.arqPeriodicos = new Arquivo(filePeriodicos);
        this.arqAlunos = new Arquivo(fileAlunos);
        this.arqProfessores = new Arquivo(fileProfessores);
        this.arqFuncionarios = new Arquivo(fileFuncionarios);

    }


    public static void escreverPara(String nomeArq, String conteudo) {
        try {
            File arq = new File(nomeArq);

            // Se o arquivo nao existe, entao ele cria.
            if (!arq.exists()) {
                arq.createNewFile();
            }

            PrintWriter esc = new PrintWriter(new FileWriter(arq, true));
            BufferedWriter escritor = new BufferedWriter(esc);
            escritor.append(conteudo);
            escritor.close();

            System.out.println("Concluido.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean sucessoAoImportarArquivo() throws IOException{
        int contEnt = 0;

        if(this.fileLivros.exists()) {
            this.livroComums = arqLivros.importarLivrosComuns();
            contEnt++;
        }

        if(this.filePeriodicos.exists()){
            this.livroPeriodicos = arqPeriodicos.importarPeriodicos();
            contEnt++;
        }


        if(this.fileAlunos.exists()) {
            alunos = arqAlunos.importarAlunos();
            contEnt++;
        }


        if(fileProfessores.exists()) {
            professores = arqProfessores.importarProfessores();
            contEnt++;
        }


        if(fileFuncionarios.exists()) {
            funcionarios = arqFuncionarios.importarFuncionarios();
            contEnt++;
        }

        if(contEnt == 5) {
            return true;
        }
        else {
            return false;
        }
    }

    public void criaListaUsuarios() {
        this.usuarios = new ArrayList<>();

        for (int i = 0; i < alunos.size(); i++) {
            usuarios.add(this.alunos.get(i));
        }

        for (int i = 0; i < professores.size(); i++) {
            usuarios.add(this.professores.get(i));
        }

        for (int i = 0; i < funcionarios.size(); i++) {
            usuarios.add(this.funcionarios.get(i));
        }
    }


    public void deletaListaUsuarios(){
        this.usuarios.clear();
    }

    public Usuario localizarUsuario(String nomePesq){

        Usuario usuario = null;

        criaListaUsuarios();

        for(int i = 0 ; i < this.usuarios.size() ; i++) {
            String userName = this.usuarios.get(i).getNome();
            if(nomePesq.equalsIgnoreCase(userName)){
                usuario = this.usuarios.get(i);
                break;
            }
        }

        deletaListaUsuarios();
        return usuario;
    }

    public Aluno localizarAluno(String nomePesq){

        Aluno aluno = null;

        for(int i = 0 ; i < this.alunos.size() ; i++) {
            String userName = this.alunos.get(i).getNome();
            if(nomePesq.equalsIgnoreCase(userName)){
                aluno = this.alunos.get(i);
                break;
            }
        }


        return aluno;
    }

    public void criaListaLivros(){

        for (int i = 0; i < livroComums.size(); i++) {
            this.livros.add(this.livroComums.get(i));
        }

        for (int i = 0; i < livroPeriodicos.size(); i++) {
            this.livros.add(this.livroPeriodicos.get(i));
        }
    }

    public void deletaListaLivros(){
        this.livros.clear();
    }



    public Livro localizarLivro(String input) throws NullPointerException{
        criaListaLivros();

        Livro livro = null;

        for (int i = 0; i < livros.size() ; i++) {

            if (input.equalsIgnoreCase(livros.get(i).getTitulo())) {
                livro = livros.get(i);
                break;
            }
        }

        if(livro != null) {
            JOptionPane.showMessageDialog(null, "Livro Encontrado!");
        }
        else {
            JOptionPane.showMessageDialog(null, "Livro Nao Encontrado!");
        }

        deletaListaLivros();
        return livro;
    }

    public void gravarHistorico(String newContent) throws FileNotFoundException {

        File historico = new File("Historico.txt");
        Scanner scanner = new Scanner(historico);

        String content = "";

        while (scanner.hasNext()) {
            content += scanner.nextLine() +"\n";
        }
        content += "\n"+newContent;

        PrintWriter writer = new PrintWriter(historico);

        writer.print(content);
        writer.close();
    }

    public void adicionarFuncionario(Funcionario funcionario) {
        funcionarios.add(funcionario);
        escreverPara("Funcionarios.txt", funcionario.novo());
    }

    public void adicionarAluno(Aluno aluno) {
        alunos.add(aluno);
        escreverPara("Alunos.txt", aluno.novo());
    }

    public void adicionarProfessor(Professor professor) {
        professores.add(professor);
        escreverPara("Professores.txt", professor.novo());
    }

    public void adicionarLivro(LivroComum livro) {
        livroComums.add(livro);
        escreverPara("Livros.txt", livro.novo());
    }

    public void adicionarPeriodico(LivroPeriodico periodico) {
        livroPeriodicos.add(periodico);
        escreverPara("Periodicos.txt", periodico.novo());
    }

    public Livro devolucaoPorIndex(Usuario usuario, int escolha){
        escolha--;

        String livroPesq = usuario.livrosEmp[escolha];

        Livro livro = localizarLivro(livroPesq);
        usuario.devolver(escolha);
        livro.setQuantidade(livro.getQuantidade() +1);

        return livro;
    }


    public String retStringHistorico() throws FileNotFoundException {

        File file = new File("Historico.txt");
        Scanner scan = new Scanner(file);
        String a = "";

        while (scan.hasNext()){
            a+= "\n" + scan.next() + "\n";

        }
        scan.close();
        file = null;

        return a;
    }

    public void atualizaArquivos() throws FileNotFoundException {

        File arqLivros = new File("Livros.txt");
        PrintWriter writer = new PrintWriter(arqLivros);

        for(int i = 0 ; i < livroComums.size() ; i++){
            String content = livroComums.get(i).novo();
            writer.print(content);
        }
        writer.close();
        arqLivros = null;
        //==============================

        File arqPeriodicos = new File("Periodicos.txt");
        writer = new PrintWriter(arqPeriodicos);

        for(int i = 0 ; i < livroPeriodicos.size() ; i++){
            String content = livroPeriodicos.get(i).novo();
            writer.print(content);
        }
        writer.close();
        arqPeriodicos = null;
        //================

        File arqAlunos = new File("Alunos.txt");
        writer = new PrintWriter(arqAlunos);

        for(int i = 0 ; i < alunos.size() ; i++){
            String content = alunos.get(i).novo();
            writer.print(content);
        }
        writer.close();
        arqAlunos = null;

        //==================

        File arqProfessores = new File("Professores.txt");
        writer = new PrintWriter(arqProfessores);

        for(int i = 0 ; i < professores.size() ; i++){
            String content = professores.get(i).novo();
            writer.print(content);
        }
        writer.close();
        arqProfessores = null;

        //===================

        File arqFuncionarios = new File("Funcionarios.txt");
        writer = new PrintWriter(arqFuncionarios);

        for(int i = 0 ; i < funcionarios.size() ; i++){
            String content = funcionarios.get(i).novo();
            writer.print(content);
        }
        writer.close();
        arqProfessores = null;

    }

    public String retStringLivros() {
        return Arrays.toString(livroComums.toArray());
    }

    public String retStringFuncionarios() {
        return Arrays.toString(funcionarios.toArray());
    }


    public String retStringAlunos() {
        return Arrays.toString(alunos.toArray());
    }




    public Scanner getScan() {
        return scan;
    }

    public void setScan(Scanner scan) {
        this.scan = scan;
    }

    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(ArrayList<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public ArrayList<Livro> getLivros() {
        return livros;
    }

    public void setLivros(ArrayList<Livro> livros) {
        this.livros = livros;
    }

    public ArrayList<LivroComum> getLivroComums() {
        return livroComums;
    }

    public void setLivroComums(ArrayList<LivroComum> livroComums) {
        this.livroComums = livroComums;
    }

    public ArrayList<LivroPeriodico> getLivroPeriodicos() {
        return livroPeriodicos;
    }

    public void setLivroPeriodicos(ArrayList<LivroPeriodico> livroPeriodicos) {
        this.livroPeriodicos = livroPeriodicos;
    }

    public ArrayList<Aluno> getAlunos() {
        return alunos;
    }

    public void setAlunos(ArrayList<Aluno> alunos) {
        this.alunos = alunos;
    }

    public ArrayList<Professor> getProfessores() {
        return professores;
    }

    public void setProfessores(ArrayList<Professor> professores) {
        this.professores = professores;
    }

    public ArrayList<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public void setFuncionarios(ArrayList<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
    }

    public File getFileLivros() {
        return fileLivros;
    }

    public void setFileLivros(File fileLivros) {
        this.fileLivros = fileLivros;
    }

    public File getFilePeriodicos() {
        return filePeriodicos;
    }

    public void setFilePeriodicos(File filePeriodicos) {
        this.filePeriodicos = filePeriodicos;
    }

    public File getFileAlunos() {
        return fileAlunos;
    }

    public void setFileAlunos(File fileAlunos) {
        this.fileAlunos = fileAlunos;
    }

    public File getFileProfessores() {
        return fileProfessores;
    }

    public void setFileProfessores(File fileProfessores) {
        this.fileProfessores = fileProfessores;
    }

    public File getFileFuncionarios() {
        return fileFuncionarios;
    }

    public void setFileFuncionarios(File fileFuncionarios) {
        this.fileFuncionarios = fileFuncionarios;
    }

    public Arquivo getArqLivros() {
        return arqLivros;
    }

    public void setArqLivros(Arquivo arqLivros) {
        this.arqLivros = arqLivros;
    }

    public Arquivo getArqPeriodicos() {
        return arqPeriodicos;
    }

    public void setArqPeriodicos(Arquivo arqPeriodicos) {
        this.arqPeriodicos = arqPeriodicos;
    }

    public Arquivo getArqAlunos() {
        return arqAlunos;
    }

    public void setArqAlunos(Arquivo arqAlunos) {
        this.arqAlunos = arqAlunos;
    }

    public Arquivo getArqProfessores() {
        return arqProfessores;
    }

    public void setArqProfessores(Arquivo arqProfessores) {
        this.arqProfessores = arqProfessores;
    }

    public Arquivo getArqFuncionarios() {
        return arqFuncionarios;
    }

    public void setArqFuncionarios(Arquivo arqFuncionarios) {
        this.arqFuncionarios = arqFuncionarios;
    }
}
