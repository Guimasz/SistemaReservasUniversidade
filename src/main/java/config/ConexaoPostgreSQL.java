package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoPostgreSQL {
    // URL de conexão com o banco de dados
    private static final String URL = "jdbc:postgresql://localhost:5432/universidade";
    // Nome de usuário do banco de dados
    private static final String USUARIO = "postgres";
    // Senha do banco de dados
    private static final String SENHA = "postgres";

    // Método para obter a conexão com o banco de dados
    public static Connection obterConexao() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, SENHA);
    }
}
