package com.jaiz.study.lesson051AndMore;

import java.beans.PropertyChangeSupport;

import com.jaiz.study.CategoryType;
import com.jaiz.study.Startable;
import com.jaiz.study.StartableMeta;
import com.jaiz.study.utils.AppUtils;

import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@StartableMeta(title = "lesson051",category = CategoryType.LESSON,
subtitle = "绑定",digest = {"PropertyChangeSupport"})
public class Lesson051 extends Startable {

    @Override
    public void start(Stage primaryStage) throws Exception {
        
        BindingStudent stu=new BindingStudent();
        stu.setAge(18);
        stu.setName("小强");
        Button btn=new Button("button");

        AnchorPane root=new AnchorPane();
        root.getChildren().add(btn);
        AppUtils.quickInit(primaryStage, "lesson051", root);
        primaryStage.show();

        btn.setOnAction(event->{
            stu.setAge(20);
            stu.setName("小明");
        });

        //当属性发生变化时触发的逻辑
        //如果新值和旧值相等则不会触发
        stu.pcs.addPropertyChangeListener(event->{
            log.info("all oldValue = {}",event.getOldValue());
            log.info("all newValue = {}", event.getNewValue());
        });

        //按照firePropertyChange标签绑定监听
        stu.pcs.addPropertyChangeListener("name",event->{
            log.info("name oldValue = {}", event.getOldValue());
            log.info("name newValue = {}", event.getNewValue());
        });

        stu.pcs.addPropertyChangeListener("age",event->{
            log.info("age oldValue = {}", event.getOldValue());
            log.info("age newValue = {}", event.getNewValue());
        });

    }
    
}

class BindingStudent{
    private String name;
    private int age;
    //通知支持对象
    public PropertyChangeSupport pcs=new PropertyChangeSupport(this);

    public String getName() {
        return name;
    }

    public void setName(String name) {
        var oldValue=this.name;
        this.name = name;
        //触发属性变更
        this.pcs.firePropertyChange("name", oldValue, this.name);

    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        var oldValue=this.age;
        this.age = age;
        //触发属性变更
        this.pcs.firePropertyChange("age", oldValue, this.age);
    }
    
}