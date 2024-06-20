package model;

import java.util.Objects;

public class Turma {
    private Integer id;
    private Disciplina disciplina;

    public Turma(int id, Disciplina disciplina) {
        this.id = id;
        this.disciplina = disciplina;
    }
    public Turma() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

    @Override
    public String toString() {
        return "Turma{ " +
                "Id= " + id +
                ", Disciplina= " + disciplina +
                " }";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Turma turma)) return false;
        return Objects.equals(id, turma.id) && Objects.equals(disciplina, turma.disciplina);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, disciplina);
    }
}
