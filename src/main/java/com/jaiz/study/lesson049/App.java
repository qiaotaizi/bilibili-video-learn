package com.jaiz.study.lesson049;

import com.jaiz.study.utils.AppDesc;
import com.jaiz.study.utils.AppUtils;
import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

@AppDesc({"ScrollBar，ScrollPane，Separator"})
public class App extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception {

        AnchorPane root = new AnchorPane();



        ScrollPane scrollPane=new ScrollPane();

        VBox vb=new VBox();

        for (int i=0;i<30;i++){
            Button btn=new Button("这是button。。。。。"+i);
            vb.getChildren().add(btn);
        }

        scrollPane.setContent(vb);

        scrollPane.setMaxHeight(300);
        scrollPane.setMaxWidth(200);

        root.getChildren().add(scrollPane);

        ScrollBar scrollBar=new ScrollBar();
        scrollBar.setLayoutX(300);
        root.getChildren().add(scrollBar);


        HBox hBox=new HBox();

        Button b1=new Button("b1");
        Button b2=new Button("b2");
        Separator sep=new Separator(Orientation.VERTICAL);
        Button b3=new Button("b3");
        hBox.getChildren().addAll(b1,b2,sep,b3);
        root.getChildren().add(hBox);

        hBox.setLayoutX(300);
        hBox.setLayoutY(200);


        AppUtils.quickInitMenuBar(primaryStage,  this.getClass(), root);
    }

}
