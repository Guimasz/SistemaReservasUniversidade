package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoPostgreSQL {
    // URL de conexão com o banco de dados
    private static final String URL = "jdbc:postgresql://localhost:5432/nome_do_banco";
    // Nome de usuário do banco de dados
    private static final String USUARIO = "usuario";
    // Senha do banco de dados
    private static final String SENHA = "senha";

    // Método para obter a conexão com o banco de dados
    public static Connection obterConexao() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, SENHA);
    }
}
