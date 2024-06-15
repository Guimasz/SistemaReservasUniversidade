package model;

import java.util.ArrayList;


public class Professor {
    private Integer id;
    private String nome;
    private Boolean status;

    private ArrayList<Disciplina> pdisciplinas;

    public Professor() {
    }

    public Professor(Integer id, String nome, Boolean status, ArrayList<Disciplina> pdisciplinas) {
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

    public ArrayList<Disciplina> getPdisciplinas() {
        return pdisciplinas;
    }

    public void setPdisciplinas(ArrayList<Disciplina> pdisciplinas) {
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
