package com.example.vaadindemo.view;

import com.example.vaadindemo.component.CourseForm;
import com.example.vaadindemo.entity.Course;
import com.example.vaadindemo.repository.CourseRepository;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.List;

@Route
public class CoursesView extends AbstractView implements Reloader {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private CourseForm form;

    private Grid<Course> grid;

    @Override
    public void processRefresh() {
        grid.setItems(courseRepository.findAll());
    }

    @PostConstruct
    public void init() {
        initView();
        List<Course> list = courseRepository.findAll();
        if (list == null || list.isEmpty()) {
            for (int i = 0; i < 2; i++) {
                Course course = new Course();
                course.setName("Kuzus neve" + i);
                System.out.println(course.getName());
                courseRepository.save(course);
            }
        }
        grid = new Grid<>();
        grid.setItems(courseRepository.findAll());
        grid.addColumn(course -> course.getId()).setHeader("Azonosító");
        grid.addColumn(course -> course.getName()).setHeader("Név");
        grid.asSingleSelect().addValueChangeListener(selectionEvent -> {
            if (selectionEvent.getValue() != null) {
                form.setVisible(true);
                form.setCourse(selectionEvent.getValue());
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
            form.setCourse(new Course());
            form.setReloader(this);
            form.setInEdit(false);
        });
        add(new Text("Kurzusok"));
        add(addButton);
        add(grid);
        add(form);
    }


}
