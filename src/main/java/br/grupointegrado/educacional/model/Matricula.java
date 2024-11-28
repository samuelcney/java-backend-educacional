package br.grupointegrado.educacional.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "matriculas")
public class Matricula {

    @EmbeddedId
    MatriculaPK matriculaPK;

    @OneToMany(mappedBy = "matricula")
    @JsonIgnoreProperties("matricula")
    private List<Nota> notas;

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

    public List<Nota> getNotas() {
        return notas;
    }

    public void setNotas(List<Nota> notas) {
        this.notas = notas;
    }
}
