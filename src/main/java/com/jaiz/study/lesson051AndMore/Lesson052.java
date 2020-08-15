package com.jaiz.study.lesson051AndMore;

import com.jaiz.study.CategoryType;
import com.jaiz.study.Startable;
import com.jaiz.study.StartableMeta;
import com.jaiz.study.beans.Student;
import com.jaiz.study.utils.AppUtils;

import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.beans.property.ReadOnlyDoubleWrapper;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@StartableMeta(title = "lesson052", category = CategoryType.LESSON, subtitle = "绑定_1", digest = {""})
public class Lesson052 extends Startable {
    @Override
    public void start(Stage primaryStage) throws Exception {


        SimpleIntegerProperty sip = new SimpleIntegerProperty(5);
        //看源码可知getValue和get是等价的
        //getValue继承于IntegerExpression
        //get继承于IntegerPropertyBase
        //sip.get();

        //添加监听
        sip.addListener((ob, o, n) -> {
            log.info("oldValue = {}, newValue = {}", o, n);
        });

        //SimpleXXProperty的继承类，可以返回一个只读属性
        ReadOnlyDoubleWrapper rodw = new ReadOnlyDoubleWrapper(3.14);
        //只读属性没有set方法
        ReadOnlyDoubleProperty rodp = rodw.getReadOnlyProperty();
        log.info("只读属性={}", rodp.get());
        rodw.set(0.618);
        //读写属性变更后，只读属性也会发生变化
        log.info("读写属性变更后，只读属性={}", rodp.get());


        GridPane root = new GridPane();
        TextField nameTextField = new TextField();
        TextField ageTextField = new TextField();
        root.add(nameTextField, 0, 0);
        root.add(ageTextField, 0, 1);
        Button action = new Button("action");
        GridPane.setRowSpan(action, 2);
        root.add(action, 1, 0);
        Label nameLabel = new Label("name");
        Label nameLabel2 = new Label("name2");
        Label ageLabel = new Label("age");
        root.add(nameLabel, 2, 0);
        root.add(ageLabel, 2, 1);
        root.add(nameLabel2, 3, 0);


        BindingData data = new BindingData("name", 1);
        nameTextField.textProperty().bindBidirectional(data.getNameProp());
        nameLabel.textProperty().bind(data.getNameProp());
        ageLabel.textProperty().bindBidirectional(data.getAgeProp(), new StringConverter<Number>() {
            @Override
            public String toString(Number number) {
                return number.toString();
            }

            @Override
            public Number fromString(String s) {
                return Integer.valueOf(s);
            }
        });
        action.setOnAction(actionEvent -> {
            data.setName(nameTextField.getText());
            data.setAge(Integer.parseInt(ageTextField.getText()));
        });
        nameLabel2.textProperty().bind(nameTextField.textProperty());


        AppUtils.quickInit(primaryStage, "lesson052", root);
        primaryStage.show();

    }

}

class BindingData {
    private SimpleIntegerProperty age = new SimpleIntegerProperty();
    private SimpleStringProperty name = new SimpleStringProperty();

    public BindingData(String name, int age) {
        this.name.set(name);
        this.age.set(age);
    }

    public int getAge() {
        return age.get();
    }

    public void setAge(int age) {
        this.age.set(age);
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public SimpleIntegerProperty getAgeProp() {
        return this.age;
    }

    public SimpleStringProperty getNameProp() {
        return this.name;
    }
}
