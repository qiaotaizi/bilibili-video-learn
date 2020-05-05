package com.jaiz.study.lesson040;

import com.jaiz.study.beans.Student;
import com.jaiz.study.utils.AppUtils;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

/**
 * setCellFactory+ListCell动态修改数据
 * 性能上较37课的方式更加优秀
 * 而且ListCell、支持自定义选项样式
 *
 * 注意ChoiceBox是不支持cellFactory的
 */
public class App extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception {

        AnchorPane root = new AnchorPane();

        ComboBox<Student> comboBox=new ComboBox<>();
        ObservableList<Student> data=initData();
        HBox hBox=new HBox();
        TextField tf=new TextField();
        Button button=new Button("修改");

        hBox.getChildren().addAll(comboBox,tf,button);

        comboBox.setItems(data);

        //选中项展示设置
        ListCell<Student> buttonCell=new StudentListCell();

        comboBox.setButtonCell(buttonCell);

        //下拉项展示设置
        comboBox.setCellFactory(param -> new StudentListCell());

        root.getChildren().add(hBox);

        comboBox.setOnAction(event -> {
            SingleSelectionModel<Student> selectionModel=comboBox.getSelectionModel();
            Student selected=selectionModel.getSelectedItem();
            if (Objects.nonNull(selected)){
                tf.setText(selected.getName());
            }
        });

        button.setOnAction(event -> {
            SingleSelectionModel<Student> selectionModel=comboBox.getSelectionModel();
            Student selected=selectionModel.getSelectedItem();
            int selectedIndex=selectionModel.getSelectedIndex();
            if (StringUtils.isNotBlank(tf.getText()) && Objects.nonNull(selected)){
                selected.setName(tf.getText());
                selectionModel.clearSelection();
                selectionModel.select(selectedIndex);
            }
        });


        AppUtils.quickInitMenuBar(primaryStage,  this.getClass(), root);
    }

    /**
     * 数据初始化
     * @return
     */
    private ObservableList<Student> initData() {
        ObservableList<Student> result= FXCollections.observableArrayList(
                Student.builder().id(1).name("小明").age(18).build(),
                Student.builder().id(2).name("小强").age(19).build(),
                Student.builder().id(3).name("小华").age(17).build()
        );
        return result;
    }
}
