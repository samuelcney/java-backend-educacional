create table professores(
    id int not null primary key auto_increment,
    nome varchar(200) not null,
    email varchar(400) unique not null,
    telefone varchar(11),
    especialidade varchar(100)
);

create table cursos(
    id int not null primary key auto_increment,
    nome varchar(200) not null,
    codigo varchar(20) not null unique,
    carga_horaria int not null
);

create table turmas(
    id int not null primary key auto_increment,
    ano int,
    semestre int check (semestre in (1, 2)),
    curso_id int,
    foreign key (curso_id) references cursos(id)
);