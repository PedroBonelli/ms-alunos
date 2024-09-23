package com.github.pedrobonelli.ms_alunos.tests;

import com.github.pedrobonelli.ms_alunos.dto.AlunoDTO;
import com.github.pedrobonelli.ms_alunos.model.AlunoModel;
import com.github.pedrobonelli.ms_alunos.model.Status;

public class Factory {

    public static AlunoModel createAluno(){

        AlunoModel aluno = new AlunoModel(1L, "Josefo", "josefo@shabt.com", "1234", "93451", Status.MATRICULADO, "3SIG");

        return aluno;
    }

    public static AlunoDTO createAlunoDTO(){
        AlunoModel aluno = createAluno();
        return new AlunoDTO(aluno);
    }
}
