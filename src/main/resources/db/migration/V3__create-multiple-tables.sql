create table disciplinas(
    id int not null primary key auto_increment,
    nome varchar(200) not null,
    codigo varchar(20) unique not null,
    curso_id int,
    foreign key(curso_id) references cursos(id),
    professor_id int,
    foreign key(professor_id) references professores(id)
);

create table matriculas(
    id int not null primary key auto_increment,
    aluno_id int,
    foreign key (aluno_id) references alunos(id),
    turma_id int,
    foreign key (turma_id) references turmas(id)
);

create table notas(
    id int not null primary key auto_increment,
    matricula_id int,
    foreign key (matricula_id) references matriculas(id),
    disciplina_id int,
    foreign key (disciplina_id) references disciplinas(id),
    nota decimal(5, 2),
    data_lancamento date
);