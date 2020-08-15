package com.jaiz.study.lesson027;

import com.jaiz.study.CategoryType;
import com.jaiz.study.Startable;
import com.jaiz.study.StartableMeta;
import com.jaiz.study.utils.AppUtils;

import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;

/**
 * 菜单栏续讲
 * <p>
 * SeparatorMenuItem(菜单栏分隔符)
 * 子菜单
 * 单选菜单组
 * 复选菜单
 */
@Slf4j
@StartableMeta(title = "lesson027", category = CategoryType.LESSON,
        subtitle = "MenuBar_2", digest = {"SeparatorMenuItem"})
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

        Menu subMenu = new Menu("sub menu");
        MenuItem submi1 = new MenuItem("hello");
        MenuItem submi2 = new MenuItem("world");
        subMenu.getItems().addAll(submi1, submi2);


        ToggleGroup tg = new ToggleGroup();
        RadioMenuItem rmi1 = new RadioMenuItem("rmi1");
        RadioMenuItem rmi2 = new RadioMenuItem("rmi2");
        RadioMenuItem rmi3 = new RadioMenuItem("rmi3");
        rmi1.setToggleGroup(tg);
        rmi1.setSelected(true);
        rmi2.setToggleGroup(tg);
        rmi3.setToggleGroup(tg);

        menu3.getItems().addAll(rmi1, rmi2, rmi3, new SeparatorMenuItem());

        CheckMenuItem cmi = new CheckMenuItem("test");
        menu3.getItems().add(cmi);


        item1.setOnAction(actionEvent -> {
            log.info("item1 clicked");
        });
        item2.setOnAction(actionEvent -> {
            log.info("item2 clicked");
        });
        item5.setOnAction(actionEvent -> {
            log.info("item5 clicked");
        });

        SeparatorMenuItem separatorMenuItem = new SeparatorMenuItem();

        menu1.getItems().addAll(item1, item2, subMenu);
        menu2.getItems().addAll(item3, item4, separatorMenuItem, item5);
        menuBar.getMenus().addAll(menu1, menu2, menu3, menu4);

        root.getChildren().add(menuBar);

        AppUtils.quickInit(primaryStage, "lesson027", root);
        primaryStage.show();

    }
}
