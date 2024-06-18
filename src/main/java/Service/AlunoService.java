package Service;

import dao.AlunoDao;
import model.Aluno;
import model.Turma;

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

    public void criarAluno(String nome, Turma turma, Boolean status) {
        Aluno novoAluno = new Aluno();
        novoAluno.setNome(nome);
        novoAluno.setTurma(turma);
        novoAluno.setStatus(status);

        if ((novoAluno.getNome() == null) || (novoAluno.getTurma() == null) || (novoAluno.isStatus() == null)) {
            throw new RuntimeException("Existem campos obrigatórios não preenchidos");
        }

        alunoDao.criarAluno(novoAluno);
    }

    public void atualizarAluno(Integer matricula, String nome, Turma turma, Boolean status) {
        Aluno novoAluno = new Aluno();
        novoAluno.setNome(nome);
        novoAluno.setTurma(turma);
        novoAluno.setStatus(status);

        if ((novoAluno.getNome() == null) || (novoAluno.getTurma() == null) || (novoAluno.isStatus() == null)) {
            throw new RuntimeException("Existem campos obrigatórios não preenchidos");
        }
        alunoDao.atualizarAluno(matricula, novoAluno);
    }

    public void deletarAluno(Integer matricula) {

        alunoDao.deletarAluno(matricula);
    }


}
