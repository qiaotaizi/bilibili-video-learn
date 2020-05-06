package com.jaiz.study.lesson048;

import com.jaiz.study.beans.Student;
import com.jaiz.study.utils.AppDesc;
import com.jaiz.study.utils.AppUtils;
import com.jaiz.study.utils.Repo;
import com.jaiz.study.utils.SimplifiedStringConverter;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.ScheduledService;
import javafx.concurrent.Task;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Objects;

@AppDesc({"Spinner"})
public class App extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception {

        AnchorPane root = new AnchorPane();

        VBox vBox=new VBox();

        Spinner<Integer> spinner =new Spinner<>(0,10,0,1);
        spinner.setEditable(false);
        spinner.getStyleClass().add(Spinner.STYLE_CLASS_SPLIT_ARROWS_HORIZONTAL);//有一些可选的样式
        vBox.getChildren().add(spinner);

        ObservableList<Student> data= Repo.STUDENTS;

        //如果spinner中绑定对象的话，推荐使用这种初始化方式。
        SpinnerValueFactory.ListSpinnerValueFactory<Student> valueFactory=new SpinnerValueFactory.ListSpinnerValueFactory<>(data);
        valueFactory.setConverter(new SimplifiedStringConverter<>() {
            @Override
            public String toString(Student object) {
                return object.getName();
            }
        });
        Spinner<Student> spinner1=new Spinner<>(valueFactory);
        spinner1.setEditable(false);
        spinner1.getStyleClass().add(Spinner.STYLE_CLASS_SPLIT_ARROWS_HORIZONTAL);

        vBox.getChildren().add(spinner1);

        root.getChildren().add(vBox);

        AppUtils.quickInitMenuBar(primaryStage,  this.getClass(), root);
    }

}
