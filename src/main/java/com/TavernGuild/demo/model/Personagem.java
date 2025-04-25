package com.TavernGuild.demo.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Personagem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "O nome é obrigatório")
    private String nome;
    @Enumerated(EnumType.STRING)
    private ClassType classe;
    private int nivel;
    private int moedas;
    @ManyToMany
    @JoinTable(
        name = "personagem_item",
        joinColumns = @JoinColumn(name = "personagem_id"),
        inverseJoinColumns = @JoinColumn(name = "item_id")
    )
    private List<Item> itens = new ArrayList<>();
}
