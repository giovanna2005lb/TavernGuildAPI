package com.TavernGuild.demo.specification;

import com.TavernGuild.demo.dto.ItemDTO;
import com.TavernGuild.demo.model.Item;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class ItemSpecification {

    public static Specification<Item> comFiltros(ItemDTO filtro) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filtro.getNome() != null && !filtro.getNome().isBlank()) {
                predicates.add(cb.like(cb.lower(root.get("nome")), "%" + filtro.getNome().toLowerCase() + "%"));
            }

            if (filtro.getTipo() != null) {
                predicates.add(cb.equal(root.get("itemtype"), filtro.getTipo()));
            }

            if (filtro.getRaridade() != null) {
                predicates.add(cb.equal(root.get("raritytype"), filtro.getRaridade()));
            }

            if (filtro.getPrecoMin() != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("preco"), filtro.getPrecoMin()));
            }

            if (filtro.getPrecoMax() != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("preco"), filtro.getPrecoMax()));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
