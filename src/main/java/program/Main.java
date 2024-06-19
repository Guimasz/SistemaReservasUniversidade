package program;

import config.ConexaoPostgreSQL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;
import service.DisciplinaService;
import dao.DisciplinaDao;
import model.Disciplina;


public class Main {
    public static void main(String[] args) throws SQLException {

    Scanner sc = new Scanner(System.in);
        Inicializar inicio = new Inicializar();
        inicio.start();



        System.out.println();
        System.out.println("Olá, bem vindo ao sistema de reservas de laboratório da universidade");
        System.out.println();
        System.out.println("Qual operação deseja realizar?");
        System.out.println();
        System.out.println("Digite 1 para verificar a lista de professores");
        System.out.println("Digite 2 para verificar a lista de alunos");
        System.out.println("Digite 3 para fazer uma solicitacao de reserva");
        System.out.println();
        int op = sc.nextInt();
        switch (op) {
            case 1:
                inicio.popular();
                //professorService.findAll();

            case 2:
                System.out.println("Digite o numero da turma que deseja exibir");
                int turmaId = sc.nextInt();
                // Se tiver alguma turma com o id especificado,faça um select dos alunos com aquela fk dando join com as disciplinas
            case 3:
                //SolicitacaoService solicitar = new solicitacaoService(solicitacaoDao);
                //solicitar.logicaSolicitacao();

        }






































    }
}

