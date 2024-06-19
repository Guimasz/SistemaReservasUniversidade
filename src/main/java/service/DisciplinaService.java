package service;

import dao.DisciplinaDao;
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

    public void criarDisciplina(String sigla, String descricao, Boolean status) {
        Disciplina novaDisciplina = new Disciplina();
        novaDisciplina.setSigla(sigla);
        novaDisciplina.setDescricao(descricao);
        novaDisciplina.setStatus(status);

        if ((novaDisciplina.getSigla() == null) || (novaDisciplina.getDescricao() == null) || (novaDisciplina.isStatus() == null)) {
            throw new RuntimeException("Existem campos obrigatórios não preenchidos");
        }

        disciplinaDao.criarDisciplina(novaDisciplina);

    }

    public void atualizarDisciplina(Integer id, String sigla, String descricao, Boolean status) {
        Disciplina novaDisciplina = new Disciplina();
        novaDisciplina.setSigla(sigla);
        novaDisciplina.setDescricao(descricao);
        novaDisciplina.setStatus(status);

        if ((novaDisciplina.getSigla() == null) || (novaDisciplina.getDescricao() == null) || (novaDisciplina.isStatus() == null)) {
            throw new RuntimeException("Existem campos obrigatórios não preenchidos");
        }
        disciplinaDao.atualizarDisciplina(id,novaDisciplina);
    }

    public void deletarDisciplina(Integer id) {
        disciplinaDao.deletarDisciplina(id);
    }




}
