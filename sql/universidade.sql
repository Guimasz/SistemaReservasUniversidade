create table disciplina(
	id integer primary key not null,
	sigla varchar(50) not null,
	descricao varchar(50),
	status boolean
)

create table professor (
	id integer primary key not null,
	nome varchar(50) not null,
	status boolean
)

CREATE TABLE professorDisciplina (
    idProfessor INTEGER NOT NULL,
    idDisciplina INTEGER NOT NULL,
    PRIMARY KEY (idProfessor, idDisciplina),
    FOREIGN KEY (idDisciplina) REFERENCES disciplina(id),
    FOREIGN KEY (idProfessor) REFERENCES professor(id)
)

create table turma (
	id integer primary key not null, 
	disciplina integer not null,
	constraint fk_disciplina foreign key (disciplina) references disciplina(id)

)

create table laboratorio (
	id integer primary key not null,
	descricao varchar(200) not null,
	capacidade integer not null,
	status boolean

)

create table reserva (
	id integer primary key not null,
	laboratorio integer not null,
	professor integer not null,
	turma integer not null, 
	dataHora timestamp not null,
	duracao integer,
	aprovada boolean,
	constraint fk_lab foreign key (laboratorio) references laboratorio(id),
	constraint fk_prof foreign key (professor) references professor(id),
	constraint fk_turma foreign key (turma) references turma(id)

)

create table aluno (
	matricula integer primary key not null,
	nome varchar(50) not null,
	status boolean,
	turma integer not null,
	constraint fk_turma foreign key (turma) references turma(id)
)


