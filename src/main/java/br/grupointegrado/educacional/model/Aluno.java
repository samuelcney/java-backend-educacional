package br.grupointegrado.educacional.model;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "alunos")
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String nome;

    @Column
    private String email;

    @Column
    private String matricula;

    @Column(name = "data_nascimento")
    @Temporal(TemporalType.DATE)
    private Date dataNascimento;

    @OneToMany(mappedBy = "matriculaPK.aluno")
    private List<Matricula> matriculas;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public List<Matricula> getMatriculas() {
        return matriculas;
    }

    public void setMatriculas(List<Matricula> matriculas) {
        this.matriculas = matriculas;
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, email);
    }
}
