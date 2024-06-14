package model;

public class Aluno {

    private Integer matricula;
    private String nome;
    private Turma turma;
    private Boolean status;

    public Aluno(Integer matricula, String nome, Boolean status) {
        this.matricula = matricula;
        this.nome = nome;
        this.status = status;
    }

    public Aluno() {
    }

    public Integer getMatricula() {
        return matricula;
    }

    public void setMatricula(Integer matricula) {
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Turma getTurma() {
        return turma;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }

    public Boolean isStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Aluno{" +
                "matricula=" + matricula +
                ", nome='" + nome + '\'' +
                ", turma=" + turma +
                ", status=" + status +
                '}';
    }
}
