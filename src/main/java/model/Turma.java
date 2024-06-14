package model;

public class Turma {
    private int id;
    private Disciplina disciplina;

    public Turma(int id, Disciplina disciplina) {
        this.id = id;
        this.disciplina = disciplina;
    }
    public Turma() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
        return "Turma{" +
                "id=" + id +
                ", disciplina=" + disciplina +
                '}';
    }
}
