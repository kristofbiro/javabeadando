package com.example.vaadindemo.view;
import com.example.vaadindemo.component.AppMenuBar;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route
public class RoomView extends AbstractView {

    public RoomView(){
        initView();
        add(new Text("Ez egy terem képernyő"));
    }
}
