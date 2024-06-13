package model;

import java.time.Duration;
import java.time.LocalDateTime;

public class Reserva {
    private int id;
    private Solicitacao solicitacao;
    private Boolean situacaoReserva;
    private LocalDateTime diaHoraReserva;
    private Duration duracao;
    private String nomeProf;
    private String nomeDis;
    private LocalDateTime dataLiberacao;

    public Reserva(int id, Solicitacao solicitacao, LocalDateTime diaHoraReserva, Duration duracao, String nomeDis, LocalDateTime dataLiberacao, String nomeProf, Boolean situacaoReserva) {
        this.id = id;
        this.solicitacao = solicitacao;
        this.diaHoraReserva = diaHoraReserva;
        this.duracao = duracao;
        this.nomeDis = nomeDis;
        this.dataLiberacao = dataLiberacao;
        this.nomeProf = nomeProf;
        this.situacaoReserva = situacaoReserva;
    }

    public int id() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Solicitacao solicitacao() {
        return solicitacao;
    }

    public void setSolicitacao(Solicitacao solicitacao) {
        this.solicitacao = solicitacao;
    }

    public LocalDateTime diaHoraReserva() {
        return diaHoraReserva;
    }

    public void setDiaHoraReserva(LocalDateTime diaHoraReserva) {
        this.diaHoraReserva = diaHoraReserva;
    }

    public Boolean situacaoReserva() {
        return situacaoReserva;
    }

    public void setSituacaoReserva(Boolean situacaoReserva) {
        this.situacaoReserva = situacaoReserva;
    }

    public Duration duracao() {
        return duracao;
    }

    public void setDuracao(Duration duracao) {
        this.duracao = duracao;
    }

    public String nomeProf() {
        return nomeProf;
    }

    public void setNomeProf(String nomeProf) {
        this.nomeProf = nomeProf;
    }

    public String nomeDis() {
        return nomeDis;
    }

    public void setNomeDis(String nomeDis) {
        this.nomeDis = nomeDis;
    }

    public LocalDateTime dataLiberacao() {
        return dataLiberacao;
    }

    public void setDataLiberacao(LocalDateTime dataLiberacao) {
        this.dataLiberacao = dataLiberacao;
    }

    @Override
    public String toString() {
        return "Reserva{" +
                "id=" + id +
                ", solicitacao=" + solicitacao +
                ", situacaoReserva=" + situacaoReserva +
                ", diaHoraReserva=" + diaHoraReserva +
                ", duracao=" + duracao +
                ", nomeProf='" + nomeProf + '\'' +
                ", nomeDis='" + nomeDis + '\'' +
                ", dataLiberacao=" + dataLiberacao +
                '}';
    }
}
