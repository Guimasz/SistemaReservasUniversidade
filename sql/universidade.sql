
CREATE TABLE IF NOT EXISTS disciplina (
    id serial PRIMARY KEY NOT NULL,
    sigla varchar(50) NOT NULL,
    descricao varchar(50),
    status boolean
);


CREATE TABLE IF NOT EXISTS professor (
    id serial PRIMARY KEY NOT NULL,
    nome varchar(50) NOT NULL,
    status boolean
);


CREATE TABLE IF NOT EXISTS professorDisciplina (
    idProfessor INTEGER NOT NULL,
    idDisciplina INTEGER NOT NULL,
    PRIMARY KEY (idProfessor, idDisciplina),
    FOREIGN KEY (idDisciplina) REFERENCES disciplina(id) ON DELETE CASCADE,
    FOREIGN KEY (idProfessor) REFERENCES professor(id) ON DELETE CASCADE
);


CREATE TABLE IF NOT EXISTS turma (
    id serial PRIMARY KEY NOT NULL,
    disciplina INTEGER NOT NULL,
    CONSTRAINT fk_disciplina FOREIGN KEY (disciplina) REFERENCES disciplina(id) ON DELETE CASCADE
);


CREATE TABLE IF NOT EXISTS laboratorio (
    id serial PRIMARY KEY NOT NULL,
    descricao varchar(200) NOT NULL,
    capacidade INTEGER NOT NULL,
    status boolean
);


CREATE TABLE IF NOT EXISTS reserva (
    id serial PRIMARY KEY NOT NULL,
    laboratorio INTEGER NOT NULL,
    professor INTEGER NOT NULL,
    turma INTEGER NOT NULL,
    dataHora timestamp NOT NULL,
    duracao INTEGER,
    aprovada boolean,
    CONSTRAINT fk_lab FOREIGN KEY (laboratorio) REFERENCES laboratorio(id) ON DELETE CASCADE,
    CONSTRAINT fk_prof FOREIGN KEY (professor) REFERENCES professor(id) ON DELETE CASCADE,
    CONSTRAINT fk_turma FOREIGN KEY (turma) REFERENCES turma(id) ON DELETE CASCADE
);


CREATE TABLE IF NOT EXISTS aluno (
    matricula serial PRIMARY KEY NOT NULL,
    nome varchar(50) NOT NULL,
    status boolean,
    turma INTEGER NOT NULL,
    CONSTRAINT fk_turma FOREIGN KEY (turma) REFERENCES turma(id) ON DELETE CASCADE
);


CREATE VIEW  vw_reserva_detalhada AS
SELECT r.id, r.datahora, r.duracao, r.aprovada,
       l.id AS lab_id, l.descricao AS lab_desc, l.capacidade AS lab_cap, l.status AS lab_status,
       p.id AS prof_id, p.nome AS prof_nome, p.status AS prof_status,
       t.id AS turma_id, d.id AS disc_id, d.sigla AS disc_sigla, d.descricao AS disc_desc, d.status AS disc_status
FROM reserva r
INNER JOIN laboratorio l ON r.laboratorio = l.id
INNER JOIN professor p ON r.professor = p.id
INNER JOIN turma t ON r.turma = t.id
INNER JOIN disciplina d ON t.disciplina = d.id;


CREATE VIEW  vw_reserva_aprovadas AS
SELECT r.id, r.datahora, r.duracao, r.aprovada,
       l.id AS lab_id, l.descricao AS lab_desc, l.capacidade AS lab_cap, l.status AS lab_status,
       p.id AS prof_id, p.nome AS prof_nome, p.status AS prof_status,
       t.id AS turma_id, d.id AS disc_id, d.sigla AS disc_sigla, d.descricao AS disc_desc, d.status AS disc_status
FROM reserva r
INNER JOIN laboratorio l ON r.laboratorio = l.id
INNER JOIN professor p ON r.professor = p.id
INNER JOIN turma t ON r.turma = t.id
INNER JOIN disciplina d ON t.disciplina = d.id
WHERE r.aprovada = true;

CREATE VIEW vw_professor_disciplina AS
SELECT p.nome as nome_professor, d.id as idDisciplina, d.sigla as sigla_disciplina, d.descricao as nome_disciplina
FROM professofessorDisciplina pd
INNER JOIN professor p on pd.idProfessor = p.id
INNER JOIN disciplina d on pd.idDisciplina = d.id

