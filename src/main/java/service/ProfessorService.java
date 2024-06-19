package service;

import dao.ProfessorDao;
import model.Professor;

import java.sql.SQLException;
import java.util.ArrayList;

public class ProfessorService {

   ProfessorDao professorDao;

   public ProfessorService() {
       this.professorDao = new ProfessorDao();
   }

    public void criarProfessor(String nome, Boolean status) {
        if (nome == null || status == null) {
            throw new IllegalArgumentException("Existem campos obrigatórios não preenchidos");
        }

        Professor novoProfessor = new Professor();
        novoProfessor.setNome(nome);
        novoProfessor.setStatus(status);

        professorDao.criarProfessor(novoProfessor);
    }

   public void atualizarProfessor(Integer id, String nome, Boolean status) {
       Professor novoProfessor = new Professor();
       novoProfessor.setNome(nome);
       novoProfessor.setStatus(status);

       professorDao.atualizarProfessor(id, novoProfessor);
   }

    public void deletarProfessor(Integer id) throws SQLException {
         professorDao.deletarProfessor(id);
    }

    public Professor findProfessorById(Integer id) {
        Professor professor = professorDao.findProfessorById(id);
        if (professor == null) {
            throw new RuntimeException("Professor não encontrado");
        }
        return professor;
    }

    public ArrayList<Professor> findAll() {
        if (professorDao.findAll() == null) {
            throw new RuntimeException("Não existem professores cadastrados");
        }
        return professorDao.findAll();
    }

}
