package model;

public class Disciplina {

    private String sigla;
    private String descricao;
    private Integer id;
    private Boolean status;

    public Disciplina(String sigla, String descricao, Integer id, Boolean status) {
        this.sigla = sigla;
        this.descricao = descricao;
        this.id = id;
        this.status = status;
    }
    public Disciplina() {
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean isStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
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
