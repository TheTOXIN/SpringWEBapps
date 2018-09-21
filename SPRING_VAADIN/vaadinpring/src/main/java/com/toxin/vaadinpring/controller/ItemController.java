package com.toxin.vaadinpring.controller;

import com.toxin.vaadinpring.entity.Item;
import com.toxin.vaadinpring.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("items")
public class ItemController {

    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("find-all")
    public List<Item> findAll() {
        return itemService.findAll();
    }

    @GetMapping("find/{id}")
    public Item find(@PathVariable Long id) {
        return itemService.find(id);
    }

    @PostMapping("save")
    public void save(@RequestBody Item item) {
        itemService.save(item);
    }

    @DeleteMapping("delete/{id}")
    public void delete(@PathVariable Long id) {
        itemService.delete(id);
    }

}
