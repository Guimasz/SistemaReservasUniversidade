package model;

public class Aluno {

    private long id;
    private String nome;
    private String matricula;
    private Turma turma;
    private boolean status;


    public Aluno(long id, String nome, String matricula, boolean status) {
        this.id = id;
        this.nome = nome;
        this.matricula = matricula;
        this.status = status;
    }


    public long id() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String nome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String matricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public Turma turma() {
        return turma;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }

    public boolean status() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Aluno{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", matricula='" + matricula + '\'' +
                ", turma=" + turma +
                ", status=" + status +
                '}';
    }
}
