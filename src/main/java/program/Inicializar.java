package program;

import config.ConexaoPostgreSQL;

import java.sql.*;

public class Inicializar {
    private  Connection conexao;

    public Inicializar(){}
    public Inicializar(Connection connection) {
        this.conexao = connection;
    }

    public void start() throws SQLException {
        conectar();
        createTables();
    }

    private void conectar(){
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

        }
    }

}








