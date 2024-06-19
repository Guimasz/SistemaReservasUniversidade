package service;
import dao.TurmaDao;
import model.Disciplina;
import model.Laboratorio;
import model.Turma;

import java.util.ArrayList;

public class TurmaService {
    TurmaDao turmaDao;

    public TurmaService() {
        this.turmaDao = new TurmaDao();
    }

    public void criarTurma(Disciplina disciplina) {
        Turma novaTurma = new Turma();
        novaTurma.setDisciplina(disciplina);
        turmaDao.criarTurma(novaTurma);
    }

    public void atualizarTurma(Integer id, Disciplina disciplina) {
        Turma novaTurma = new Turma();
        novaTurma.setDisciplina(disciplina);

        turmaDao.atualizarTurma(id,novaTurma);
    }

public void deletarTurma(Integer id) {
        turmaDao.deletarTurma(id);
    }

    public ArrayList<Turma> findAll() {
        if (turmaDao.findAll() == null) {
            throw new RuntimeException("Não existem turmas cadastradas");
        }
       return turmaDao.findAll();
    }

    public Turma findTurmaById(Integer id) {
        Turma turma = turmaDao.findTurmaById(id);
        if (turma == null) {
            throw new RuntimeException("Turma não encontrada");
        }
        return turma;
    }

}
