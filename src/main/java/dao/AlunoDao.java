package dao;

import config.ConexaoPostgreSQL;
import model.Aluno;
import model.Turma;

import java.sql.*;
import java.util.ArrayList;

public class AlunoDao {

    public ArrayList<Aluno> findAll() {
        String sql = "SELECT a.id, a.nome, a.matricula, a.status, t.id AS turma_id"
                + "FROM aluno a "
                + "INNER JOIN turma t ON a.turma_id = t.id";
        ArrayList<Aluno> alunos = new ArrayList<>();

        try (Connection conexao = ConexaoPostgreSQL.obterConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Aluno aluno = new Aluno();
                aluno.setId(rs.getLong("id"));
                aluno.setNome(rs.getString("nome"));
                aluno.setMatricula(rs.getString("matricula"));
                aluno.setStatus(rs.getBoolean("status"));
                Turma turma = new Turma();
                turma.setId(rs.getInt("turma_id"));
                aluno.setTurma(turma);
                alunos.add(aluno);
            }

        } catch (SQLException e) {
            e.printStackTrace();

        }
        return alunos;
    }
    public Aluno findAlunobyId(Long id) {
        String sql = "SELECT a.id, a.nome, a.matricula, a.status, t.id AS turma_id, t.nome AS turma_nome "
                + "FROM aluno a "
                + "INNER JOIN turma t ON a.turma_id = t.id "
                + "WHERE a.id = ?";
        Aluno aluno = null;

        try (Connection conexao = ConexaoPostgreSQL.obterConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setLong(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    aluno = new Aluno();
                    aluno.setId(rs.getLong("id"));
                    aluno.setNome(rs.getString("nome"));
                    aluno.setMatricula(rs.getString("matricula"));
                    aluno.setStatus(rs.getBoolean("status"));
                    Turma turma = new Turma();
                    turma.setId(rs.getInt("turma_id"));
                    aluno.setTurma(turma);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return aluno;
    }

    public void criarAluno(Aluno aluno) {
        String sql = "INSERT INTO aluno (nome, matricula, turma, status) VALUES (?, ?, ?, ?)";

        try (Connection conexao = ConexaoPostgreSQL.obterConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, aluno.getNome());
            stmt.setString(2, aluno.getMatricula());
            stmt.setInt(3, aluno.getTurma().getId());
            stmt.setBoolean(4, aluno.isStatus());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void atualizarAluno(Aluno aluno) {
        String sql = "UPDATE aluno SET nome = ?, matricula = ?, turma = ?, status = ? WHERE id = ?";


        try (Connection conexao = ConexaoPostgreSQL.obterConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, aluno.getNome());
            stmt.setString(2, aluno.getMatricula());
            stmt.setInt(3, aluno.getTurma().getId());
            stmt.setBoolean(4, aluno.isStatus());
            stmt.setLong(5, aluno.getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void deletarAluno(Long id) {
        String sql = "DELETE FROM aluno WHERE id = ?";

        try (Connection conexao = ConexaoPostgreSQL.obterConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setLong(1, id);

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}