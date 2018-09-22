package com.toxin.vaadinpring.components;

import com.toxin.vaadinpring.entity.Item;

import com.toxin.vaadinpring.service.ItemService;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.KeyNotifier;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.converter.StringToIntegerConverter;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;


@UIScope
@SpringComponent
public class ItemEditor extends VerticalLayout implements KeyNotifier {

    private final ItemService itemService;

    private Item item;

    private TextField title = new TextField("TITLE");
    private TextField description = new TextField("DESCRIPTION");
    private TextField price = new TextField("PRICE");

    private Button save = new Button("SAVE", VaadinIcon.CHECK.create());
    private Button cancel = new Button("CANCEL", VaadinIcon.CLOSE.create());
    private Button delete = new Button("DELETE", VaadinIcon.TRASH.create());

    private HorizontalLayout actions = new HorizontalLayout(save, cancel, delete);

    private Binder<Item> binder = new Binder<>(Item.class);

    @Setter
    private EditorHolder editorHolder;

    @Setter
    private UpdateHolder updateHolder;

    @Autowired
    public ItemEditor(ItemService itemService) {
        this.itemService = itemService;

        add(title, description, price, actions);

        binder.forField(price)
            .withConverter(new StringToIntegerConverter("NOT INT"))
            .bind(Item::getPrice, Item::setPrice);

        binder.bindInstanceFields(this);

        setSpacing(true);

        save.getElement().getThemeList().add("primary");
        delete.getElement().getThemeList().add("error");

        addKeyPressListener(Key.ENTER, e -> save());

        save.addClickListener(e -> save());
        delete.addClickListener(e -> delete());
        cancel.addClickListener(e -> editItem(item));

        setVisible(false);
    }

    private void save() {
        itemService.save(item);
        editorHolder.onChange();
        updateHolder.onChange();
    }

    private void delete() {
        itemService.delete(item.getId());
        editorHolder.onChange();
        updateHolder.onChange();
    }

    public void editItem(Item i) {
        System.out.println(i);
        if (i == null) {
            setVisible(false);
            return;
        }

        boolean persisted = i.getId() != null;

        if (persisted) item = itemService.find(i.getId());
        else item = i;

        cancel.setVisible(persisted);
        binder.setBean(item);
        setVisible(true);
        title.focus();
        updateHolder.onChange();
    }

    @FunctionalInterface
    public interface EditorHolder {
        void onChange();
    }

    @FunctionalInterface
    public interface UpdateHolder {
        void onChange();
    }

}
