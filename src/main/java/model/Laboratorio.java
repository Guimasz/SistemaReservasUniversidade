package model;

public class Laboratorio {

    private int id;
    private String descricao;
    private int capacidade;
    private boolean status;

    public Laboratorio(int id, String descricao, boolean status, int capacidade) {
        this.id = id;
        this.descricao = descricao;
        this.status = status;
        this.capacidade = capacidade;
    }

    public Laboratorio() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Laboratorio{" +
                "id=" + id +
                ", descricao='" + descricao + '\'' +
                ", capacidade=" + capacidade +
                ", status=" + status +
                '}';
    }
}
