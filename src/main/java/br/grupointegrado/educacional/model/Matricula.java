package br.grupointegrado.educacional.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "matriculas")
@JsonIgnoreProperties({"turmas"})
public class Matricula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Embedded
    private MatriculaPK matriculaPK;

    @JsonManagedReference
    public MatriculaPK getMatriculaPK() {
        return matriculaPK;
    }

    public void setMatriculaPK(MatriculaPK matriculaPK) {
        this.matriculaPK = matriculaPK;
    }

    @JsonIgnore
    public Aluno getAluno(){
        return matriculaPK != null ? matriculaPK.getAluno() : null;
    }

    public void setAluno(Aluno aluno){
        if(this.matriculaPK == null){
            this.matriculaPK = new MatriculaPK();
        }
        this.matriculaPK.setAluno(aluno);
    }

    @JsonIgnore
    public Turma getTurma(){
        return matriculaPK != null ? matriculaPK.getTurma() : null;
    }

    public void setTurma(Turma turma){
        if(this.matriculaPK == null){
            this.matriculaPK = new MatriculaPK();
        }

        this.matriculaPK.setTurma(turma);
    }
}
