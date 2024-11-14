create table professores(
    id int not null primary auto_increment,
    nome varchar(200) not null,
    email varchar(400) unique not null,
    telefone varchar(11),
    especialidade varchar(100)
)

