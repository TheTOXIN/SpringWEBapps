package com.toxin.springraph.service;

import com.toxin.springraph.entity.Cart;
import com.toxin.springraph.entity.Item;
import com.toxin.springraph.repository.CartRepository;
import com.toxin.springraph.repository.ItemRepository;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final ItemRepository itemRepository;

    @Autowired
    public CartService(CartRepository cartRepository, ItemRepository itemRepository) {
        this.cartRepository = cartRepository;
        this.itemRepository = itemRepository;
    }

    @GraphQLQuery(name = "carts")
    public List<Cart> getCarts() {
        return cartRepository.findAll();
    }

    @GraphQLQuery(name = "cart")
    public Optional<Cart> getCartById(@GraphQLArgument(name = "id") Long id) {
        return cartRepository.findById(id);
    }

    @GraphQLMutation(name = "saveCart")
    public Cart saveCart(@GraphQLArgument(name = "item") Cart cart) {
        return cartRepository.save(cart);
    }

    @GraphQLMutation(name = "deleteCart")
    public void deleteCart(@GraphQLArgument(name = "id") Long id) {
        cartRepository.deleteById(id);
    }

    @GraphQLMutation(name = "addItem")
    public String addItem(
            @GraphQLArgument(name = "cartId") Long cartId,
            @GraphQLArgument(name = "title") String title,
            @GraphQLArgument(name = "price", defaultValue = "100") Integer price
    ) {
        Cart cart = cartRepository.findById(cartId).orElse(null);

        if (cart == null) return "CART NOT FOUND";
        if (cart.getItems().size() == cart.getCapacity()) return "CART FULL";

        Item item = new Item();
        item.setPrice(price);
        item.setTitle(title);
        item.setCart(cart);

        itemRepository.save(item);

        return "ITEM ADD TO CART";
    }

}
