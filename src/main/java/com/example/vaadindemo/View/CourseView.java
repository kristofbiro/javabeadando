package com.example.vaadindemo.view;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.router.Route;

@Route
public class CourseView extends AbstractView {

    public CourseView()
    {
        initView();
        add(new Text("Kurzus"));
    }

}
