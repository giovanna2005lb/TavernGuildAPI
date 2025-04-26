package com.TavernGuild.demo.controller;

import com.TavernGuild.demo.dto.ItemDTO;
import com.TavernGuild.demo.model.Item;
import com.TavernGuild.demo.model.Personagem;
import com.TavernGuild.demo.repository.ItemRepository;
import com.TavernGuild.demo.repository.PersonagemRepository;
import com.TavernGuild.demo.specification.ItemSpecification;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/itens")
public class ItemController {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private PersonagemRepository personagemRepository;

    @PostMapping
    public ResponseEntity<Item> createItem(@Valid @RequestBody Item item) {
        item.setDono(null);
        Item savedItem = itemRepository.save(item);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedItem);
    }

    @GetMapping
    public ResponseEntity<List<Item>> getAllItems() {
        return ResponseEntity.ok(itemRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Item> getItemById(@PathVariable Long id) {
        return itemRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Item> updateItem(@PathVariable Long id, @Valid @RequestBody Item updatedItem) {
        return itemRepository.findById(id).map(item -> {
            item.setNome(updatedItem.getNome());
            item.setItemtype(updatedItem.getItemtype());
            item.setRaritytype(updatedItem.getRaritytype());
            item.setPreco(updatedItem.getPreco());
            return ResponseEntity.ok(itemRepository.save(item));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable Long id) {
        if (!itemRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        itemRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{itemId}/comprar/{personagemId}")
    public ResponseEntity<Item> comprarItem(@PathVariable Long itemId, @PathVariable Long personagemId) {
        Optional<Item> optionalItem = itemRepository.findById(itemId);
        Optional<Personagem> optionalPersonagem = personagemRepository.findById(personagemId);

        if (optionalItem.isEmpty() || optionalPersonagem.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Item item = optionalItem.get();
        Personagem personagem = optionalPersonagem.get();

        if (item.getDono() != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null); // Já tem dono
        }

        if (personagem.getMoedas() < item.getPreco()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null); // Não tem moedas suficientes
        }

        personagem.setMoedas(personagem.getMoedas() - item.getPreco());
        item.setDono(personagem);

        personagemRepository.save(personagem);
        return ResponseEntity.ok(itemRepository.save(item));
    }

    @GetMapping("/buscar")
    public List<Item> buscarComFiltros(ItemDTO filtro) {
        Specification<Item> spec = ItemSpecification.comFiltros(filtro);
        return itemRepository.findAll(spec);
    }
}
