package com.jaiz.study;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.Pane;
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


    /**
     * 快速设置stage的一些基本特性
     * 默认调用stage.show()打开窗口
     * 支持command+q快速退出应用
     * @param stage
     * @param title
     * @param root
     */
    public static void quickInitMenuBar(Stage stage, String title, Pane root){

        MenuBar menuBar=new MenuBar();
        menuBar.setUseSystemMenuBar(true);
        Menu menu=new Menu("File");
        MenuItem close=new MenuItem("quit "+title);
        close.setAccelerator(KeyCombination.valueOf("Shortcut+q"));
        menu.getItems().add(close);
        menuBar.getMenus().add(menu);
        root.getChildren().add(menuBar);

        close.setOnAction(actionEvent -> {
            stage.close();
        });

        quickInit(stage,title,root);
        stage.show();
    }

}
