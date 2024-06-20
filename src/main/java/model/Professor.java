package model;

import java.util.ArrayList;
import java.util.Objects;


public class Professor {
    private Integer id;
    private String nome;
    private Boolean status;

    public Professor() {
    }

    public Professor(Integer id, String nome, Boolean status) {
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


    @Override
    public String toString() {
        return "Professor{ " +
                "Id= " + id +
                ", Nome= '" + nome + '\'' +
                ", Status= " + status +
                " }";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Professor professor)) return false;
        return Objects.equals(id, professor.id) && Objects.equals(nome, professor.nome) && Objects.equals(status, professor.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, status);
    }
}
