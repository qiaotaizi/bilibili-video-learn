package com.jaiz.study;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AppUtils {

    /**
     * 快速设置stage的名称，尺寸，场景
     * @param stage
     * @param title
     * @param root
     */
    public static void quickInit(Stage stage, String title, Parent root){
        stage.setTitle(title);
        stage.setHeight(400);
        stage.setWidth(600);
        stage.setScene(new Scene(root));
    }

}
