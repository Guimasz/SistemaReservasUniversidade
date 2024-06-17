package dao;

import config.ConexaoPostgreSQL;
import model.Aluno;
import model.Disciplina;
import model.Professor;

import java.sql.*;
import java.util.ArrayList;

public class ProfessorDao {


    public ArrayList<Professor> findAll() {
        String sql = "SELECT * FROM professor";
        ArrayList<Professor> professores = new ArrayList<>();

        try (Connection conexao = ConexaoPostgreSQL.obterConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Professor professor = new Professor();
                professor.setId(rs.getInt("id"));
                professor.setNome(rs.getString("nome"));
                professor.setStatus(rs.getBoolean("status"));
                professores.add(professor);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return professores;
    }


    public Professor findProfessorById(int id) {
        String sql = "SELECT * FROM professor WHERE id = ?";
        Professor professor = null;

        try (Connection conexao = ConexaoPostgreSQL.obterConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    professor = new Professor();
                    professor.setId(rs.getInt("id"));
                    professor.setNome(rs.getString("nome"));
                    professor.setStatus(rs.getBoolean("status"));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return professor;
    }


    public void criarProfessor(Professor professor) {
        String sql = "INSERT INTO professor (nome, status) VALUES (?, ?)";
        try (Connection conexao = ConexaoPostgreSQL.obterConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, professor.getNome());
            stmt.setBoolean(2, professor.isStatus());
            stmt.executeUpdate();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void atualizarProfessor(Integer id, Professor novoProfessor) {
        String sql = "UPDATE professor SET nome = ?, status = ? WHERE id = ?";

        try (Connection conexao = ConexaoPostgreSQL.obterConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, novoProfessor.getNome());
            stmt.setBoolean(2, novoProfessor.isStatus());
            stmt.setInt(3, id);

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void deletarProfessor(Integer id) throws SQLException {
        String sql = "DELETE FROM professor WHERE id = ?";

        try (Connection conexao = ConexaoPostgreSQL.obterConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, id);

            stmt.executeUpdate();
        }
    }
}



