package model;

public class Departamento {
    private long id;
    private String sigla;
    private String descricao;
    private boolean status;



    public long id() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String sigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String descricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public boolean status() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Departamento(long id, String sigla, String descricao, boolean status) {
        this.id = id;
        this.sigla = sigla;
        this.descricao = descricao;
        this.status = status;
    }

    @Override
    public String toString() {
        return "Departamento{" +
                "id=" + id +
                ", sigla='" + sigla + '\'' +
                ", descricao='" + descricao + '\'' +
                ", status=" + status +
                '}';
    }
}
