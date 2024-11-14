create table alunos(
    id int not null primary key auto_increment,
    nome varchar(200) not null,
    email varchar(400) unique not null,
    matricula varchar(20) unique not null,
    data_nascimento date
)