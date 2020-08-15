package com.jaiz.study.lesson040;

import java.util.Objects;

import com.jaiz.study.CategoryType;
import com.jaiz.study.Startable;
import com.jaiz.study.StartableMeta;
import com.jaiz.study.beans.Student;
import com.jaiz.study.utils.AppUtils;
import com.jaiz.study.utils.Repo;

import org.apache.commons.lang3.StringUtils;

import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * setCellFactory+ListCell动态修改数据
 * 性能上较37课的方式更加优秀
 * 而且ListCell、支持自定义选项样式
 * <p>
 * 注意ChoiceBox是不支持cellFactory的
 */
@StartableMeta(title = "lesson040", category = CategoryType.LESSON,
        subtitle = "CellFactory与ListCell")
public class App extends Startable {


    @Override
    public void start(Stage primaryStage) throws Exception {

        AnchorPane root = new AnchorPane();

        ComboBox<Student> comboBox = new ComboBox<>();
        ObservableList<Student> data = Repo.STUDENTS;
        HBox hBox = new HBox();
        TextField tf = new TextField();
        Button button = new Button("修改");

        hBox.getChildren().addAll(comboBox, tf, button);

        comboBox.setItems(data);

        //选中项展示设置
        ListCell<Student> buttonCell = new StudentListCell();

        comboBox.setButtonCell(buttonCell);

        //下拉项展示设置
        comboBox.setCellFactory(param -> new StudentListCell());

        root.getChildren().add(hBox);

        comboBox.setOnAction(event -> {
            SingleSelectionModel<Student> selectionModel = comboBox.getSelectionModel();
            Student selected = selectionModel.getSelectedItem();
            if (Objects.nonNull(selected)) {
                tf.setText(selected.getName());
            }
        });

        button.setOnAction(event -> {
            SingleSelectionModel<Student> selectionModel = comboBox.getSelectionModel();
            Student selected = selectionModel.getSelectedItem();
            int selectedIndex = selectionModel.getSelectedIndex();
            if (StringUtils.isNotBlank(tf.getText()) && Objects.nonNull(selected)) {
                selected.setName(tf.getText());
                selectionModel.clearSelection();
                selectionModel.select(selectedIndex);
            }
        });


        AppUtils.quickInit(primaryStage, "lesson040", root);
        primaryStage.show();
    }
}
