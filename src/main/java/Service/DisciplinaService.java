package Service;

import Dao.DisciplinaDao;
import model.Disciplina;

public class DisciplinaService {
    DisciplinaDao disciplinaDao;


    public DisciplinaService() {
        this.disciplinaDao = new DisciplinaDao();
    }

    public void findAll() {
        if (disciplinaDao.findAll() == null) {
            throw new RuntimeException("Não existem disciplinas cadastradas");
        }
        disciplinaDao.findAll();
    }

    public Disciplina findDisciplinabyId(Integer id) {
        Disciplina disciplina = disciplinaDao.findDisciplinaById(id);
        if (disciplina == null) {
            throw new RuntimeException("Disciplina não encontrada");
        }
        return disciplina;
    }

    public void criarDisciplina(Disciplina disciplina) {

        if ((disciplina.getSigla() == null) || (disciplina.getDescricao() == null) || (disciplina.isStatus() == null)) {
            throw new RuntimeException("Existem campos obrigatórios não preenchidos");
        }

        disciplinaDao.criarDisciplina(disciplina);

    }

    public void atualizarDisciplina(Disciplina disciplina) {
        if ((disciplina.getSigla() == null) || (disciplina.getDescricao() == null) || (disciplina.isStatus() == null)) {
            throw new RuntimeException("Existem campos obrigatórios não preenchidos");
        }
        disciplinaDao.atualizarDisciplina(disciplina);
    }

    public void deletarDisciplina(Integer id) {
        disciplinaDao.deletarDisciplina(id);
    }




}
