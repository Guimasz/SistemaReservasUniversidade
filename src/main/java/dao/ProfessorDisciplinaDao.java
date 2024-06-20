package dao;

import config.ConexaoPostgreSQL;
import model.ProfessorDisciplina;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProfessorDisciplinaDao {


    public void criarProfDis(ProfessorDisciplina professorDisciplina) throws SQLException {
        String sql = "INSERT INTO professordisciplina (idProfessor, idDisciplina) VALUES (?, ?)";
        try (Connection conexao = ConexaoPostgreSQL.obterConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {


            stmt.setInt(1, professorDisciplina.getIdProfessor());
            stmt.setInt(2, professorDisciplina.getIdDisciplina());
            stmt.executeUpdate();
        }
    }

    public ArrayList<ProfessorDisciplina> findAll() throws SQLException {
        ArrayList<ProfessorDisciplina> professorDisciplinas = new ArrayList<>();
        String sql = "SELECT * FROM professorDisciplina";
        try (Connection conexao = ConexaoPostgreSQL.obterConexao();
             Statement stmt = conexao.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                int idProfessor = rs.getInt("idProfessor");
                int idDisciplina = rs.getInt("idDisciplina");
                ProfessorDisciplina professorDisciplina = new ProfessorDisciplina(idDisciplina, idProfessor);
                professorDisciplinas.add(professorDisciplina);
            }
        }
        return professorDisciplinas;
    }

    public ArrayList<ProfessorDisciplina> findByProfessor(int idProfessor) throws SQLException {
        ArrayList<ProfessorDisciplina> professorDisciplinas = new ArrayList<>();
        String sql = "SELECT * FROM ProfessorDisciplina WHERE idProfessor = ?";
        try (Connection conexao = ConexaoPostgreSQL.obterConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, idProfessor);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int idDisciplina = rs.getInt("idDisciplina");
                    ProfessorDisciplina professorDisciplina = new ProfessorDisciplina(idDisciplina, idProfessor);
                    professorDisciplinas.add(professorDisciplina);
                }
            }
        }
        return professorDisciplinas;
    }

    public void atualizarProfDis(ProfessorDisciplina professorDisciplina) throws SQLException {
        String sql = "UPDATE ProfessorDisciplina SET idProfessor = ?, idDisciplina = ? WHERE idProfessor = ? AND idDisciplina = ?";
        try (Connection conexao = ConexaoPostgreSQL.obterConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, professorDisciplina.getIdProfessor());
            stmt.setInt(2, professorDisciplina.getIdDisciplina());
            stmt.setInt(3, professorDisciplina.getIdProfessor());
            stmt.setInt(4, professorDisciplina.getIdDisciplina());
            stmt.executeUpdate();
        }
    }

    public void deletarProfDis(int idProfessor, int idDisciplina) throws SQLException {
        String sql = "DELETE FROM ProfessorDisciplina WHERE idProfessor = ? AND idDisciplina = ?";
        try (Connection conexao = ConexaoPostgreSQL.obterConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, idProfessor);
            stmt.setInt(2, idDisciplina);
            stmt.executeUpdate();
        }
    }
}

