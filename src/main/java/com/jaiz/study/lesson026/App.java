package com.jaiz.study.lesson026;

import com.jaiz.study.CategoryType;
import com.jaiz.study.Startable;
import com.jaiz.study.StartableMeta;
import com.jaiz.study.utils.AppUtils;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;

/**
 * MenuBar 菜单栏
 * Menu 菜单
 * MenuItem 菜单项
 * <p>
 * 菜单图标
 * 快捷键
 */
@Slf4j
@StartableMeta(title = "lesson026", category = CategoryType.LESSON,
        subtitle = "MenuBar", digest = {"MenuBar", "Menu", "MenuItem"})
public class App extends Startable {
    @Override
    public void start(Stage primaryStage) throws Exception {

        AnchorPane root = new AnchorPane();

        MenuBar menuBar = new MenuBar();
        menuBar.setUseSystemMenuBar(true);

        Menu menu1 = new Menu("menu1");
        Menu menu2 = new Menu("menu2");
        Menu menu3 = new Menu("menu3");
        Menu menu4 = new Menu("menu4");

        MenuItem item1 = new MenuItem("item1");
        MenuItem item2 = new MenuItem("item2");
        MenuItem item3 = new MenuItem("item3");
        MenuItem item4 = new MenuItem("item4");
        MenuItem item5 = new MenuItem("item5");
        item5.acceleratorProperty().setValue(KeyCombination.valueOf("Shortcut+s"));


        ImageView imageView = new ImageView("/com/jaiz/study/lesson026/icon.png");
        MenuItem item6 = new MenuItem("item6", imageView);


        item1.setOnAction(actionEvent -> {
            log.info("item1 clicked");
        });
        item2.setOnAction(actionEvent -> {
            log.info("item2 clicked");
        });
        item5.setOnAction(actionEvent -> {
            log.info("item5 clicked");
        });

        menu1.getItems().addAll(item1, item2);
        menu2.getItems().addAll(item3, item4, item5);
        menu3.getItems().add(item6);
        menuBar.getMenus().addAll(menu1, menu2, menu3, menu4);

        root.getChildren().add(menuBar);

        AppUtils.quickInit(primaryStage, "lesson026", root);
        primaryStage.show();

    }
}
