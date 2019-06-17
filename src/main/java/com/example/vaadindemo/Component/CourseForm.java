package com.example.vaadindemo.component;

import com.example.vaadindemo.entity.Course;
import com.example.vaadindemo.repository.CourseRepository;
import com.example.vaadindemo.view.Reloader;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

@SpringComponent
@UIScope
public class CourseForm extends VerticalLayout {

    private Course course;

    private boolean inEdit;
    private Binder<Course> binder;
    private TextField name;

    private Reloader reloader;

    @Autowired
    private CourseRepository repository;

    @PostConstruct
    private void init() {
        binder = new Binder<>(Course.class);
        name = new TextField("Name");
        Button button = new Button();
        button.setText("Delete");
        button.setIcon(VaadinIcon.TRASH.create());
        button.addClickListener(buttonClickEvent ->
        {
            setVisible(false);
            repository.delete(course);
            reloader.processRefresh();
        });

        Button editButton = new Button();
        editButton.setText("Edit");
        editButton.setIcon(VaadinIcon.PENCIL.create());
        editButton.addClickListener(buttonClickEvent ->
        {
            if (inEdit) {
                repository.update(course);
            } else {
                repository.save(course);
            }
            reloader.processRefresh();
            setInEdit(false);
            setVisible(false);
        });

        add(name);
        add(button);
        add(editButton);
        setVisible(false);
        binder.bindInstanceFields(this);
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        if (course != null) {
            binder.setBean(course);
        }
        this.course = course;
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
