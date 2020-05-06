package com.jaiz.study.examples.htmleditor;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.web.HTMLEditor;
import javafx.stage.Stage;


public class HTMLEditorApp extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {

        AnchorPane root=new AnchorPane();

        HTMLEditor htmlEditor=new HTMLEditor();
        Button btn=new Button("获取文档");
        VBox vb=new VBox();
        vb.getChildren().addAll(htmlEditor,btn);

        btn.setOnAction(event -> {
            System.out.println(htmlEditor.getHtmlText());
        });

        root.getChildren().add(vb);
        Scene scene=new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.setTitle("HTMLEditorApp");
        primaryStage.show();
    }
}
