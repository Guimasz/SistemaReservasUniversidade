package model;

import java.time.Duration;
import java.time.LocalDateTime;

public class Solicitacao {
    private int id;
    private Laboratorio laboratorio;
    private Professor professor;
    private Turma turma;
    private LocalDateTime dataHora;
    private Duration tempo;

    public Solicitacao(int id, Duration tempo, LocalDateTime dataHora, Turma turma, Professor professor, Laboratorio laboratorio) {
        this.id = id;
        this.tempo = tempo;
        this.dataHora = dataHora;
        this.turma = turma;
        this.professor = professor;
        this.laboratorio = laboratorio;
    }

    public int id() {
        return id;
    }

    public void setId(int id) {
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

    public LocalDateTime dataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public Duration tempo() {
        return tempo;
    }

    public void setTempo(Duration tempo) {
        this.tempo = tempo;
    }

    @Override
    public String toString() {
        return "solicitacao{" +
                "id=" + id +
                ", laboratorio=" + laboratorio +
                ", professor=" + professor +
                ", turma=" + turma +
                ", dataHora=" + dataHora +
                ", tempo=" + tempo +
                '}';
    }
}
