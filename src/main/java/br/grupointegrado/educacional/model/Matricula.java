package br.grupointegrado.educacional.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "matriculas")
public class Matricula {

    @EmbeddedId
    private MatriculaPK matriculaPK;

    public MatriculaPK getMatriculaPK() {
        return matriculaPK;
    }

    public void setMatriculaPK(MatriculaPK matriculaPK) {
        this.matriculaPK = matriculaPK;
    }

    public Aluno getAluno(){
        return matriculaPK != null ? matriculaPK.getAluno() : null;
    }

    public void setAluno(Aluno aluno){
        if(this.matriculaPK == null){
            this.matriculaPK = new MatriculaPK();
        }
        this.matriculaPK.setAluno(aluno);
    }

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
