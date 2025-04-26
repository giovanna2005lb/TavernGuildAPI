package com.TavernGuild.demo.dto;

import com.TavernGuild.demo.model.ItemType;
import com.TavernGuild.demo.model.RarityType;
import lombok.Data;

@Data
public class ItemDTO {
    private String nome;
    private ItemType tipo;
    private RarityType raridade;
    private Integer precoMin;
    private Integer precoMax;
}
