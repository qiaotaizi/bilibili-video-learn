package com.jaiz.study.examples.sqlbeautify;

import com.alibaba.druid.sql.SQLUtils;
import com.jaiz.study.utils.WarnHandler;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SqlBeautify extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {

        Thread.setDefaultUncaughtExceptionHandler(WarnHandler.getInstance());
        WarnHandler.getInstance().initWarnHandlerLater(primaryStage);

        AnchorPane anchorPane=new AnchorPane();
        VBox vBox=new VBox();


        TextArea ta=new TextArea();
        Button btn= new Button("格式化");
        VBox.setMargin(btn,new Insets(20,0,0,0));

        btn.setOnAction(event -> {
            String text=ta.getText();
            //注意，当sql格式错误时，这里只是打印异常，并没有真正抛出
            String beautified=SQLUtils.formatMySql(text);
            ta.setText(beautified);
        });

        vBox.getChildren().addAll(ta,btn);
        vBox.setAlignment(Pos.CENTER);

        anchorPane.getChildren().add(vBox);

        Scene scene=new Scene(anchorPane);

        primaryStage.setTitle("SqlBeautify");
        primaryStage.setHeight(400);
        primaryStage.setWidth(600);
        primaryStage.setScene(scene);

        ta.setPrefWidth(primaryStage.getWidth());

        primaryStage.show();
    }
}
