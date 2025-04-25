package com.TavernGuild.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.TavernGuild.demo.model.Item;
import com.TavernGuild.demo.repository.ItemRepository;

@RestController
@RequestMapping("/items")
public class ItemController {
    
    @Autowired
    private ItemRepository repository;

    @GetMapping
    public List<Item> getAll() {
        return repository.findAll();
    }
}
