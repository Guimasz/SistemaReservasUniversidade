package dao;

import config.ConexaoPostgreSQL;
import model.Aluno;
import model.Disciplina;
import model.Turma;

import java.sql.*;
import java.util.ArrayList;

public class AlunoDao {

    public ArrayList<Aluno> findAll() {
        String sql = "SELECT a.matricula, a.nome, a.status, t.id AS turma_id, t.disciplina AS disciplina_id"
                + " FROM aluno a "
                + " INNER JOIN turma t ON a.turma = t.id";
        ArrayList<Aluno> alunos = new ArrayList<>();

        try (Connection conexao = ConexaoPostgreSQL.obterConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Aluno aluno = new Aluno();
                aluno.setMatricula(rs.getInt("matricula"));
                aluno.setNome(rs.getString("nome"));
                aluno.setStatus(rs.getBoolean("status"));
                Turma turma = new Turma();
                turma.setId(rs.getInt("turma_id"));

                Disciplina disciplina = new Disciplina();
                disciplina.setId(rs.getInt("disciplina_id"));
                disciplina.setSigla(rs.getString("disciplina_sigla"));
                disciplina.setDescricao(rs.getString("disciplina_descricao"));
                disciplina.setStatus(rs.getBoolean("disciplina_status"));
                turma.setDisciplina(disciplina);

                aluno.setTurma(turma);
                alunos.add(aluno);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return alunos;
    }


    public Aluno findAlunobyMatricula(Integer matricula) {
        String sql = "SELECT a.matricula, a.nome, a.status, t.id AS turma_id, t.disciplina AS disciplina_id "
                + " FROM aluno a "
                + " INNER JOIN turma t ON a.turma = t.id "
                + " WHERE a.matricula = ?";
        Aluno aluno = null;

        try (Connection conexao = ConexaoPostgreSQL.obterConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, matricula);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    aluno = new Aluno();
                    aluno.setMatricula(rs.getInt("matricula"));
                    aluno.setNome(rs.getString("nome"));
                    aluno.setStatus(rs.getBoolean("status"));
                    Turma turma = new Turma();
                    turma.setId(rs.getInt("turma_id"));
                    Disciplina disciplina = new Disciplina();
                    disciplina.setId(rs.getInt("disciplina_id"));
                    disciplina.setSigla(rs.getString("disciplina_sigla"));
                    disciplina.setDescricao(rs.getString("disciplina_descricao"));
                    disciplina.setStatus(rs.getBoolean("disciplina_status"));
                    turma.setDisciplina(disciplina);
                    aluno.setTurma(turma);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return aluno;
    }

    public void criarAluno(Aluno aluno) {
        String sql = "INSERT INTO aluno (matricula, nome, turma, status) VALUES (?, ?, ?, ?)";

        try (Connection conexao = ConexaoPostgreSQL.obterConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, aluno.getMatricula());
            stmt.setString(2, aluno.getNome());
            stmt.setInt(3, aluno.getTurma().getId());
            stmt.setBoolean(4, aluno.isStatus());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void atualizarAluno(Aluno aluno) {
        String sql = "UPDATE aluno SET nome = ?, turma = ?, status = ? WHERE matricula = ?";

        try (Connection conexao = ConexaoPostgreSQL.obterConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, aluno.getNome());
            stmt.setInt(2, aluno.getTurma().getId());
            stmt.setBoolean(3, aluno.isStatus());
            stmt.setInt(4, aluno.getMatricula());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void deletarAluno(Integer matricula) {
        String sql = "DELETE FROM aluno WHERE matricula = ?";

        try (Connection conexao = ConexaoPostgreSQL.obterConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, matricula);

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}