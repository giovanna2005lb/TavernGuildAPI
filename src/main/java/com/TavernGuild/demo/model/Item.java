package com.TavernGuild.demo.model;

import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class Item {
    private String nome;
    private ItemType itemtype;
    private RarityType raritytype;
    private int preco;
    private Personagem dono;
}
