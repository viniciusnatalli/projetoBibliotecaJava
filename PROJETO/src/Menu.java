import javax.swing.*;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
public class Menu {

    Scanner scan = new Scanner(System.in);
    private int escolha = -1;
    private boolean entradaValida;
    private Biblioteca biblioteca;

    public Menu(Biblioteca biblioteca) {
        this.biblioteca = biblioteca;

        validarEntrada();
    }

    public void exibir(){

        try {
            this.escolha = (Integer.parseInt(JOptionPane.showInputDialog("BEM VINDO AO MENU! Pressione::\n" +
                    "1. Emprestar\n" +
                    "2. Devolver \n" +
                    "3. Cadastros\n" +
                    "4. Relatorios\n" +
                    "5. Quitar d�vida \n" +
                    "0. SAIR")));
        }catch (NumberFormatException e){
            JOptionPane.showMessageDialog(null, "Favor inserir valor v�lido!");
        }

    }

    public int menuCadastros() throws NullPointerException{
        return Integer.parseInt(JOptionPane.showInputDialog("1 - Cadastro de funcionarios \n" +
                "2 - Cadastro de alunos \n" +
                "3 - Cadastro de professores \n" +
                "4 - Cadastro de livros \n" +
                "5 - Cadastro de periodicos").trim());
    }


    public void cadastroFunc() throws NullPointerException{

        String matricula = JOptionPane.showInputDialog("Informe a matricula: ").trim();
        String nome = JOptionPane.showInputDialog("Informe o nome: ").trim();
        String endereco = JOptionPane.showInputDialog("Informe o endereco:").trim();
        String dataIng = JOptionPane.showInputDialog("Informe a data de ingresso: ").trim();
        String setor = JOptionPane.showInputDialog("Informe o setor: ").trim();
        String login = JOptionPane.showInputDialog("Informe o login:").trim();
        String senha = JOptionPane.showInputDialog("Informe a senha: ").trim();

        Funcionario funcionario = new Funcionario(matricula, nome, endereco, dataIng, setor, login, senha);
        this.biblioteca.adicionarFuncionario(funcionario);


    }

    public void cadastroAluno() throws NullPointerException{
        String matricula = JOptionPane.showInputDialog("Informe a matricula: ").trim();
        String nome = JOptionPane.showInputDialog("Informe o nome: ").trim();
        String endereco = JOptionPane.showInputDialog("Informe o endereco:").trim();
        String curso =  JOptionPane.showInputDialog("Informe o curso:").trim();
        String dataIng = JOptionPane.showInputDialog("Informe a data de ingresso: ").trim();

        Aluno aluno = new Aluno(matricula, nome, endereco, curso, dataIng, 0.0);
        this.biblioteca.adicionarAluno(aluno);
    }

    public void cadastroProfessor() throws NullPointerException{
        String matricula = JOptionPane.showInputDialog("Informe a matricula: ").trim();
        String nome = JOptionPane.showInputDialog("Informe o nome: ").trim();
        String endereco = JOptionPane.showInputDialog("Informe o endereco:").trim();
        String dataIng = JOptionPane.showInputDialog("Informe a data de ingresso: ").trim();
        String setor = JOptionPane.showInputDialog("Informe o setor: ").trim();

        Professor professor = new Professor(matricula, nome, endereco, dataIng, setor);
        this.biblioteca.adicionarProfessor(professor);
    }

    public void cadastroLivro() throws NullPointerException{
        System.out.println("Informe o codigo");
        String codigo = scan.nextLine();
        System.out.println("Informe o autor");
        String autor = scan.nextLine();
        System.out.println("Informe o titulo");
        String titulo = scan.nextLine();
        System.out.println("Informe o tipo (L/P)");
        String tipo = scan.nextLine();
        int quantidade = 2;
        System.out.println("Informe a editora");
        String editora = scan.nextLine();
        System.out.println("Informe o ano de publicacao");
        String anoPub = scan.nextLine();
        System.out.println("Informe o issn");
        String issn = scan.nextLine();
        LivroComum livro = new LivroComum(codigo, autor, titulo, tipo, quantidade, editora, anoPub, issn);
        this.biblioteca.adicionarLivro(livro);
    }

