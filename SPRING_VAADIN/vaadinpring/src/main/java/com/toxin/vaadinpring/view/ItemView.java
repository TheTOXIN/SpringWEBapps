package com.toxin.vaadinpring.view;

import com.toxin.vaadinpring.components.ItemEditor;
import com.toxin.vaadinpring.entity.Item;
import com.toxin.vaadinpring.service.ItemService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import org.springframework.util.StringUtils;


@Route
public class ItemView extends VerticalLayout {

    private final ItemService itemService;
    private final ItemEditor itemEditor;

    private final Grid<Item> itemGrid;
    private final TextField filter;
    private final Button addNewBtn;
    private final Label label;

    public ItemView(
        ItemService itemService,
        ItemEditor itemEditor
    ) {
        this.itemService = itemService;
        this.itemEditor = itemEditor;

        this.itemGrid = new Grid<>(Item.class);
        this.filter = new TextField();
        this.addNewBtn = new Button(VaadinIcon.PLUS.create());
        this.label = new Label(itemService.countPrice() + " RUB");

        HorizontalLayout actions = new HorizontalLayout(filter, addNewBtn, label);
        add(actions, itemGrid, itemEditor);

        init();
        listItem(null);
    }

    public void init() {
        itemGrid.setHeight("300px");
        itemGrid.setColumns("id", "title", "price");
        itemGrid.getColumnByKey("id").setWidth("50px").setFlexGrow(0);

        filter.setPlaceholder("FILTER");
        filter.setValueChangeMode(ValueChangeMode.EAGER);
        filter.addValueChangeListener(e -> listItem(e.getValue()));

        itemGrid.asSingleSelect().addValueChangeListener(e -> itemEditor.editItem(e.getValue()));

        addNewBtn.addClickListener(e -> itemEditor.editItem(itemService.getMock()));

        itemEditor.setEditorHolder(() -> {
            itemEditor.setVisible(false);
            listItem(filter.getValue());
        });

        itemEditor.setUpdateHolder(() -> {
            label.setText(itemService.countPrice() + " RUB");
        });
    }

    private void listItem(String value) {
        if (StringUtils.isEmpty(value)) itemGrid.setItems(itemService.findAll());
        else itemGrid.setItems(itemService.findByFilter(value));
    }

}
