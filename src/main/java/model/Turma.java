package model;

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
        return "Turma{" +
                "id=" + id +
                ", disciplina=" + disciplina +
                '}';
    }
}
