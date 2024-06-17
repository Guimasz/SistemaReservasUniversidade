package Service;
import Dao.AlunoDao;
import model.Aluno;

import java.util.ArrayList;

public class AlunoService {
   AlunoDao alunoDao;

    public AlunoService() {
        this.alunoDao = new AlunoDao();
    }

    public ArrayList<Aluno> findAll() {

        if (alunoDao.findAll() == null) {
            throw new RuntimeException("Não existem alunos cadastrados");
        }

        return alunoDao.findAll();
    }

    public Aluno findAlunobyMatricula(Integer matricula) {
        Aluno aluno = alunoDao.findAlunobyMatricula(matricula);
        if (aluno == null) {
           throw new RuntimeException("Aluno não encontrado");
        }
        return aluno;
    }

    public void criarAluno(Aluno aluno) {

        if ((aluno.getNome() == null) || (aluno.getTurma() == null) || (aluno.getTurma() == null) || (aluno.isStatus() == null)) {
            throw new RuntimeException("Existem campos obrigatórios não preenchidos");
        }

        alunoDao.criarAluno(aluno);
    }

    public void atualizarAluno(Aluno aluno) {
        if ((aluno.getNome() == null) || (aluno.getTurma() == null) || (aluno.getTurma() == null) || (aluno.isStatus() == null)) {
            throw new RuntimeException("Existem campos obrigatórios não preenchidos");
        }
        alunoDao.atualizarAluno(aluno);
    }

    public void deletarAluno(Integer matricula) {
        alunoDao.deletarAluno(matricula);
    }



}
