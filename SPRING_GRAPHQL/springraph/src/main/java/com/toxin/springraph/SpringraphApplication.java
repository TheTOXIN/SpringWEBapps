package com.toxin.springraph;

import com.toxin.springraph.entity.Cart;
import com.toxin.springraph.entity.Item;
import com.toxin.springraph.repository.CartRepository;
import com.toxin.springraph.repository.ItemRepository;
import com.toxin.springraph.service.CartService;
import com.toxin.springraph.service.ItemService;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


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
            Cart cart1 = new Cart();
            cart1.setName("MAGNIT");
            cart1.setCapacity(10);

            Cart cart2 = new Cart();
            cart2.setName("DNS");
            cart2.setCapacity(5);

            cartService.saveCart(cart1);
            cartService.saveCart(cart2);

            Item item1 = new Item();
			item1.setTitle("Epica");
			item1.setPrice(56);

            Item item2 = new Item();
            item2.setTitle("Giros");
            item2.setPrice(230);

            Item item3 = new Item();
            item3.setTitle("Xiaomi Mi PRO");
            item3.setPrice(70000);

            Item item4 = new Item();
            item4.setTitle("PS4");
            item4.setPrice(25000);

            item1.setCart(cart1);
            item2.setCart(cart1);
            item3.setCart(cart2);
            item4.setCart(cart2);

            itemService.saveItem(item1);
			itemService.saveItem(item2);
			itemService.saveItem(item3);
			itemService.saveItem(item4);
		};
	}

}
