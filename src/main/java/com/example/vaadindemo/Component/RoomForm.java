package com.example.vaadindemo.component;

import com.example.vaadindemo.entity.Room;
import com.example.vaadindemo.repository.RoomRepository;
import com.example.vaadindemo.view.Reloader;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

@SpringComponent
@UIScope
public class RoomForm extends VerticalLayout {

    private Room room;

    private boolean inEdit;
    private Binder<Room> binder;
    private TextField name;
    private NumberField level;
    private Reloader reloader;

    @Autowired
    RoomRepository repository;

    @PostConstruct
    private void init() {
        binder = new Binder<>(Room.class);
        name = new TextField("Name");
        level = new NumberField("Level");

        Button button = new Button();
        button.setText("Delete");
        button.setIcon(VaadinIcon.TRASH.create());
        button.addClickListener(buttonClickEvent ->
        {
            setVisible(false);
            repository.delete(room);
            reloader.processRefresh();
        });

        Button editButton = new Button();
        editButton.setText("Edit");
        editButton.setIcon(VaadinIcon.PENCIL.create());
        editButton.addClickListener(buttonClickEvent ->
        {
            if (inEdit) {
                repository.update(room);
            } else {
                repository.save(room);
            }
            reloader.processRefresh();
            setInEdit(false);
            setVisible(false);
        });
        add(name);
        add(level);
        add(button);
        add(editButton);
        setVisible(false);
        binder.bindInstanceFields(this);
    }

    public Room getRoom() {return room;}
    public void setRoom(Room room){
        if(room != null){
            binder.setBean(room);
        }
        this.room = room;
    }

    public Reloader getReloader() {
        return reloader;
    }

    public void setReloader(Reloader reloader) {
        this.reloader = reloader;
    }

    public boolean isInEdit() {
        return inEdit;
    }

    public void setInEdit(boolean inEdit) {
        this.inEdit = inEdit;
    }
}
