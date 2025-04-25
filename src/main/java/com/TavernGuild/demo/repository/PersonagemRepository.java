package com.TavernGuild.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.TavernGuild.demo.model.ClassType;
import com.TavernGuild.demo.model.Personagem;

public interface PersonagemRepository extends JpaRepository<Personagem, Long> {

    Optional<Personagem> findByNome(String nome);
    Optional<List<Personagem>> findByClasse(ClassType classe);


}
