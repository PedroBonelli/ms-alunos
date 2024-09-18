package com.github.pedrobonelli.ms_alunos.dto;

import com.github.pedrobonelli.ms_alunos.model.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AlunoDTO {

    private Long id;
    private String nome;
    private String password;
    private String rm;
    private Status status;
    private String turma;

}
