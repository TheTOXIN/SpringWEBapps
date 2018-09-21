package com.toxin.vaadinpring.view;

import com.toxin.vaadinpring.entity.Item;
import com.toxin.vaadinpring.repository.ItemRepository;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route
public class ItemView extends VerticalLayout {

    private final ItemRepository itemRepository;
    private final Grid<Item> itemGrid;

    public ItemView(
        ItemRepository itemRepository
    ) {
        this.itemRepository = itemRepository;
        this.itemGrid = new Grid<>(Item.class);

        initGrid();

        add(itemGrid);
    }

    private void initGrid() {
        itemGrid.setHeight("300px");
        itemGrid.setColumns("id", "title", "price", "description", "buy");
        itemGrid.getColumnByKey("id").setWidth("50px").setFlexGrow(0);

        itemGrid.setItems(itemRepository.findAll());
    }

}
