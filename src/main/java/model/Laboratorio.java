package model;

public class Laboratorio {

    private Integer id;
    private String descricao;
    private Integer capacidade;
    private Boolean status;

    public Laboratorio(Integer id, String descricao, Boolean status, Integer capacidade) {
        this.id = id;
        this.descricao = descricao;
        this.status = status;
        this.capacidade = capacidade;
    }

    public Laboratorio() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(Integer capacidade) {
        this.capacidade = capacidade;
    }

    public Boolean isStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Laboratorio{ " +
                "Id= " + id +
                ", Descricao= '" + descricao + '\'' +
                ", Capacidade= " + capacidade +
                ", Status= " + status +
                '}';
    }
}
