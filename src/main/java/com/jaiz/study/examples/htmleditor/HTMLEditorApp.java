package com.jaiz.study.examples.htmleditor;

import com.jaiz.study.CategoryType;
import com.jaiz.study.Startable;
import com.jaiz.study.StartableMeta;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.web.HTMLEditor;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@StartableMeta(title = "HTMLEditor",category = CategoryType.EXAMPLE)
public class HTMLEditorApp extends Startable {
    @Override
    public void start(Stage primaryStage) throws Exception {

        AnchorPane root=new AnchorPane();

        HTMLEditor htmlEditor=new HTMLEditor();
        Button btn=new Button("获取文档");
        VBox vb=new VBox();
        vb.getChildren().addAll(htmlEditor,btn);

        btn.setOnAction(event -> {
            log.info("html content = {}",htmlEditor.getHtmlText());
        });

        root.getChildren().add(vb);
        Scene scene=new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.setTitle("HTMLEditorApp");
        primaryStage.show();
    }
}
