package com.github.pedrobonelli.ms_alunos.controller;

import com.github.pedrobonelli.ms_alunos.dto.AlunoDTO;
import com.github.pedrobonelli.ms_alunos.model.AlunoModel;
import com.github.pedrobonelli.ms_alunos.service.AlunoService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    @Autowired
    private AlunoService service;

    @GetMapping
    public ResponseEntity<List<AlunoDTO>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/id")
    public ResponseEntity<AlunoDTO> findById(@PathVariable("id") long id){
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<AlunoDTO> insert(@RequestBody @Valid AlunoDTO dto){
        dto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(dto.getId())
                .toUri();

        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AlunoDTO> update(@PathVariable @NotNull Long id, @RequestBody @Valid AlunoDTO dto){
        dto = service.update(id, dto);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
