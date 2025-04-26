package com.TavernGuild.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome é obrigatório")
    private String nome;

    @Enumerated(EnumType.STRING)
    private ItemType itemtype;

    @Enumerated(EnumType.STRING)
    private RarityType raritytype;

    private int preco;

    @ManyToOne
    private Personagem dono; // << ADICIONE ISSO AQUI
}
