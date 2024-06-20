package program;

import model.Aluno;
import model.Disciplina;
import model.Professor;
import model.Turma;
import service.*;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu {

    private final Util util;

    public Menu(Util util) {
        this.util = util;
    }

    public void ini(){

        DisciplinaService dS = new DisciplinaService();
        TurmaService tS = new TurmaService();
        AlunoService aS = new AlunoService();
        ProfessorService pS = new ProfessorService();
        ProfessorDisciplinaService pDS = new ProfessorDisciplinaService();
        ReservaService rS = new ReservaService();
        LaboratorioService lS = new LaboratorioService();
        Scanner sc = new Scanner(System.in);

        System.out.println();
        System.out.println("Olá, bem vindo ao sistema de reservas de laboratório da universidade");
        System.out.println();
        System.out.println("Qual operação deseja realizar?");
        System.out.println();
        System.out.println("Digite 1 para ver professores");
        System.out.println("Digite 2 para ver alunos");
        System.out.println("Digite 3 para ver disciplinas");
        System.out.println("Digite 4 para ver laboratorios");
        System.out.println("Digite 5 para ver turmas");
        System.out.println("Digite 6 para ver as disciplinas dos professores");
        System.out.println("Digite 7 para fazer uma solicitacao de reserva");
        System.out.println("Digite 8 para ver a lista de reservas");
        System.out.println("Digite 9 para resetar o Banco de Dados");
        System.out.println("Digite 10 para finalizar o programa");

        int op = sc.nextInt();
        switch (op) {
            case 1:

                ArrayList<Professor> profLista = pS.findAll();
                for (Professor p : profLista) {
                    System.out.println(p.toString());
                }

                System.out.println("Digite 1 para criar");
                System.out.println("Digite 2 para alterar");
                System.out.println("Digite 3 para remover");




                break;
            case 2:
                ArrayList<Aluno> alnLista = aS.findAll();
                for (Aluno a : alnLista) {
                    System.out.println(a.toString());
                }

                System.out.println("Digite 1 para criar");
                System.out.println("Digite 2 para alterar");
                System.out.println("Digite 3 para remover");
                System.out.println("Digite 4 para voltar ao menu principal");
                int op2 = sc.nextInt();
                switch (op2) {
                    case 1:
                        System.out.println("Digite o nome do aluno");
                        String nome = sc.next();
                        System.out.println("Digite o id da turma do aluno");
                        Turma turma = tS.findTurmaById(sc.nextInt());
                        aS.criarAluno(nome, turma, true);
                        System.out.println("Aluno criado com sucesso!");
                        break;
                    case 2:
                        System.out.println("Digite a matricula do aluno que deseja atualizar");
                        int matricula = sc.nextInt();
                        System.out.println("Digite o nome do aluno");
                        nome = sc.next();
                        System.out.println("Digite o id da turma do aluno");
                        turma = tS.findTurmaById(sc.nextInt());
                        aS.atualizarAluno(matricula, nome, turma, true);
                        break;
                    case 3:
                        System.out.println("Digite a matricula do aluno que deseja excluir");
                        int matricula3 = sc.nextInt();
                        aS.deletarAluno(matricula3);
                        break;
                    case 4:
                        ini();
                        break;
                }
                ini();

                break;
            case 3:
                ArrayList<Disciplina>  disLista = dS.findAll();
                for (Disciplina d : disLista) {
                    System.out.println(d.toString());
                }
                break;
            case 4:


            case 5:
                //SolicitacaoService solicitar = new solicitacaoService(solicitacaoDao);
                //solicitar.logicaSolicitacao();

            case 6:


                // Se tiver alguma turma com o id especificado,faça um select dos alunos com aquela fk dando join com as disciplinas
            case 9:
                System.out.println("Tem certeza que deseja resetar o banco de dados? (s/n)");
                sc.nextLine();
                if (sc.nextLine().equals("s")) {
                util.reset();
                    ini();
                }else{
                    ini();
                }

                break;

        }

    }
}
