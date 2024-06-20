package service;

import dao.ProfessorDisciplinaDao;
import model.ProfessorDisciplina;

import java.sql.Array;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProfessorDisciplinaService {
    private ProfessorDisciplinaDao professorDisciplinaDao;

    public ProfessorDisciplinaService() {
        professorDisciplinaDao = new ProfessorDisciplinaDao();
    }

    public void criarProfDis(int idProfessor, int idDisciplina) throws SQLException {
        ProfessorDisciplina professorDisciplina = new ProfessorDisciplina(idDisciplina, idProfessor);
        professorDisciplinaDao.criarProfDis(professorDisciplina);
    }

    public ArrayList<ProfessorDisciplina> findAll() throws SQLException {
        return professorDisciplinaDao.findAll();
    }

    public ArrayList<ProfessorDisciplina> findByProfessor(int idProfessor) throws SQLException {
        return professorDisciplinaDao.findByProfessor(idProfessor);
    }

    public void atualizarProfDis(int idProfessor, int idDisciplina, int newIdProfessor, int newIdDisciplina) throws SQLException {
        ProfessorDisciplina professorDisciplina = new ProfessorDisciplina(newIdDisciplina, newIdProfessor);
        professorDisciplinaDao.atualizarProfDis(professorDisciplina);
    }

    public void deletarProfDis(int idProfessor, int idDisciplina) throws SQLException {
        professorDisciplinaDao.deletarProfDis(idProfessor, idDisciplina);
    }
}
