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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Laboratorio getLaboratorio() {
        return laboratorio;
    }

    public void setLaboratorio(Laboratorio laboratorio) {
        this.laboratorio = laboratorio;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public Turma getTurma() {
        return turma;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }

    public Duration getTempo() {
        return tempo;
    }

    public void setTempo(Duration tempo) {
        this.tempo = tempo;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public Boolean isAprovada() {
        return aprovada;
    }

    public void setAprovada(Boolean aprovada) {
        this.aprovada = aprovada;
    }

    @Override
    public String toString() {
        return "Reserva{ " +
                "Id= " + id +
                ", Laboratorio= " + laboratorio.getId() +
                ", Professor= " + professor.getId() +
                ", Turma= " + turma.getId() +
                ", DataHora= " + dataHora +
                ", Tempo= " + tempo +
                ", Aprovada= " + aprovada +
                " }";
    }
}
