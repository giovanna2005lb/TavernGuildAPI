package com.TavernGuild.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.TavernGuild.demo.model.ClassType;
import com.TavernGuild.demo.model.Personagem;
import com.TavernGuild.demo.repository.PersonagemRepository;

@RestController
@RequestMapping("/characters")
public class PersonagemController {
    
    @Autowired
    private PersonagemRepository repository;

    @GetMapping
    public List<Personagem> getAll() {
        return repository.findAll();
    }

    @GetMapping("nome/{nome}")
    public Personagem get(@PathVariable String nome) {
        return repository.findByNome(nome).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping("classe/{classe}")
    public List<Personagem> get(@PathVariable ClassType classe) {
        return repository.findByClasse(classe).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Personagem> create(@RequestBody Personagem personagem){
        repository.save(personagem);
        return ResponseEntity.status(201).body(personagem);
    }

    @PutMapping ("{id}")
    public Personagem update(@PathVariable Long id, @RequestBody Personagem personagem) {
        personagem.setId(id);
        return repository.save(personagem);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        repository.delete(repository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND)));
    }

}