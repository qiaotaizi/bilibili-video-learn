package com.jaiz.study.lesson025;

import com.jaiz.study.AppUtils;
import javafx.application.Application;
import javafx.application.HostServices;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        FlowPane root=new FlowPane();

        Hyperlink hyperlink=new Hyperlink("http://www.baidu.com");
        hyperlink.setFocusTraversable(false);
        hyperlink.setOnAction(event -> {
            HostServices hs=this.getHostServices();
            hs.showDocument(hyperlink.getText());
        });

        //超链接前带一个控件
        Hyperlink hyperlink1=new Hyperlink("http://www.baidu.com",new Button("click me!"));
        hyperlink1.setFocusTraversable(false);
        hyperlink1.setOnAction(event -> {
            HostServices hs=this.getHostServices();
            hs.showDocument(hyperlink1.getText());
        });

        root.getChildren().addAll(hyperlink,hyperlink1);

        AppUtils.quickInit(primaryStage,"lesson025",root);
        primaryStage.show();
    }
}
