package com.toxin.springraph.service;

import com.toxin.springraph.entity.Item;
import com.toxin.springraph.repository.ItemRepository;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @GraphQLQuery(name = "items")
    public List<Item> getItems() {
        return itemRepository.findAll();
    }

    @GraphQLQuery(name = "item")
    public Optional<Item> getItemById(@GraphQLArgument(name = "id") Long id) {
        return itemRepository.findById(id);
    }

    @GraphQLMutation(name = "saveItem")
    public Item saveItem(@GraphQLArgument(name = "item") Item item) {
        return itemRepository.save(item);
    }

    @GraphQLMutation(name = "deleteItem")
    public void deleteItem(@GraphQLArgument(name = "id") Long id) {
        itemRepository.deleteById(id);
    }

}
