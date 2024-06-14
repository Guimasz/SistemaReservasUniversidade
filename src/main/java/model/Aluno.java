package model;

public class Aluno {

    private Integer id;
    private String nome;
    private String matricula;
    private Turma turma;
    private Boolean status;


    public Aluno(Integer id, String nome, String matricula, Boolean status) {
        this.id = id;
        this.nome = nome;
        this.matricula = matricula;
        this.status = status;
    }

    public Aluno() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
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
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", matricula='" + matricula + '\'' +
                ", turma=" + turma +
                ", status=" + status +
                '}';
    }
}
