package com.github.pedrobonelli.ms_alunos.service;

import com.github.pedrobonelli.ms_alunos.dto.AlunoDTO;
import com.github.pedrobonelli.ms_alunos.model.AlunoModel;
import com.github.pedrobonelli.ms_alunos.repository.AlunoRepository;
import com.github.pedrobonelli.ms_alunos.tests.Factory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(SpringExtension.class)
public class AlunoServiceTests {

    @InjectMocks
    private AlunoService service;

    @Mock
    private AlunoRepository repository;

    private Long existingId;
    private Long nonExistingId;

    private AlunoModel aluno;
    private AlunoDTO alunoDTO;

    @BeforeEach
    void setup() throws Exception{
        existingId = 1L;
        nonExistingId = 10L;

        Mockito.when(repository.existsById(existingId)).thenReturn(true);
        Mockito.when(repository.existsById(nonExistingId)).thenReturn(false);

        Mockito.doNothing().when(repository).deleteById(existingId);

        aluno = Factory.createAluno();
        alunoDTO = new AlunoDTO(aluno);

        Mockito.when(repository.findById(existingId)).thenReturn(Optional.of(aluno));
        Mockito.when(repository.findById(nonExistingId)).thenReturn(Optional.empty());

        Mockito.when(repository.save(any())).thenReturn(aluno);

        //update - primeiro caso - id existe
        Mockito.when(repository.getReferenceById(existingId)).thenReturn(aluno);

        //update - segundo caso - id não existe
        Mockito.when(repository.getReferenceById(nonExistingId)).thenReturn(aluno);

        //delete - quando id existe
        Mockito.doNothing().when(repository).deleteById(existingId);
    }

    @Test
    @DisplayName("delete deveria não fazer nada quando ID existe")
    public void deleteShouldDoNothingWhenIdExists(){
        Assertions.assertDoesNotThrow(
                () -> {
                    service.delete(existingId);
                }
        );
    }

    @Test
    public void deleteShouldThrowEmptyResultDataAccessExceptionWhenIdDoesNotExist(){
        long nonExistingId = 100L;
        Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
            repository.deleteById(nonExistingId);
        });
    }



}
