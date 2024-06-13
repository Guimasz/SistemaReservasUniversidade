package program;

import config.ConexaoPostgreSQL;
import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {


        try {
            Connection conexao = ConexaoPostgreSQL.obterConexao();
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
}

