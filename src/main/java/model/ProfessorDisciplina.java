package model;

public class ProfessorDisciplina {
    private int idProfessor;
    private int idDisciplina;

    public ProfessorDisciplina(int idDisciplina, int idProfessor) {
        this.idDisciplina = idDisciplina;
        this.idProfessor = idProfessor;
    }

    public ProfessorDisciplina(){}

    public int getIdProfessor() {
        return idProfessor;
    }

    public void setIdProfessor(int idProfessor) {
        this.idProfessor = idProfessor;
    }

    public int getIdDisciplina() {
        return idDisciplina;
    }

    public void setIdDisciplina(int idDisciplina) {
        this.idDisciplina = idDisciplina;
    }

    @Override
    public String toString() {
        return "professorDisciplina{ " +
                "IdProfessor= " + idProfessor +
                ", IdDisciplina= " + idDisciplina +
                " }";
    }
}
