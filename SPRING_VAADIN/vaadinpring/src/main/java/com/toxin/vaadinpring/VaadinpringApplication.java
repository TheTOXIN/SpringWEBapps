package com.toxin.vaadinpring;

import com.toxin.vaadinpring.entity.Item;
import com.toxin.vaadinpring.repository.ItemRepository;
import com.toxin.vaadinpring.view.ItemView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterListener;
import com.vaadin.flow.router.Route;
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

    @Route
    public static class MainView extends VerticalLayout {

        private Label label = new Label();
        private Button button = new Button();

        public MainView() {
            label.setText("HELLO SPRING VAADIN");
            button.setText("GO TO ITEMS");

            label.setHeight("100");
            button.addClickListener(event -> System.out.println("SUKA"));

            setDefaultHorizontalComponentAlignment(Alignment.CENTER);

            add(label);
            add(button);
        }
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
