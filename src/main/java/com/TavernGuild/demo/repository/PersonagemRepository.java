package com.TavernGuild.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.TavernGuild.demo.model.Personagem;

public interface PersonagemRepository extends JpaRepository<Personagem, Long> {

}
