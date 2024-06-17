package dao;

import config.ConexaoPostgreSQL;
import model.Laboratorio;

import java.sql.*;
import java.util.ArrayList;

public class LaboratorioDao {

    // Método para recuperar todos os laboratórios
    public ArrayList<Laboratorio> findAll() {
        String sql = "SELECT * FROM laboratorio";
        ArrayList<Laboratorio> laboratorios = new ArrayList<>();

        try (Connection conexao = ConexaoPostgreSQL.obterConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Laboratorio laboratorio = new Laboratorio();
                laboratorio.setId(rs.getInt("id"));
                laboratorio.setDescricao(rs.getString("descricao"));
                laboratorio.setCapacidade(rs.getInt("capacidade"));
                laboratorio.setStatus(rs.getBoolean("status"));
                laboratorios.add(laboratorio);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return laboratorios;
    }

    // Método para recuperar um laboratório pelo ID
    public Laboratorio findLaboratorioById(int id) {
        String sql = "SELECT * FROM laboratorio WHERE id = ?";
        Laboratorio laboratorio = null;

        try (Connection conexao = ConexaoPostgreSQL.obterConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    laboratorio = new Laboratorio();
                    laboratorio.setId(rs.getInt("id"));
                    laboratorio.setDescricao(rs.getString("descricao"));
                    laboratorio.setCapacidade(rs.getInt("capacidade"));
                    laboratorio.setStatus(rs.getBoolean("status"));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return laboratorio;
    }

    // Método para criar um novo laboratório
    public void criarLaboratorio(Laboratorio laboratorio) {
        String sql = "INSERT INTO laboratorio (descricao, capacidade, status) VALUES (?, ?, ?)";

        try (Connection conexao = ConexaoPostgreSQL.obterConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, laboratorio.getDescricao());
            stmt.setInt(2, laboratorio.getCapacidade());
            stmt.setBoolean(3, laboratorio.isStatus());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para atualizar um laboratório existente
    public void atualizarLaboratorio(Laboratorio laboratorio) {
        String sql = "UPDATE laboratorio SET descricao = ?, capacidade = ?, status = ? WHERE id = ?";

        try (Connection conexao = ConexaoPostgreSQL.obterConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, laboratorio.getDescricao());
            stmt.setInt(2, laboratorio.getCapacidade());
            stmt.setBoolean(3, laboratorio.isStatus());
            stmt.setInt(4, laboratorio.getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para deletar um laboratório pelo ID
    public void deletarLaboratorio(int id) {
        String sql = "DELETE FROM laboratorio WHERE id = ?";

        try (Connection conexao = ConexaoPostgreSQL.obterConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, id);

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
