package program;

import java.sql.*;

public class Inicializar {
    private static void createTableAluno(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            String sql = "CREATE TABLE IF NOT EXISTS aluno (" +
                    "id INTEGER PRIMARY KEY," +
                    "nome VARCHAR(255) NOT NULL," +
                    "idade INT NOT NULL)";
            statement.execute(sql);
            System.out.println("Tabela 'aluno' criada com sucesso.");
        }
    }
}
