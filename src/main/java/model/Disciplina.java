package model;

public class Disciplina {

    private String sigla;
    private String descricao;
    private long id;
    private boolean status;

    public Disciplina(String sigla, String descricao, long id, boolean status) {
        this.sigla = sigla;
        this.descricao = descricao;
        this.id = id;
        this.status = status;
    }

    public String sigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public long id() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean status() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String descricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return "Disciplina{" +
                "sigla='" + sigla + '\'' +
                ", descricao='" + descricao + '\'' +
                ", id=" + id +
                ", status=" + status +
                '}';
    }
}
