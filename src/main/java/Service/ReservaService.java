package Service;
import dao.ReservaDao;
import model.Laboratorio;
import model.Reserva;
import model.Turma;

import java.time.Duration;
import java.time.LocalDateTime;

public class ReservaService {

 ReservaDao reservaDao;

 public void criarReserva(Laboratorio laboratorio, Turma turma, LocalDateTime dataHora, Duration duracao, Boolean aprovada) {
     Reserva novareserva = new Reserva();
        novareserva.setLaboratorio(laboratorio);
        novareserva.setTurma(turma);
        novareserva.setDataHora(dataHora);
        novareserva.setTempo(duracao);
        novareserva.setAprovada(aprovada);



     reservaDao.criarReserva(novareserva);
 }

 public void atualizarReserva(Integer id, Laboratorio laboratorio, Turma turma, LocalDateTime dataHora, Duration duracao, Boolean aprovada) {
     Reserva novaReserva = new Reserva();
        novaReserva.setLaboratorio(laboratorio);
        novaReserva.setTurma(turma);
        novaReserva.setDataHora(dataHora);
        novaReserva.setTempo(duracao);
        novaReserva.setAprovada(aprovada);
     reservaDao.atualizarReserva(id, novaReserva);

 }

    public void deletarReserva(Integer id) {
        reservaDao.deletarReserva(id);
    }

}