package com.toxin.springraph.service;

import com.toxin.springraph.entity.Cart;
import com.toxin.springraph.repository.CartRepository;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    private final CartRepository cartRepository;

    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
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

}
