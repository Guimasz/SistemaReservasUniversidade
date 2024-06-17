package dao;

import config.ConexaoPostgreSQL;
import model.Disciplina;
import model.Turma;

import java.sql.*;
import java.util.ArrayList;

public class TurmaDao {


    public ArrayList<Turma> findAll() {
        String sql = "SELECT t.id, d.id AS disciplina_id, d.sigla, d.descricao, d.status FROM turma t "
                + "INNER JOIN disciplina d ON t.disciplina = d.id";
        ArrayList<Turma> turmas = new ArrayList<>();

        try (Connection conexao = ConexaoPostgreSQL.obterConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Turma turma = new Turma();
                turma.setId(rs.getInt("id"));
                Disciplina disciplina = new Disciplina();
                disciplina.setId(rs.getInt("disciplina_id"));
                disciplina.setSigla(rs.getString("sigla"));
                disciplina.setDescricao(rs.getString("descricao"));
                disciplina.setStatus(rs.getBoolean("status"));
                turma.setDisciplina(disciplina);
                turmas.add(turma);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return turmas;
    }


    public Turma findTurmaById(int id) {
        String sql = "SELECT t.id, d.id AS disciplina_id, d.sigla, d.descricao, d.status FROM turma t "
                + "INNER JOIN disciplina d ON t.disciplina = d.id "
                + "WHERE t.id = ?";
        Turma turma = null;

        try (Connection conexao = ConexaoPostgreSQL.obterConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    turma = new Turma();
                    turma.setId(rs.getInt("id"));
                    Disciplina disciplina = new Disciplina();
                    disciplina.setId(rs.getInt("disciplina_id"));
                    disciplina.setSigla(rs.getString("sigla"));
                    disciplina.setDescricao(rs.getString("descricao"));
                    disciplina.setStatus(rs.getBoolean("status"));
                    turma.setDisciplina(disciplina);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return turma;
    }

    // Método para criar uma nova turma
    public void criarTurma(Turma turma) {
        String sql = "INSERT INTO turma (disciplina) VALUES (?)";

        try (Connection conexao = ConexaoPostgreSQL.obterConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setLong(1, turma.getDisciplina().getId());

            int affectedRows = stmt.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        turma.setId(generatedKeys.getInt(1));
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para atualizar uma turma existente
    public void atualizarTurma(Integer id ,Turma turma) {
        String sql = "UPDATE turma SET disciplina= ? WHERE id = ?";

        try (Connection conexao = ConexaoPostgreSQL.obterConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setLong(1, turma.getDisciplina().getId());
            stmt.setInt(2, turma.getId());
            stmt.setInt(3, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para deletar uma turma pelo ID
    public void deletarTurma(int id) {
        String sql = "DELETE FROM turma WHERE id = ?";

        try (Connection conexao = ConexaoPostgreSQL.obterConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, id);

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
