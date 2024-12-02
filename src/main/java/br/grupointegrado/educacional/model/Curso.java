    package br.grupointegrado.educacional.model;

    import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
    import jakarta.persistence.*;

    import java.util.List;

    @Entity
    @Table(name = "cursos")
    @JsonIgnoreProperties({"disciplinas.curso", "turmas.curso"})
    public class Curso {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;

        @Column
        private String nome;

        @Column
        private String codigo;

        @Column(name = "carga_horaria")
        private Integer cargaHoraria;

        @OneToMany(mappedBy = "curso")
        @JsonIgnoreProperties("curso")
        private List<Turma> turmas;

        @OneToMany(mappedBy = "curso")
        @JsonIgnoreProperties("curso")
        private List<Disciplina> disciplinas;

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

        public String getCodigo() {
            return codigo;
        }

        public void setCodigo(String codigo) {
            this.codigo = codigo;
        }

        public Integer getCarga_horaria() {
            return cargaHoraria;
        }

        public void setCarga_horaria(Integer carga_horaria) {
            this.cargaHoraria = carga_horaria;
        }

        public List<Turma> getTurmas() {
            return turmas;
        }

        public void setTurmas(List<Turma> turmas) {
            this.turmas = turmas;
        }
    }
