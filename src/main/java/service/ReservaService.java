package service;

import config.ConexaoPostgreSQL;
import dao.ReservaDao;
import model.*;

import java.sql.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class ReservaService {

    ReservaDao reservaDao;

    public ReservaService() {
        this.reservaDao = new ReservaDao();
    }

    public void criarReserva(Laboratorio laboratorio, Professor professor, Turma turma, LocalDateTime dataHora, Duration duracao) {
        Reserva novaReserva = new Reserva();
        novaReserva.setLaboratorio(laboratorio);
        novaReserva.setProfessor(professor);
        novaReserva.setTurma(turma);
        novaReserva.setDataHora(dataHora);
        novaReserva.setTempo(duracao);
        novaReserva.setAprovada(aprovarReserva(novaReserva));


        reservaDao.criarReserva(novaReserva);
    }

    public void atualizarReserva(Integer id, Laboratorio laboratorio, Turma turma, LocalDateTime dataHora, Duration duracao) {
        Reserva novaReserva = new Reserva();
        novaReserva.setLaboratorio(laboratorio);
        novaReserva.setTurma(turma);
        novaReserva.setDataHora(dataHora);
        novaReserva.setTempo(duracao);
        novaReserva.setAprovada(aprovarReserva(novaReserva));
        reservaDao.atualizarReserva(id, novaReserva);

    }

    public void deletarReserva(Integer id) {
        reservaDao.deletarReserva(id);
    }

    public Reserva findReservaById(Integer id) {
        Reserva reserva = reservaDao.findReservaById(id);
        if (reserva == null) {
            throw new RuntimeException("Reserva não encontrada");
        }
        return reserva;
    }

    public ArrayList<Reserva> findAll() {
        if (reservaDao.findAll() == null) {
            throw new RuntimeException("Não existem reservas cadastradas");
        }
        return reservaDao.findAll();
    }

    public ArrayList<Reserva> findAllAprovadas() {
        if (reservaDao.findAllAprovadas() == null) {
            throw new RuntimeException("Não existem reservas aprovadas");
        }
        return reservaDao.findAllAprovadas();
    }

    public Boolean aprovarReserva(Reserva novaReserva){
        ArrayList<Reserva> lista = reservaDao.findAllAprovadas();

        LocalDateTime novaReservaInicio = novaReserva.getDataHora();
        LocalDateTime novaReservaFim = novaReservaInicio.plus(novaReserva.getTempo());

        for (Reserva existente : lista) {
            LocalDateTime reservaExistenteInicio = existente.getDataHora();
            LocalDateTime reservaExistenteFim = reservaExistenteInicio.plus(existente.getTempo());

            boolean conflito = novaReservaInicio.isBefore(reservaExistenteFim) && novaReservaFim.isAfter(reservaExistenteInicio);
            if (conflito) {

                // Verificar se o laboratório é o mesmo
                if (novaReserva.getLaboratorio().equals(existente.getLaboratorio())) {
                    return false; // Existe sobreposição de laboratório
                }
                // Verificar se o professor é o mesmo
                if (novaReserva.getProfessor().equals(existente.getProfessor())) {
                    return false; // Existe sobreposição de professor
                }
                // Verificar se a turma é a mesma
                if (novaReserva.getTurma().equals(existente.getTurma())) {
                    return false; // Existe sobreposição de turma
                }

                // Verificar se o professor está disponível para a disciplina
                if (!professorPodeDarAula(novaReserva.getProfessor(), novaReserva.getTurma().getDisciplina())) {
                    return false; // Professor não pode dar aula para essa disciplina
                }

            }


        }


        return true;
    }

    private boolean professorPodeDarAula(Professor professor, Disciplina disciplina) {
        String sql = "SELECT 1 FROM professordisciplina WHERE idProfessor = ? AND idDisciplina = ? LIMIT 1";
        boolean podeDarAula = false;

        try (Connection conexao = ConexaoPostgreSQL.obterConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, professor.getId());
            stmt.setInt(2, disciplina.getId());

            try (ResultSet rs = stmt.executeQuery()) {
                podeDarAula = rs.next(); // Se houver pelo menos um resultado, o professor pode dar aula para a disciplina
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return podeDarAula;
    }

}
