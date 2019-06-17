package com.example.vaadindemo.view;

import com.example.vaadindemo.component.AppMenuBar;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route
public class MainView extends AbstractView {

    public MainView() {
        initView();

        Button btn = new Button();
        btn.setText("Kattants rám!");
        btn.addClickListener(buttonClickEvent -> Notification.show("Almafa"));
        add(btn);

        Button btn2 = new Button();
        btn2.setText("Almafa");
        btn2.addClickListener(buttonClickEvent -> Notification.show("Körtefa"));
        add(btn2);

        Text text=new Text("Helló bilág");
        add(text);

    }
}