    public void cadastroPeriodico() throws NullPointerException {
        String codigo = JOptionPane.showInputDialog("Informe o codigo: ").trim();
        String autor = JOptionPane.showInputDialog("Informe o autor: ").trim();
        String titulo = JOptionPane.showInputDialog("Informe o titulo: ").trim();
        String tipo = JOptionPane.showInputDialog("Informe o tipo: ").trim();
        int quantidade = 2;
        double fatorImpacto = Double.parseDouble(JOptionPane.showInputDialog("Informe o fator de impacto:"));
        LivroPeriodico periodico = new LivroPeriodico(codigo, autor, titulo, tipo, quantidade, fatorImpacto);
        this.biblioteca.adicionarPeriodico(periodico);
    }

    public int menuRelatorios() throws NullPointerException{
        return Integer.parseInt(JOptionPane.showInputDialog("1 - Livros Cadastrados \n" +
                "2 - Emprestimos realizados  \n" +
                "3 - Funcionarios cadastrados \n" +
                "4 - Alunos cadastrados \n" +
                "5 - Imprimir multa individual").trim());
    }




    public int getEscolha() throws NullPointerException{
        return escolha;
    }

    public void setEscolha(int escolha) {
        this.escolha = escolha;
    }

    public boolean isEntradaValida() {
        return entradaValida;
    }


    public void validarEntrada() throws NullPointerException{

        ArrayList<Funcionario> funcionarios = biblioteca.getFuncionarios();

        if (funcionarios.size() == 0 ) {
            JOptionPane.showMessageDialog(null, "nenhum funcionario cadastrado! sera necessario entrar com usuario padrao");
        }

        for (int tentativa = 0; tentativa < 3 && entradaValida == false; tentativa++) {


            String loginInserido = JOptionPane.showInputDialog("Login: ").trim();

            int index = -1;

            for (int i = 0; i < funcionarios.size() ; i++) {
                if (loginInserido.equals(funcionarios.get(i).getLogin())) {
                    index = i;
                    break;
                }
            }


            if (loginInserido.equals("admin")) {
                this.entradaValida = true;

            }else {

                if (index == -1) {
                    JOptionPane.showMessageDialog(null, "Funcionario nao encontrado!");
                } else {

                    String senhaInserida = JOptionPane.showInputDialog("Senha: ").trim();


                    if (senhaInserida.equals(funcionarios.get(index).getSenha())) {
                        this.entradaValida = true;
                        this.escolha = -1;
                    } else {


                        JOptionPane.showMessageDialog(null, "Senha invalida");
                    }
                }
            }
        }
    }


    public void emprestimo() throws ParseException, FileNotFoundException,NullPointerException {



        String nomePesq = JOptionPane.showInputDialog("Insira o nome do usuario: ").trim();
        Usuario usuario = biblioteca.localizarUsuario(nomePesq);

        if(usuario == null){
            JOptionPane.showMessageDialog(null, "Usuario nao encontrado!");
        }
        else {

            if (usuario instanceof Aluno) {
                usuario = (Aluno) usuario;
            }

            int condicao = usuario.retCondicao();
            if (condicao > 0) {

                String livroPesq = JOptionPane.showInputDialog("Insira o titulo: ").trim();
                Livro livro = biblioteca.localizarLivro(livroPesq);

                if (livro == null) {
                    JOptionPane.showMessageDialog(null, "Livro nao encontrado!");
                } else {
                    int estoque = livro.getQuantidade();

                    if (estoque == 0) {
                        JOptionPane.showMessageDialog(null, "Livro indisponivel para empréstimo. \n" +
                                "MOTIVO: ESTOQUE = 0");
                    } else {

                        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                        int qtdEmp = usuario.qtdEmprestimos();

                        usuario.getLivrosEmp()[qtdEmp] = livro.getTitulo();
                        usuario.getDatasEmp()[qtdEmp] = dateFormat.format(new Date());
                        livro.setQuantidade(livro.getQuantidade()-1);

                        JOptionPane.showMessageDialog(null, "Emprestimo Realizado!");
                        String emprestimo = retStringEmprestimo(usuario,livro);
                        getBiblioteca().gravarHistorico(emprestimo);

                        biblioteca.atualizaArquivos();



                    }
                }
            }
        }

    }

