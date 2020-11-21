package com.jaiz.study.lesson051AndMore;

import com.jaiz.study.CategoryType;
import com.jaiz.study.Startable;
import com.jaiz.study.StartableMeta;
import com.jaiz.study.utils.AppUtils;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.PopupWindow;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

@Slf4j
@StartableMeta(title = "lesson066",
        category = CategoryType.LESSON,
        subtitle = "ToolTip",
        digest = {""})
public class Lesson066 extends Startable {
    @Override
    public void start(Stage primaryStage) throws Exception {
        GridPane root=new GridPane();
        root.setAlignment(Pos.CENTER);

        Button btn=new Button("show tooltip anchor location");
        Tooltip tt=new Tooltip();
        btn.setTooltip(tt);
        root.add(btn,0,0);
        btn.setOnAction(event -> {
            var target=(Button)event.getTarget();
            var location=target.getTooltip().getAnchorLocation();
            log.info("AnchorLocation = {}",location.name());
        });

        //自定义tooltip中的显示内容
        tt.setStyle("-fx-background-color:white;");
        VBox popup=new VBox();
        Rectangle r1=new Rectangle(30,10);
        r1.setFill(Color.PINK);
        Rectangle r2=new Rectangle(30,10);
        r2.setFill(Color.BLUEVIOLET);
        popup.getChildren().addAll(r1,r2);
        tt.setGraphic(popup);

        ChoiceBox<PopupWindow.AnchorLocation> choiceBox=new ChoiceBox<>();
        root.add(choiceBox,1,0);
        ObservableList<PopupWindow.AnchorLocation> items= FXCollections.observableArrayList();
        items.addAll(Arrays.asList(PopupWindow.AnchorLocation.values()));
        choiceBox.setItems(items);
        choiceBox.setConverter(new StringConverter<PopupWindow.AnchorLocation>() {
            @Override
            public String toString(PopupWindow.AnchorLocation object) {
                return object.name();
            }

            @Override
            public PopupWindow.AnchorLocation fromString(String string) {
                return null;
            }
        });
        var selectionModel = choiceBox.getSelectionModel();
        selectionModel.selectFirst();
        tt.setAnchorLocation(selectionModel.getSelectedItem());
        tt.anchorLocationProperty().bind(choiceBox.valueProperty());
        //自定义AnchorLocation的意义不大

        //tooltip的安装与卸载
        CheckBox cb=new CheckBox("显示tooltip");
        cb.setSelected(true);
        root.add(cb,2,0);
        cb.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue){
                Tooltip.install(btn,tt);
            }else {
                Tooltip.uninstall(btn,tt);
            }
        });

        tt.setOnShown(event -> {
            log.info("tt显示");
        });
        tt.setOnHidden(event -> {
            log.info("隐藏");
        });


        AppUtils.quickInit(primaryStage,"lesson066",root);
        primaryStage.show();
    }
}
