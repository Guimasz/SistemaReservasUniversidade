package Service;

import Dao.ProfessorDao;
import model.Professor;

public class ProfessorService {

   ProfessorDao professorDao;

   public ProfessorService() {
       this.professorDao = new ProfessorDao();
   }

   public void criarProfessor(Professor professor) {
       if ((professor.getNome() == null) || (professor.isStatus() == null)) {
           throw new RuntimeException("Existem campos obrigatórios não preenchidos");
       }
       professorDao.criarProfessor(professor);
   }

   public void atualizarProfessor(Integer id, String nome, Boolean status) {
       Professor novoProfessor = new Professor();
       novoProfessor.setNome(nome);
       novoProfessor.setStatus(status);

       professorDao.atualizarProfessor(id, novoProfessor);
   }

}
