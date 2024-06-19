package service;

import dao.ReservaDao;
import model.Laboratorio;
import model.Professor;
import model.Reserva;
import model.Turma;

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

            }


        }


        return true;
    }

}
