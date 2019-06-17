package com.example.vaadindemo.view;

import com.example.vaadindemo.component.RoomForm;
import com.example.vaadindemo.entity.Room;
import com.example.vaadindemo.repository.RoomRepository;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.List;

@Route
public class RoomsView extends AbstractView implements Reloader{

    @Autowired
    RoomRepository roomRepository;

    @Autowired
    RoomForm form;

    private Grid<Room> grid;

    @Override
    public void processRefresh() {
        grid.setItems(roomRepository.findAll());
    }

    @PostConstruct
    public void init() {
        initView();
        List<Room> list = roomRepository.findAll();
        if (list == null || list.isEmpty()) {
            for (int i = 0; i < 2; i++) {
                Room room = new Room();
                room.setName("Terem neve" + i);
                room.setLevel(i+1);
                System.out.println(room.getName() + room.getLevel());
                roomRepository.save(room);
            }
        }
        grid = new Grid<>();
        grid.setItems(list);
        grid.addColumn(room -> room.getId()).setHeader("Id");
        grid.addColumn(room -> room.getName()).setHeader("Name");
        grid.addColumn(room -> room.getLevel()).setHeader("Level");

        grid.asSingleSelect().addValueChangeListener(selectionEvent -> {
            if (selectionEvent.getValue() != null) {
                form.setVisible(true);
                form.setRoom(selectionEvent.getValue());
                form.setReloader(this);
                form.setInEdit(true);
            } else {
                form.setVisible(false);
            }
        });

        Button addButton=new Button("New");
        addButton.setIcon(VaadinIcon.PLUS.create());
        addButton.addClickListener(buttonClickEvent -> {
            form.setVisible(true);
            form.setRoom(new Room());
            form.setReloader(this);
            form.setInEdit(false);
        });

        add(new Text("Termek"));
        add(addButton);
        add(grid);
        add(form);
    }
}
