package model;

import java.util.List;

public class Professor {
    private long id;
    private String nome;
    private boolean status;
    private Departamento departamento;
    private List<Disciplina> pdisciplinas;
    public Professor() {}
    public Professor(long id, String nome, boolean status, Departamento departamento) {}

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

    public boolean status() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Departamento departamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public List<Disciplina> pdisciplinas() {
        return pdisciplinas;
    }

    public void setPdisciplinas(List<Disciplina> pdisciplinas) {
        this.pdisciplinas = pdisciplinas;
    }

    @Override
    public String toString() {
        return "Professor{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", status=" + status +
                ", departamento=" + departamento +
                ", pdisciplinas=" + pdisciplinas +
                '}';
    }
}
