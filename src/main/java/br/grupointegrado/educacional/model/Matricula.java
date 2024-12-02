package br.grupointegrado.educacional.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "matriculas")
@JsonIgnoreProperties({"turmas"})
public class Matricula {

    @EmbeddedId
    private MatriculaPK matriculaPK;

    @JsonManagedReference
    public MatriculaPK getMatriculaPK() {
        return matriculaPK;
    }

    public void setMatriculaPK(MatriculaPK matriculaPK) {
        this.matriculaPK = matriculaPK;
    }

    @JsonManagedReference
    public Aluno getAluno(){
        return matriculaPK != null ? matriculaPK.getAluno() : null;
    }

    public void setAluno(Aluno aluno){
        if(this.matriculaPK == null){
            this.matriculaPK = new MatriculaPK();
        }
        this.matriculaPK.setAluno(aluno);
    }

    @JsonManagedReference
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
