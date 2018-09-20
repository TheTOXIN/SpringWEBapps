package com.toxin.springraph;

import com.toxin.springraph.entity.Cart;
import com.toxin.springraph.entity.Item;
import com.toxin.springraph.repository.ItemRepository;
import com.toxin.springraph.service.CartService;
import com.toxin.springraph.service.ItemService;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.stream.Stream;

@SpringBootApplication
public class SpringraphApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringraphApplication.class, args);
	}

	@Bean
	public ApplicationRunner init(
		ItemService itemService,
		CartService cartService
	) {
		return args -> {
			Stream.of(
				"Epica", "PS4", "Xiaomi", "Giros"
			).forEach(title -> {
				Item item = new Item();

				item.setPrice((int) (Math.random() * 1000));
				item.setTitle(title);

				itemService.saveItem(item);
			});

			Stream.of(
				"MAGNIT", "DNS"
			).forEach(name -> {
				Cart cart = new Cart();

				cart.setCapacity((int) (Math.random() * 100));
				cart.setName(name);

				cartService.saveCart(cart);
			});

			cartService.getCarts().forEach(System.out::println);
			itemService.getItems().forEach(System.out::println);
		};
	}

}
