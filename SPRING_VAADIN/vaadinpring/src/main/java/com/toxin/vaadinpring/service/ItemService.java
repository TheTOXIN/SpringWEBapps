package com.toxin.vaadinpring.service;

import com.toxin.vaadinpring.entity.Item;
import com.toxin.vaadinpring.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    @Autowired
    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public List<Item> findAll() {
        return itemRepository.findAll();
    }

    public Item find(Long id) {
        return itemRepository.findById(id).get();
    }

    public Item findByFilter(String value) {
        return itemRepository.findByTitleStartsWithIgnoreCase(value);
    }

    public void save(Item item) {
        itemRepository.save(item);
    }

    public void delete(Long id) {
        itemRepository.deleteById(id);
    }

    public int countPrice() {
        return itemRepository.findAll()
            .stream()
            .mapToInt(Item::getPrice)
            .sum();
    }

    public Item getMock() {
        Item mock = new Item();

        mock.setDescription("");
        mock.setTitle("");
        mock.setPrice(0);
        mock.setBuy(false);

        return mock;
    }

}
