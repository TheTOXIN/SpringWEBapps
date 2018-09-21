package com.toxin.vaadinpring;

import com.toxin.vaadinpring.entity.Item;
import com.toxin.vaadinpring.repository.ItemRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication
public class VaadinpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(VaadinpringApplication.class, args);
    }

    @Bean
    public CommandLineRunner loadData(ItemRepository itemRepository) {
        return (args) -> {
            Arrays.asList(
                    "PS4", "Epica", "Giros", "Xiaomi Mi PRO", "SHLUHA"
            ).forEach(title -> {
                Item item = new Item();

                item.setTitle(title);
                item.setPrice((int) (Math.random() * 100000));
                if ((int) (Math.random() * 1) == 0) item.setDescription("Hmmm...");

                itemRepository.save(item);
            });

            itemRepository.findAll().forEach(System.out::print);
        };
    }

}
