package service;
import dao.LaboratorioDao;
import model.Laboratorio;

import java.util.ArrayList;

public class LaboratorioService {

    private final LaboratorioDao laboratorioDao;

    public LaboratorioService() {
        this.laboratorioDao = new LaboratorioDao();
    }


    public void criarLaboratorio(String descricao, Integer capacidade, Boolean status) {
        Laboratorio novoLaboratorio = new Laboratorio();
        novoLaboratorio.setDescricao(descricao);
        novoLaboratorio.setCapacidade(capacidade);
        novoLaboratorio.setStatus(status);

        if ((novoLaboratorio.getDescricao() == null) || (novoLaboratorio.getCapacidade() == null) || (novoLaboratorio.isStatus() == null)) {
            throw new RuntimeException("Existem campos obrigatórios não preenchidos");
        }
        laboratorioDao.criarLaboratorio(novoLaboratorio);
    }

    public void atualizarLaboratorio(Integer id, String descricao, Integer capacidade, Boolean status) {
        Laboratorio laboratorio = new Laboratorio();
        if ((laboratorio.getDescricao() == null) || (laboratorio.getCapacidade() == null) || (laboratorio.isStatus() == null)) {
            throw new RuntimeException("Existem campos obrigatórios não preenchidos");
        }
        laboratorioDao.atualizarLaboratorio(laboratorio);
    }

public void deletarLaboratorio(Integer id) {
        laboratorioDao.deletarLaboratorio(id);
    }

    public ArrayList<Laboratorio> findAll() {

        if (laboratorioDao.findAll() == null) {
            throw new RuntimeException("Não existem laboratórios cadastrados");
        }

        return laboratorioDao.findAll();
    }

    public Laboratorio findLaboratorioById(Integer id) {
        Laboratorio laboratorio = laboratorioDao.findLaboratorioById(id);

        if (laboratorio == null) {
            throw new RuntimeException("Laboratório não encontrado");
        }
        return laboratorio;
    }

}
