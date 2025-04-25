package com.TavernGuild.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.TavernGuild.demo.model.Item;

public interface ItemRepository extends JpaRepository<Item, Long>{
    
}