    public void devolucao() throws FileNotFoundException,NullPointerException {

        String nomePesq = JOptionPane.showInputDialog("Insira o nome do usuario: ").trim();
        Usuario usuario = biblioteca.localizarUsuario(nomePesq);

        if(usuario == null){
            JOptionPane.showMessageDialog(null, "Usuario nao encontrado!");
        }
        else {

            int qtdEmp = usuario.qtdEmprestimos();

            if(qtdEmp == 0) {
                JOptionPane.showMessageDialog(null, "Usuario Inserido nao possui livros emprestados");
            }
            else{

                int esc = Integer.parseInt(JOptionPane.showInputDialog("Informe qual livro deseja devolver: \n" +
                        usuario.retStringLivrosEmprestados().trim()));

                if (esc < 0 || esc > qtdEmp){
                    JOptionPane.showMessageDialog(null, "Valor invalido!");
                }

                else{
                    Livro livro = biblioteca.devolucaoPorIndex(usuario,esc);

                    JOptionPane.showMessageDialog(null, "Devolucao concluida");
                    String devolucao = retStringDevolucao(usuario,livro);
                    biblioteca.gravarHistorico(devolucao);
                    biblioteca.atualizaArquivos();

                }




            }


        }
    }

    public void quitarDivida() throws ParseException, FileNotFoundException,NullPointerException {

        String nomePesq = JOptionPane.showInputDialog("Insira o nome do usuario: ").trim();
        Aluno aluno = biblioteca.localizarAluno(nomePesq);

        if(aluno == null){
            JOptionPane.showMessageDialog(null, "Usuario nao encontrado!");
        }
        else {

            if(aluno.getMulta() == 0){
                JOptionPane.showMessageDialog(null, "Aluno não possui multa!");
            }
            else{



                int esc = Integer.parseInt(JOptionPane.showInputDialog(null, ("Livros Com atraso: \n" +
                        aluno.retStringLivrosComAtraso()).trim() +"\n" +
                        "Deseja Quitar divida? \n" +
                        "1.SIM \n" +
                        "0.NAO"));

                if(esc == 1) {

                    ArrayList<String> listaLivrosEmp = aluno.retLivrosComAtraso();

                    for (int i = 0; i < listaLivrosEmp.size(); i++) {

                        String sLivro = listaLivrosEmp.get(i);

                        Livro livro = biblioteca.localizarLivro(sLivro);
                        livro.setQuantidade(livro.getQuantidade() + 1);

                        JOptionPane.showMessageDialog(null, "Devolucao concluida");

                        String devolucao = retStringDevolucao(aluno, livro);
                        biblioteca.gravarHistorico(devolucao);
                    }

                    aluno.quitarDivida();
                    JOptionPane.showMessageDialog(null, "Multa Quitada!");
                    biblioteca.atualizaArquivos();
                }
            }
        }

    }


    public void exibeHistorico() throws FileNotFoundException {
        System.out.println(biblioteca.retStringHistorico());
    }

    public void exibeLivros() throws FileNotFoundException {
        System.out.println(biblioteca.retStringLivros());

    }

    public void exibeFuncionarios() throws FileNotFoundException {
        System.out.println(biblioteca.retStringFuncionarios());
    }

    public void exibeAlunos() throws FileNotFoundException {
        System.out.println(biblioteca.retStringAlunos());
    }

    public void multaIndividual() throws ParseException {
        String nomePesq = JOptionPane.showInputDialog("Insira o nome do usuário: ").trim();
        Usuario usuario = biblioteca.localizarUsuario(nomePesq);

        if(usuario == null){
            JOptionPane.showMessageDialog(null, "Usuario não encontrado!");
        }
        else {

            if (usuario instanceof Aluno) {
                usuario = (Aluno) usuario;
            }

            int condicao = usuario.retCondicao(); 


        }
    }

    public String retStringDevolucao(Usuario user, Livro livro){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        String data =  dateFormat.format(new Date());

        String newContent = "OPERACAO: DEVOLUCAO \n" +
                "Usuario: "+user.getNome() +"\n" +
                "Livro: "+livro.getTitulo() +"\n" +
                "Data: "+data;

        return newContent;
    }

    public String retStringEmprestimo(Usuario user,Livro livro){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        String data =  dateFormat.format(new Date());

        String newContent = "OPERACAO: EMPRESTIMO \n" +
                "Usuario: "+user.getNome() +"\n" +
                "Livro: "+livro.getTitulo() +"\n" +
                "Data: "+data;

        return newContent;
    }

    public Biblioteca getBiblioteca() {
        return biblioteca;
    }


}
