package dao;

import config.ConexaoPostgreSQL;
import model.*;

import java.sql.*;
import java.time.Duration;
import java.util.ArrayList;

public class ReservaDao {

    // Método para recuperar todas as reservas
    public ArrayList<Reserva> findAll() {
        String sql = "SELECT r.id, r.data_hora, r.tempo, r.aprovada, " +
                "l.id AS laboratorio_id, l.descricao AS laboratorio_descricao, l.capacidade AS laboratorio_capacidade, l.status AS laboratorio_status, " +
                "p.id AS professor_id, p.nome AS professor_nome, p.status AS professor_status, " +
                "t.id AS turma_id, d.id AS disciplina_id, d.sigla AS disciplina_sigla, d.descricao AS disciplina_descricao, d.status AS disciplina_status " +
                "FROM reserva r " +
                "INNER JOIN laboratorio l ON r.laboratorio_id = l.id " +
                "INNER JOIN professor p ON r.professor_id = p.id " +
                "INNER JOIN turma t ON r.turma_id = t.id " +
                "INNER JOIN disciplina d ON t.disciplina_id = d.id";
        ArrayList<Reserva> reservas = new ArrayList<>();

        try (Connection conexao = ConexaoPostgreSQL.obterConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Reserva reserva = new Reserva();
                reserva.setId(rs.getInt("id"));
                reserva.setDataHora(rs.getTimestamp("data_hora").toLocalDateTime());
                reserva.setTempo(Duration.ofMinutes(rs.getLong("tempo")));
                reserva.setAprovada(rs.getBoolean("aprovada"));

                Laboratorio laboratorio = new Laboratorio();
                laboratorio.setId(rs.getInt("laboratorio_id"));
                laboratorio.setDescricao(rs.getString("laboratorio_descricao"));
                laboratorio.setCapacidade(rs.getInt("laboratorio_capacidade"));
                laboratorio.setStatus(rs.getBoolean("laboratorio_status"));
                reserva.setLaboratorio(laboratorio);

                Professor professor = new Professor();
                professor.setId(rs.getInt("professor_id"));
                professor.setNome(rs.getString("professor_nome"));
                professor.setStatus(rs.getBoolean("professor_status"));
                reserva.setProfessor(professor);

                Turma turma = new Turma();
                turma.setId(rs.getInt("turma_id"));
                Disciplina disciplina = new Disciplina();
                disciplina.setId(rs.getInt("disciplina_id"));
                disciplina.setSigla(rs.getString("disciplina_sigla"));
                disciplina.setDescricao(rs.getString("disciplina_descricao"));
                disciplina.setStatus(rs.getBoolean("disciplina_status"));
                turma.setDisciplina(disciplina);
                reserva.setTurma(turma);

                reservas.add(reserva);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reservas;
    }

    // Método para recuperar uma reserva pelo ID
    public Reserva findReservaById(int id) {
        String sql = "SELECT r.id, r.data_hora, r.tempo, r.aprovada, " +
                "l.id AS laboratorio_id, l.descricao AS laboratorio_descricao, l.capacidade AS laboratorio_capacidade, l.status AS laboratorio_status, " +
                "p.id AS professor_id, p.nome AS professor_nome, p.status AS professor_status, " +
                "t.id AS turma_id, d.id AS disciplina_id, d.sigla AS disciplina_sigla, d.descricao AS disciplina_descricao, d.status AS disciplina_status " +
                "FROM reserva r " +
                "INNER JOIN laboratorio l ON r.laboratorio_id = l.id " +
                "INNER JOIN professor p ON r.professor_id = p.id " +
                "INNER JOIN turma t ON r.turma_id = t.id " +
                "INNER JOIN disciplina d ON t.disciplina_id = d.id " +
                "WHERE r.id = ?";
        Reserva reserva = null;

        try (Connection conexao = ConexaoPostgreSQL.obterConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    reserva = new Reserva();
                    reserva.setId(rs.getInt("id"));
                    reserva.setDataHora(rs.getTimestamp("data_hora").toLocalDateTime());
                    reserva.setTempo(Duration.ofMinutes(rs.getLong("tempo")));
                    reserva.setAprovada(rs.getBoolean("aprovada"));

                    Laboratorio laboratorio = new Laboratorio();
                    laboratorio.setId(rs.getInt("laboratorio_id"));
                    laboratorio.setDescricao(rs.getString("laboratorio_descricao"));
                    laboratorio.setCapacidade(rs.getInt("laboratorio_capacidade"));
                    laboratorio.setStatus(rs.getBoolean("laboratorio_status"));
                    reserva.setLaboratorio(laboratorio);

                    Professor professor = new Professor();
                    professor.setId(rs.getInt("professor_id"));
                    professor.setNome(rs.getString("professor_nome"));
                    professor.setStatus(rs.getBoolean("professor_status"));
                    reserva.setProfessor(professor);

                    Turma turma = new Turma();
                    turma.setId(rs.getInt("turma_id"));
                    Disciplina disciplina = new Disciplina();
                    disciplina.setId(rs.getInt("disciplina_id"));
                    disciplina.setSigla(rs.getString("disciplina_sigla"));
                    disciplina.setDescricao(rs.getString("disciplina_descricao"));
                    disciplina.setStatus(rs.getBoolean("disciplina_status"));
                    turma.setDisciplina(disciplina);
                    reserva.setTurma(turma);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reserva;
    }

    // Método para criar uma nova reserva
    public void criarReserva(Reserva reserva) {
        String sql = "INSERT INTO reserva (laboratorio_id, professor_id, turma_id, data_hora, tempo, aprovada) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conexao = ConexaoPostgreSQL.obterConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, reserva.getLaboratorio().getId());
            stmt.setInt(2, reserva.getProfessor().getId());
            stmt.setInt(3, reserva.getTurma().getId());
            stmt.setTimestamp(4, Timestamp.valueOf(reserva.getDataHora()));
            stmt.setLong(5, reserva.getTempo().toMinutes());
            stmt.setBoolean(6, reserva.isAprovada());

            int affectedRows = stmt.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        reserva.setId(generatedKeys.getInt(1));
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para atualizar uma reserva existente
    public void atualizarReserva(Integer id, Reserva reserva) {
        String sql = "UPDATE reserva SET laboratorio_id = ?, professor_id = ?, turma_id = ?, data_hora = ?, tempo = ?, aprovada = ? WHERE id = ?";

        try (Connection conexao = ConexaoPostgreSQL.obterConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, reserva.getLaboratorio().getId());
            stmt.setInt(2, reserva.getProfessor().getId());
            stmt.setInt(3, reserva.getTurma().getId());
            stmt.setTimestamp(4, Timestamp.valueOf(reserva.getDataHora()));
            stmt.setLong(5, reserva.getTempo().toMinutes());
            stmt.setBoolean(6, reserva.isAprovada());
            stmt.setInt(7, id);

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para deletar uma reserva pelo ID
    public void deletarReserva(int id) {
        String sql = "DELETE FROM reserva WHERE id = ?";

        try (Connection conexao = ConexaoPostgreSQL.obterConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, id);

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
