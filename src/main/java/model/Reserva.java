package model;

import java.time.Duration;
import java.time.LocalDateTime;

public class Reserva {
    private Integer id;
    private Laboratorio laboratorio;
    private Professor professor;
    private Turma turma;
    private LocalDateTime dataHora;
    private Duration tempo;
    private Boolean aprovada;

    public Reserva() {}

    public Reserva(Integer id, Laboratorio laboratorio, Professor professor, Turma turma, LocalDateTime dataHora, Duration tempo, Boolean aprovada) {
        this.id = id;
        this.laboratorio = laboratorio;
        this.professor = professor;
        this.turma = turma;
        this.dataHora = dataHora;
        this.tempo = tempo;
        this.aprovada = aprovada;
    }

    public Integer id() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Laboratorio laboratorio() {
        return laboratorio;
    }

    public void setLaboratorio(Laboratorio laboratorio) {
        this.laboratorio = laboratorio;
    }

    public Professor professor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public Turma turma() {
        return turma;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }

    public Duration tempo() {
        return tempo;
    }

    public void setTempo(Duration tempo) {
        this.tempo = tempo;
    }

    public LocalDateTime dataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public Boolean aprovada() {
        return aprovada;
    }

    public void setAprovada(Boolean aprovada) {
        this.aprovada = aprovada;
    }

    @Override
    public String toString() {
        return "Reserva{" +
                "id=" + id +
                ", laboratorio=" + laboratorio +
                ", professor=" + professor +
                ", turma=" + turma +
                ", dataHora=" + dataHora +
                ", tempo=" + tempo +
                ", aprovada=" + aprovada +
                '}';
    }
}
