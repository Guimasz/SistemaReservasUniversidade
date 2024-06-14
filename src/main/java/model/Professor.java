package model;

import java.util.List;

public class Professor {
    private Integer id;
    private String nome;
    private Boolean status;

    private List<Disciplina> pdisciplinas;

    public Professor() {
    }

    public Professor(Integer id, String nome, Boolean status, List<Disciplina> pdisciplinas) {
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

    public Boolean isStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public List<Disciplina> getPdisciplinas() {
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
                ", pdisciplinas=" + pdisciplinas +
                '}';
    }
}
