
import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class Principal {

    public static void main(String[] args) throws IOException, ParseException {

        Scanner scan = new Scanner(System.in);
        Biblioteca bib = new Biblioteca("Livros.txt","Periodicos.txt","Alunos.txt","Professores.txt","Funcionarios.txt");

        if(bib.sucessoAoImportarArquivo()){

            Menu menu = new Menu(bib);

            if(menu.isEntradaValida()){

                while (menu.getEscolha() != 0){
                    menu.exibir();

                    switch (menu.getEscolha()){

                        case 1: menu.emprestimo();break;

                        case 2: menu.devolucao();break;

                        case 3: int e = menu.menuCadastros();

                            switch (e){

                                case 1: menu.cadastroFunc(); break;

                                case 2: menu.cadastroAluno(); break;

                                case 3: menu.cadastroProfessor(); break;

                                case 4: menu.cadastroLivro(); break;

                                case 5: menu.cadastroPeriodico(); break;



                            }
                            break;

                        case 4: int a = menu.menuRelatorios();

                            switch (a) {

                                case 1: menu.exibeLivros();break;

                                case 2: menu.exibeHistorico(); break;

                                case 3: menu.exibeFuncionarios(); break;

                                case 4: menu.exibeAlunos(); break;

                                case 5: menu.quitarDivida(); break;

                            }
                            break;

                        case 5: menu.quitarDivida();
                            break;



                    }


                }

            }
        }else{
            JOptionPane.showMessageDialog(null,"Erro ao importar arquivos");
        }

        JOptionPane.showMessageDialog(null,"Fim do Programa");
    }

}


