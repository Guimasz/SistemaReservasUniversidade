package Dao;

import config.ConexaoPostgreSQL;
import model.Disciplina;
import java.sql.*;
import java.util.ArrayList;


public class DisciplinaDao {

    public ArrayList<Disciplina> findAll() {
        String sql = "SELECT * FROM disciplina";
        ArrayList<Disciplina> disciplinas = new ArrayList<>();

        try (Connection conexao = ConexaoPostgreSQL.obterConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Disciplina disciplina = new Disciplina();
                disciplina.setId(rs.getInt("id"));
                disciplina.setSigla(rs.getString("sigla"));
                disciplina.setDescricao(rs.getString("descricao"));
                disciplina.setStatus(rs.getBoolean("status"));
                disciplinas.add(disciplina);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return disciplinas;
    }

    public Disciplina findDisciplinaById(Integer id) {
        String sql = "SELECT * FROM disciplina WHERE id = ?";
        Disciplina disciplina = null;

        try (Connection conexao = ConexaoPostgreSQL.obterConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    disciplina = new Disciplina();
                    disciplina.setId(rs.getInt("id"));
                    disciplina.setSigla(rs.getString("sigla"));
                    disciplina.setDescricao(rs.getString("descricao"));
                    disciplina.setStatus(rs.getBoolean("status"));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return disciplina;
    }

    public void criarDisciplina(Disciplina disciplina) {
        String sql = "INSERT INTO disciplina (sigla, descricao, status) VALUES (?, ?, ?)";

        try (Connection conexao = ConexaoPostgreSQL.obterConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, disciplina.getSigla());
            stmt.setString(2, disciplina.getDescricao());
            stmt.setBoolean(3, disciplina.isStatus());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void atualizarDisciplina(Disciplina disciplina) {
        String sql = "UPDATE disciplina SET sigla = ?, descricao = ?, status = ? WHERE id = ?";

        try (Connection conexao = ConexaoPostgreSQL.obterConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, disciplina.getSigla());
            stmt.setString(2, disciplina.getDescricao());
            stmt.setBoolean(3, disciplina.isStatus());
            stmt.setInt(4, disciplina.getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletarDisciplina(Integer id) {
        String sql = "DELETE FROM disciplina WHERE id = ?";

        try (Connection conexao = ConexaoPostgreSQL.obterConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, id);

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
