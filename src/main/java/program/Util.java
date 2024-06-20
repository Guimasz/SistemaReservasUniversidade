package program;

import config.ConexaoPostgreSQL;
import service.*;

import java.sql.*;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Scanner;

import model.*;

public class Util {
    private Connection conexao;

    public Util() {
    }

    public Util(Connection connection) {
        this.conexao = connection;
    }

    public void start() throws SQLException {
        conectar();
        createTables();

    }

    public void reset() {
        try {
            deleteAllTables();
            createTables();
            popular();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void conectar() {
        try {
            conexao = ConexaoPostgreSQL.obterConexao();
            if (conexao != null) {
                System.out.println("Conexão estabelecida com sucesso!");
                // Aqui você pode realizar operações no banco de dados

            } else {
                System.out.println("Falha ao estabelecer conexão.");
                return;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao conectar ao banco de dados: " + e.getMessage());
            return;
        }
    }

    private void desconectar() throws SQLException {
        conexao.close();
        System.out.println("Banco Desconectado com sucesso!");
    }



    private void createTables() throws SQLException {
        try (Statement statement = conexao.createStatement()) {
            String createDisciplinaTable = "CREATE TABLE IF NOT EXISTS disciplina(" +
                    "id serial PRIMARY KEY NOT NULL," +
                    "sigla varchar(50) NOT NULL," +
                    "descricao varchar(50)," +
                    "status boolean" +
                    ");";

            String createProfessorTable = "CREATE TABLE IF NOT EXISTS professor (" +
                    "id serial PRIMARY KEY NOT NULL," +
                    "nome varchar(50) NOT NULL," +
                    "status boolean" +
                    ");";

            String createProfessorDisciplinaTable = "CREATE TABLE IF NOT EXISTS professorDisciplina (" +
                    "idProfessor INTEGER NOT NULL," +
                    "idDisciplina INTEGER NOT NULL," +
                    "PRIMARY KEY (idProfessor, idDisciplina)," +
                    "FOREIGN KEY (idDisciplina) REFERENCES disciplina(id) ON DELETE CASCADE," +
                    "FOREIGN KEY (idProfessor) REFERENCES professor(id) ON DELETE CASCADE " +
                    ");";

            String createTurmaTable = "CREATE TABLE IF NOT EXISTS turma (" +
                    "id serial PRIMARY KEY NOT NULL," +
                    "disciplina INTEGER NOT NULL," +
                    "CONSTRAINT fk_disciplina FOREIGN KEY (disciplina) REFERENCES disciplina(id) ON DELETE CASCADE" +
                    ");";

            String createLaboratorioTable = "CREATE TABLE IF NOT EXISTS laboratorio (" +
                    "id serial PRIMARY KEY NOT NULL," +
                    "descricao varchar(200) NOT NULL," +
                    "capacidade INTEGER NOT NULL," +
                    "status boolean" +
                    ");";

            String createReservaTable = "CREATE TABLE IF NOT EXISTS reserva (" +
                    "id serial PRIMARY KEY NOT NULL," +
                    "laboratorio INTEGER NOT NULL," +
                    "professor INTEGER NOT NULL," +
                    "turma INTEGER NOT NULL," +
                    "dataHora timestamp NOT NULL," +
                    "duracao INTEGER," +
                    "aprovada boolean," +
                    "CONSTRAINT fk_lab FOREIGN KEY (laboratorio) REFERENCES laboratorio(id) ON DELETE CASCADE," +
                    "CONSTRAINT fk_prof FOREIGN KEY (professor) REFERENCES professor(id) ON DELETE CASCADE," +
                    "CONSTRAINT fk_turma FOREIGN KEY (turma) REFERENCES turma(id) ON DELETE CASCADE" +
                    ");";

            String createAlunoTable = "CREATE TABLE IF NOT EXISTS aluno (" +
                    "matricula serial PRIMARY KEY NOT NULL," +
                    "nome varchar(50) NOT NULL," +
                    "status boolean," +
                    "turma INTEGER NOT NULL," +
                    "CONSTRAINT fk_turma FOREIGN KEY (turma) REFERENCES turma(id) ON DELETE CASCADE" +
                    ");";

            statement.execute(createDisciplinaTable);
            statement.execute(createProfessorTable);
            statement.execute(createProfessorDisciplinaTable);
            statement.execute(createTurmaTable);
            statement.execute(createLaboratorioTable);
            statement.execute(createReservaTable);
            statement.execute(createAlunoTable);
        }catch (SQLException e){
            e.printStackTrace();
        }
        System.out.println("Tabelas criadas com sucesso!");
    }


    public void deleteAllTables() {
        try {
            DatabaseMetaData metaData = conexao.getMetaData();
            String[] types = {"TABLE"};
            java.sql.ResultSet resultSet = metaData.getTables(null, null, "%", types);

            while (resultSet.next()) {
                String tableName = resultSet.getString(3);
                dropTable(conexao, tableName);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Tabelas deletadas com sucesso!");
    }

    public void dropTable(Connection conexao, String tableName) {
        String sql = "DROP TABLE IF EXISTS " + tableName + " CASCADE";
        try (Statement statement = conexao.createStatement()) {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void popular() throws SQLException {
        try (Statement statement = conexao.createStatement()) {


            DisciplinaService disciplinaService = new DisciplinaService();
            ProfessorService professorService = new ProfessorService();
            LaboratorioService laboratorioService = new LaboratorioService();
            ReservaService reservaService = new ReservaService();
            TurmaService turmaService = new TurmaService();
            AlunoService alunoService = new AlunoService();
            ProfessorDisciplinaService profDisService = new ProfessorDisciplinaService();

            // Primeiro conjunto de objetos
            Disciplina disciplina1 = new Disciplina();
            disciplina1.setDescricao("Matemática");
            disciplina1.setSigla("MAT");
            disciplina1.setStatus(true);
            disciplinaService.criarDisciplina(disciplina1.getSigla(), disciplina1.getDescricao(), disciplina1.isStatus());
            disciplina1.setId(disciplinaService.findDisciplinabyId(1).getId());

            Turma turma1 = new Turma();
            turma1.setDisciplina(disciplina1);
            turmaService.criarTurma(turma1.getDisciplina());
            turma1.setId(turmaService.findTurmaById(1).getId());

            Aluno aluno1 = new Aluno();
            aluno1.setNome("João");
            aluno1.setStatus(true);
            aluno1.setTurma(turma1);
            alunoService.criarAluno(aluno1.getNome(), aluno1.getTurma(), aluno1.isStatus());
            aluno1.setMatricula(alunoService.findAlunobyMatricula(1).getMatricula());

            Professor professor1 = new Professor();
            professor1.setNome("Maria");
            professor1.setStatus(true);
            professorService.criarProfessor(professor1.getNome(), professor1.isStatus());
            professor1.setId(professorService.findProfessorById(1).getId());
            profDisService.criarProfDis(1,1);

            Laboratorio laboratorio1 = new Laboratorio();
            laboratorio1.setCapacidade(30);
            laboratorio1.setDescricao("Laboratório de Informática");
            laboratorio1.setStatus(true);
            laboratorioService.criarLaboratorio(laboratorio1.getDescricao(), laboratorio1.getCapacidade(), laboratorio1.isStatus());
            laboratorio1.setId(laboratorioService.findLaboratorioById(1).getId());

            Reserva reserva1 = new Reserva();
            reserva1.setAprovada(true);
            reserva1.setDataHora(Timestamp.valueOf("2024-10-10 10:00:00").toLocalDateTime());
            reserva1.setTempo(Duration.ofHours(2));
            reserva1.setLaboratorio(laboratorio1);
            reserva1.setProfessor(professor1);
            reserva1.setTurma(turma1);
            reservaService.criarReserva(reserva1.getLaboratorio(), reserva1.getProfessor(), reserva1.getTurma(), reserva1.getDataHora(), reserva1.getTempo());
            reserva1.setId(reservaService.findReservaById(1).getId());


            Disciplina disciplina2 = new Disciplina();
            disciplina2.setDescricao("Física");
            disciplina2.setSigla("FIS");
            disciplina2.setStatus(true);
            disciplinaService.criarDisciplina(disciplina2.getSigla(), disciplina2.getDescricao(), disciplina2.isStatus());
            disciplina2.setId(disciplinaService.findDisciplinabyId(2).getId());

            Professor professor2 = new Professor();
            professor2.setNome("Carlos");
            professor2.setStatus(true);
            professorService.criarProfessor(professor2.getNome(), professor2.isStatus());
            professor2.setId(professorService.findProfessorById(2).getId());
            profDisService.criarProfDis(2,2);


            Laboratorio laboratorio2 = new Laboratorio();
            laboratorio2.setCapacidade(25);
            laboratorio2.setDescricao("Laboratório de Física");
            laboratorio2.setStatus(true);
            laboratorioService.criarLaboratorio(laboratorio2.getDescricao(), laboratorio2.getCapacidade(), laboratorio2.isStatus());
            laboratorio2.setId(laboratorioService.findLaboratorioById(2).getId());

            Turma turma2 = new Turma();
            turma2.setDisciplina(disciplina2);
            turmaService.criarTurma(turma2.getDisciplina());
            turma2.setId(turmaService.findTurmaById(2).getId());

            Reserva reserva2 = new Reserva();
            reserva2.setAprovada(false);
            reserva2.setDataHora(Timestamp.valueOf("2024-11-15 14:00:00").toLocalDateTime());
            reserva2.setTempo(Duration.ofHours(3));
            reserva2.setLaboratorio(laboratorio2);
            reserva2.setProfessor(professor2);
            reserva2.setTurma(turma2);
            reservaService.criarReserva(reserva2.getLaboratorio(), reserva2.getProfessor(), reserva2.getTurma(), reserva2.getDataHora(), reserva2.getTempo());
            reserva2.setId(reservaService.findReservaById(2).getId());

            Aluno aluno2 = new Aluno();
            aluno2.setNome("Ana");
            aluno2.setStatus(true);
            aluno2.setTurma(turma2);
            alunoService.criarAluno(aluno2.getNome(), aluno2.getTurma(), aluno2.isStatus());
            aluno2.setMatricula(alunoService.findAlunobyMatricula(2).getMatricula());


            Disciplina disciplina3 = new Disciplina();
            disciplina3.setDescricao("Química");
            disciplina3.setSigla("QUI");
            disciplina3.setStatus(true);
            disciplinaService.criarDisciplina(disciplina3.getSigla(), disciplina3.getDescricao(), disciplina3.isStatus());
            disciplina3.setId(disciplinaService.findDisciplinabyId(3).getId());

            Professor professor3 = new Professor();
            professor3.setNome("Beatriz");
            professor3.setStatus(true);
            professorService.criarProfessor(professor3.getNome(), professor3.isStatus());
            professor3.setId(professorService.findProfessorById(3).getId());
            profDisService.criarProfDis(3,3);

            Laboratorio laboratorio3 = new Laboratorio();
            laboratorio3.setCapacidade(20);
            laboratorio3.setDescricao("Laboratório de Química");
            laboratorio3.setStatus(true);
            laboratorioService.criarLaboratorio(laboratorio3.getDescricao(), laboratorio3.getCapacidade(), laboratorio3.isStatus());
            laboratorio3.setId(laboratorioService.findLaboratorioById(3).getId());

            Turma turma3 = new Turma();
            turma3.setDisciplina(disciplina3);
            turmaService.criarTurma(turma3.getDisciplina());
            turma3.setId(turmaService.findTurmaById(3).getId());

            Reserva reserva3 = new Reserva();
            reserva3.setAprovada(true);
            reserva3.setDataHora(Timestamp.valueOf("2024-12-20 09:00:00").toLocalDateTime());
            reserva3.setTempo(Duration.ofHours(1));
            reserva3.setLaboratorio(laboratorio3);
            reserva3.setProfessor(professor3);
            reserva3.setTurma(turma3);
            reservaService.criarReserva(reserva3.getLaboratorio(), reserva3.getProfessor(), reserva3.getTurma(), reserva3.getDataHora(), reserva3.getTempo());
            reserva3.setId(reservaService.findReservaById(3).getId());

            Aluno aluno3 = new Aluno();
            aluno3.setNome("Pedro");
            aluno3.setStatus(true);
            aluno3.setTurma(turma3);
            alunoService.criarAluno(aluno3.getNome(), aluno3.getTurma(), aluno3.isStatus());
            aluno3.setMatricula(alunoService.findAlunobyMatricula(3).getMatricula());
            System.out.println("Tabelas populadas com sucesso!");
        }
    }




}









