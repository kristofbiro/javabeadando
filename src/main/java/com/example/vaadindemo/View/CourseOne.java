package com.example.vaadindemo.View;
import com.example.vaadindemo.Component.AppMenuBar;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route
public class CourseOne extends AbstractView{


    public CourseOne() {
        initView();
        add(new Text("Egy kurzus"));

    }

}


