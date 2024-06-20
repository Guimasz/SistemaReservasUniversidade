package dao;

import config.ConexaoPostgreSQL;
import model.*;

import java.sql.*;
import java.time.Duration;
import java.util.ArrayList;

public class ReservaDao {

    public ReservaDao() {
    }

    public ArrayList<Reserva> findAll() {
        String sql = "SELECT * FROM vw_reserva_detalhada";
        return executarConsulta(sql);
    }

    public ArrayList<Reserva> findAllAprovadas() {
        String sql = "SELECT * FROM vw_reserva_aprovadas";
        return executarConsulta(sql);
    }

    private ArrayList<Reserva> executarConsulta(String sql) {
        ArrayList<Reserva> reservas = new ArrayList<>();

        try (Connection conexao = ConexaoPostgreSQL.obterConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Reserva reserva = new Reserva();
                reserva.setId(rs.getInt("id"));
                reserva.setDataHora(rs.getTimestamp("datahora").toLocalDateTime());
                reserva.setTempo(Duration.ofMinutes(rs.getLong("duracao")));
                reserva.setAprovada(rs.getBoolean("aprovada"));

                Laboratorio laboratorio = new Laboratorio();
                laboratorio.setId(rs.getInt("lab_id"));
                laboratorio.setDescricao(rs.getString("lab_desc"));
                laboratorio.setCapacidade(rs.getInt("lab_cap"));
                laboratorio.setStatus(rs.getBoolean("lab_status"));
                reserva.setLaboratorio(laboratorio);

                Professor professor = new Professor();
                professor.setId(rs.getInt("prof_id"));
                professor.setNome(rs.getString("prof_nome"));
                professor.setStatus(rs.getBoolean("prof_status"));
                reserva.setProfessor(professor);

                Turma turma = new Turma();
                turma.setId(rs.getInt("turma_id"));
                Disciplina disciplina = new Disciplina();
                disciplina.setId(rs.getInt("disc_id"));
                disciplina.setSigla(rs.getString("disc_sigla"));
                disciplina.setDescricao(rs.getString("disc_desc"));
                disciplina.setStatus(rs.getBoolean("disc_status"));
                turma.setDisciplina(disciplina);
                reserva.setTurma(turma);

                reservas.add(reserva);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reservas;
    }


    public Reserva findReservaById(int id) {
        String sql = "SELECT r.id, r.datahora, r.duracao, r.aprovada, " +
                "l.id AS lab_id, l.descricao AS lab_desc, l.capacidade AS lab_cap, l.status AS lab_status, " +
                "p.id AS prof_id, p.nome AS prof_nome, p.status AS prof_status, " +
                "t.id AS turma_id, d.id AS disc_id, d.sigla AS disc_sigla, d.descricao AS disc_desc, d.status AS disc_status " +
                "FROM reserva r " +
                "INNER JOIN laboratorio l ON r.laboratorio = l.id " +
                "INNER JOIN professor p ON r.professor = p.id " +
                "INNER JOIN turma t ON r.turma = t.id " +
                "INNER JOIN disciplina d ON t.disciplina = d.id " +
                "WHERE r.id = ?";
        Reserva reserva = null;

        try (Connection conexao = ConexaoPostgreSQL.obterConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    reserva = new Reserva();
                    reserva.setId(rs.getInt("id"));
                    reserva.setDataHora(rs.getTimestamp("datahora").toLocalDateTime());
                    reserva.setTempo(Duration.ofMinutes(rs.getLong("duracao")));
                    reserva.setAprovada(rs.getBoolean("aprovada"));

                    Laboratorio laboratorio = new Laboratorio();
                    laboratorio.setId(rs.getInt("lab_id"));
                    laboratorio.setDescricao(rs.getString("lab_desc"));
                    laboratorio.setCapacidade(rs.getInt("lab_cap"));
                    laboratorio.setStatus(rs.getBoolean("lab_status"));
                    reserva.setLaboratorio(laboratorio);

                    Professor professor = new Professor();
                    professor.setId(rs.getInt("prof_id"));
                    professor.setNome(rs.getString("prof_nome"));
                    professor.setStatus(rs.getBoolean("prof_status"));
                    reserva.setProfessor(professor);

                    Turma turma = new Turma();
                    turma.setId(rs.getInt("turma_id"));
                    Disciplina disciplina = new Disciplina();
                    disciplina.setId(rs.getInt("disc_id"));
                    disciplina.setSigla(rs.getString("disc_sigla"));
                    disciplina.setDescricao(rs.getString("disc_desc"));
                    disciplina.setStatus(rs.getBoolean("disc_status"));
                    turma.setDisciplina(disciplina);
                    reserva.setTurma(turma);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reserva;
    }


    public void criarReserva(Reserva reserva) {
        String sql = "INSERT INTO reserva (laboratorio, professor, turma, datahora, duracao, aprovada) VALUES (?, ?, ?, ?, ?, ?)";

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


    public void atualizarReserva(Integer id, Reserva reserva) {
        String sql = "UPDATE reserva SET laboratorio = ?, professor = ?, turma = ?, datahora = ?, duracao = ?, aprovada = ? WHERE id = ?";

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
