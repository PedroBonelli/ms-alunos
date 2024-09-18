package com.github.pedrobonelli.ms_alunos.repository;

import com.github.pedrobonelli.ms_alunos.model.AlunoModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoRepository extends JpaRepository<AlunoModel, Long> {
}
