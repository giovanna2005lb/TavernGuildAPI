package com.TavernGuild.demo.model;

import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class Personagem {
    private String nome;
    private ClassType classtype;
    private int nivel;
    private int moedas;
}
