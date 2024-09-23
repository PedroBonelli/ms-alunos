package com.github.pedrobonelli.ms_alunos.service;

import com.github.pedrobonelli.ms_alunos.dto.AlunoDTO;
import com.github.pedrobonelli.ms_alunos.model.AlunoModel;
import com.github.pedrobonelli.ms_alunos.repository.AlunoRepository;
import com.github.pedrobonelli.ms_alunos.service.exception.DatabaseException;
import com.github.pedrobonelli.ms_alunos.service.exception.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository repository;

    @Autowired
    private ModelMapper mapper;

    @Transactional
    public List<AlunoDTO> findAll(){
        List<AlunoDTO> alunoDTOS = new ArrayList<>();

        for(AlunoModel alunoEntity : repository.findAll()){
            alunoDTOS.add(mapper.map(alunoEntity, AlunoDTO.class));
        }

        return alunoDTOS;
    }

    @Transactional
    public AlunoDTO findById(long id){
        return mapper.map(repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado - ID"+id)), AlunoDTO.class);
    }

    @Transactional
    public AlunoDTO insert(AlunoDTO dto){
        return mapper.map(repository.save(mapper.map(dto, AlunoModel.class)), AlunoDTO.class);
    }

    @Transactional
    public AlunoDTO update(Long id, AlunoDTO dto){
        try{
            AlunoModel entity = repository.getReferenceById(id);
            mapper.map(dto, entity);
            entity = repository.save(entity);
            return mapper.map(entity, AlunoDTO.class);
        } catch (EntityNotFoundException e){
            throw new ResourceNotFoundException("Recurso não encontrado - ID "+id);
        }
    }

    @Transactional
    public void delete(Long id){
        if(!repository.existsById(id)){
            throw new ResourceNotFoundException("Recurso não encontrado - ID "+id);
        }
        try{
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e){
            throw new DatabaseException("Erro de integridade do banco");
        }
    }


}
