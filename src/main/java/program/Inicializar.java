package program;

import config.ConexaoPostgreSQL;
import Service.*;

import java.sql.*;
import java.time.Duration;

import model.*;

public class Inicializar {
    private Connection conexao;

    public Inicializar() {
    }

    public Inicializar(Connection connection) {
        this.conexao = connection;
    }

    public void start() throws SQLException {
        conectar();
        createTables();
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
                    "FOREIGN KEY (idDisciplina) REFERENCES disciplina(id)," +
                    "FOREIGN KEY (idProfessor) REFERENCES professor(id)" +
                    ");";

            String createTurmaTable = "CREATE TABLE IF NOT EXISTS turma (" +
                    "id serial PRIMARY KEY NOT NULL," +
                    "disciplina INTEGER NOT NULL," +
                    "CONSTRAINT fk_disciplina FOREIGN KEY (disciplina) REFERENCES disciplina(id)" +
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
                    "CONSTRAINT fk_lab FOREIGN KEY (laboratorio) REFERENCES laboratorio(id)," +
                    "CONSTRAINT fk_prof FOREIGN KEY (professor) REFERENCES professor(id)," +
                    "CONSTRAINT fk_turma FOREIGN KEY (turma) REFERENCES turma(id)" +
                    ");";

            String createAlunoTable = "CREATE TABLE IF NOT EXISTS aluno (" +
                    "matricula serial PRIMARY KEY NOT NULL," +
                    "nome varchar(50) NOT NULL," +
                    "status boolean," +
                    "turma INTEGER NOT NULL," +
                    "CONSTRAINT fk_turma FOREIGN KEY (turma) REFERENCES turma(id)" +
                    ");";

            statement.execute(createDisciplinaTable);
            statement.execute(createProfessorTable);
            statement.execute(createProfessorDisciplinaTable);
            statement.execute(createTurmaTable);
            statement.execute(createLaboratorioTable);
            statement.execute(createReservaTable);
            statement.execute(createAlunoTable);
        }
    }

    private void popular() throws SQLException {
        try (Statement statement = conexao.createStatement()) {
//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------
            Aluno aluno1 = new Aluno();
            Disciplina disciplina1 = new Disciplina();
            Laboratorio laboratorio1 = new Laboratorio();
            Professor professor1 = new Professor();
            Reserva reserva1 = new Reserva();
            Turma turma1 = new Turma();

            disciplina1.setDescricao("Matemática");
            disciplina1.setSigla("MAT");
            disciplina1.setStatus(true);

            professor1.setNome("Maria");
            professor1.setStatus(true);

            laboratorio1.setCapacidade(30);
            laboratorio1.setDescricao("Laboratório de Informática");
            laboratorio1.setStatus(true);

            reserva1.setAprovada(true);
            reserva1.setDataHora(Timestamp.valueOf("2021-10-10 10:00:00").toLocalDateTime());
            reserva1.setTempo(Duration.ofHours(2));
            reserva1.setLaboratorio(laboratorio1);
            reserva1.setProfessor(professor1);
            reserva1.setTurma(turma1);

            turma1.setDisciplina(disciplina1);

            aluno1.setNome("João");
            aluno1.setStatus(true);
            aluno1.setTurma(turma1);


            DisciplinaService disciplinaService = new DisciplinaService();
            ProfessorService professorService = new ProfessorService();
            LaboratorioService laboratorioService = new LaboratorioService();
            ReservaService reservaService = new ReservaService();
            TurmaService turmaService = new TurmaService();
            AlunoService alunoService = new AlunoService();


            disciplinaService.criarDisciplina(disciplina1.getSigla(), disciplina1.getDescricao(), disciplina1.isStatus());
            professorService.criarProfessor(professor1.getNome(), professor1.isStatus());
            laboratorioService.criarLaboratorio(laboratorio1.getDescricao(), laboratorio1.getCapacidade(), laboratorio1.isStatus());
            turmaService.criarTurma(turma1.getDisciplina());
            reservaService.criarReserva(reserva1.getLaboratorio(),reserva1.getTurma(),reserva1.getDataHora(),reserva1.getTempo(),reserva1.isAprovada()); 
            alunoService.criarAluno(aluno1.getNome(),aluno1.getTurma(),aluno1.isStatus());

//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------
            Aluno aluno2 = new Aluno();
            Disciplina disciplina2 = new Disciplina();
            Laboratorio laboratorio2 = new Laboratorio();
            Professor professor2 = new Professor();
            Reserva reserva2 = new Reserva();
            Turma turma2 = new Turma();

            disciplina2.setDescricao("Física");
            disciplina2.setSigla("FIS");
            disciplina2.setStatus(true);

            professor2.setNome("Carlos");
            professor2.setStatus(true);

            laboratorio2.setCapacidade(25);
            laboratorio2.setDescricao("Laboratório de Física");
            laboratorio2.setStatus(true);

            reserva2.setAprovada(false);
            reserva2.setDataHora(Timestamp.valueOf("2021-11-15 14:00:00").toLocalDateTime());
            reserva2.setTempo(Duration.ofHours(3));
            reserva2.setLaboratorio(laboratorio2);
            reserva2.setProfessor(professor2);
            reserva2.setTurma(turma2);

            turma2.setDisciplina(disciplina2);

            aluno2.setNome("Ana");
            aluno2.setStatus(true);
            aluno2.setTurma(turma2);


            DisciplinaService disciplinaService2 = new DisciplinaService();
            ProfessorService professorService2 = new ProfessorService();
            LaboratorioService laboratorioService2 = new LaboratorioService();
            ReservaService reservaService2 = new ReservaService();
            TurmaService turmaService2 = new TurmaService();
            AlunoService alunoService2 = new AlunoService();


            disciplinaService2.criarDisciplina(disciplina2.getSigla(), disciplina2.getDescricao(), disciplina2.isStatus());
            professorService2.criarProfessor(professor2.getNome(), professor2.isStatus());
            laboratorioService2.criarLaboratorio(laboratorio2.getDescricao(), laboratorio2.getCapacidade(), laboratorio2.isStatus());
            turmaService2.criarTurma(turma2.getDisciplina());
            reservaService2.criarReserva(reserva2.getLaboratorio(), reserva2.getTurma(), reserva2.getDataHora(), reserva2.getTempo(), reserva2.isAprovada());
            alunoService2.criarAluno(aluno2.getNome(), aluno2.getTurma(), aluno2.isStatus());

//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------
            Aluno aluno3 = new Aluno();
            Disciplina disciplina3 = new Disciplina();
            Laboratorio laboratorio3 = new Laboratorio();
            Professor professor3 = new Professor();
            Reserva reserva3 = new Reserva();
            Turma turma3 = new Turma();

            disciplina3.setDescricao("Química");
            disciplina3.setSigla("QUI");
            disciplina3.setStatus(true);

            professor3.setNome("Beatriz");
            professor3.setStatus(true);

            laboratorio3.setCapacidade(20);
            laboratorio3.setDescricao("Laboratório de Química");
            laboratorio3.setStatus(true);

            reserva3.setAprovada(true);
            reserva3.setDataHora(Timestamp.valueOf("2021-12-20 09:00:00").toLocalDateTime());
            reserva3.setTempo(Duration.ofHours(1));
            reserva3.setLaboratorio(laboratorio3);
            reserva3.setProfessor(professor3);
            reserva3.setTurma(turma3);

            turma3.setDisciplina(disciplina3);

            aluno3.setNome("Pedro");
            aluno3.setStatus(true);
            aluno3.setTurma(turma3);


            DisciplinaService disciplinaService3 = new DisciplinaService();
            ProfessorService professorService3 = new ProfessorService();
            LaboratorioService laboratorioService3 = new LaboratorioService();
            ReservaService reservaService3 = new ReservaService();
            TurmaService turmaService3 = new TurmaService();
            AlunoService alunoService3 = new AlunoService();


            disciplinaService3.criarDisciplina(disciplina3.getSigla(), disciplina3.getDescricao(), disciplina3.isStatus());
            professorService3.criarProfessor(professor3.getNome(), professor3.isStatus());
            laboratorioService3.criarLaboratorio(laboratorio3.getDescricao(), laboratorio3.getCapacidade(), laboratorio3.isStatus());
            turmaService3.criarTurma(turma3.getDisciplina());
            reservaService3.criarReserva(reserva3.getLaboratorio(), reserva3.getTurma(), reserva3.getDataHora(), reserva3.getTempo(), reserva3.isAprovada());
            alunoService3.criarAluno(aluno3.getNome(), aluno3.getTurma(), aluno3.isStatus());

        }
    }

}








