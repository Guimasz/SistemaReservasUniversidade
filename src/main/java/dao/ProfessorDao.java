package dao;

import config.ConexaoPostgreSQL;
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
                    professor.setPdisciplinas(findDisciplinasByProfessorId(professor.getId()));
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
                        professor.setPdisciplinas(findDisciplinasByProfessorId(professor.getId()));
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

                int affectedRows = stmt.executeUpdate();

                if (affectedRows > 0) {
                    try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                        if (generatedKeys.next()) {
                            professor.setId(generatedKeys.getInt(1));
                        }
                    }
                    inserirDisciplinas(professor);
                }

            } catch (SQLException e) {
                e.printStackTrace();
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
                inserirDisciplinas(novoProfessor);

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


        public void deletarProfessor(int id) {
            String sql = "DELETE FROM professor WHERE id = ?";

            try (Connection conexao = ConexaoPostgreSQL.obterConexao();
                 PreparedStatement stmt = conexao.prepareStatement(sql)) {

                stmt.setInt(1, id);

                stmt.executeUpdate();
                deletarDisciplinasPorProfessorId(id);

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        private ArrayList<Disciplina> findDisciplinasByProfessorId(int professorId) {
            String sql = "SELECT d.* FROM disciplina d "
                    + "INNER JOIN professordisciplina pd ON d.id = pd.disciplina_id "
                    + "WHERE pd.professor_id = ?";
            ArrayList<Disciplina> disciplinas = new ArrayList<>();

            try (Connection conexao = ConexaoPostgreSQL.obterConexao();
                 PreparedStatement stmt = conexao.prepareStatement(sql)) {

                stmt.setInt(1, professorId);

                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        Disciplina disciplina = new Disciplina();
                        disciplina.setId(rs.getInt("id"));
                        disciplina.setSigla(rs.getString("sigla"));
                        disciplina.setDescricao(rs.getString("descricao"));
                        disciplina.setStatus(rs.getBoolean("status"));
                        disciplinas.add(disciplina);
                    }
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
            return disciplinas;
        }

        private void inserirDisciplinas(Professor professor) throws SQLException {
            String sql = "INSERT INTO professordisciplina (professor_id, disciplina_id) VALUES (?, ?)";

            try (Connection conexao = ConexaoPostgreSQL.obterConexao();
                 PreparedStatement stmt = conexao.prepareStatement(sql)) {

                for (Disciplina disciplina : professor.getPdisciplinas()) {
                    stmt.setInt(1, professor.getId());
                    stmt.setLong(2, disciplina.getId());
                    stmt.addBatch();
                }

                stmt.executeBatch();
            }
        }


        private void deletarDisciplinasPorProfessorId(int professorId) throws SQLException {
            String sql = "DELETE FROM professor_disciplina WHERE professor_id = ?";

            try (Connection conexao = ConexaoPostgreSQL.obterConexao();
                 PreparedStatement stmt = conexao.prepareStatement(sql)) {

                stmt.setInt(1, professorId);
                stmt.executeUpdate();
            }
        }
    }


