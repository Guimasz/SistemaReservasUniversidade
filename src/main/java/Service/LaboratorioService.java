package Service;
import Dao.LaboratorioDao;
import model.Laboratorio;

import java.util.ArrayList;

public class LaboratorioService {

    private final LaboratorioDao laboratorioDao;

    public LaboratorioService() {
        this.laboratorioDao = new LaboratorioDao();
    }

    public Laboratorio findLaboratoriobyId(Integer id) {
        Laboratorio laboratorio = laboratorioDao.findLaboratorioById(id);

        if (laboratorio == null) {
            throw new RuntimeException("Laboratório não encontrado");
        }
        return laboratorioDao.findLaboratorioById(id);
    }

    public void criarLaboratorio(Laboratorio laboratorio) {
        if ((laboratorio.getDescricao() == null) || (laboratorio.getCapacidade() == null) || (laboratorio.isStatus() == null)) {
            throw new RuntimeException("Existem campos obrigatórios não preenchidos");
        }
        laboratorioDao.criarLaboratorio(laboratorio);
    }

    public void atualizarLaboratorio(Laboratorio laboratorio) {
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


}
