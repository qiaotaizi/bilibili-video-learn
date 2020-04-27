package com.jaiz.study.lesson028;

import com.jaiz.study.AppUtils;
import javafx.application.Application;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

import java.awt.*;

/**
 * 菜单栏续讲2
 *
 * CustomMenuItem,MenuButton,SplitMenuButton,ContextMenu
 */
public class App extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        FlowPane root=new FlowPane();

        MenuBar menuBar=new MenuBar();
        menuBar.setUseSystemMenuBar(true);

        Menu menu1=new Menu("menu1");
        Menu menu2=new Menu("menu2");
        Menu menu3=new Menu("menu3");
        Menu menu4=new Menu("menu4");

        MenuItem item1=new MenuItem("item1");
        MenuItem item2=new MenuItem("item2");
        MenuItem item3=new MenuItem("item3");
        MenuItem item4=new MenuItem("item4");
        MenuItem item5=new MenuItem("item5");



        menu1.getItems().addAll(item1,item2);
        menu2.getItems().addAll(item3,item4,item5);

        //注意使用自定义菜单项的时候，对于Mac将无法使用系统菜单。
        CustomMenuItem cmi=new CustomMenuItem();
        Button b=new Button("自定义菜单项");
        cmi.setContent(b);

        //menu3.getItems().add(cmi);

        //下拉菜单控件
        MenuButton mb=new MenuButton("menu button");

        MenuItem item6=new MenuItem("item6");
        MenuItem item7=new MenuItem("item7");
        MenuItem item8=new MenuItem("item8");

        //setOnMenuValidation 使用快捷键触发菜单项时会触发该时间的监听
        item6.setOnMenuValidation(event -> {
            System.out.println("快捷键触发item6");
        });
        mb.getItems().addAll(item6,item7,item8);

        //SplitMenuButton与MenuButton的区别在于：
        //MenuButton点击时一定出现下拉列表框
        //SplitMenuButton点击按钮时不出现下拉列表框，点击箭头时才出现下拉列表框
        MenuItem item9=new MenuItem("item9");
        MenuItem item10=new MenuItem("item10");
        SplitMenuButton smb=new SplitMenuButton(item9,item10);
        smb.setText("SplitMenuButton");

        //ContextMenu 右键菜单栏
        MenuItem item11=new MenuItem("item11");
        MenuItem item12=new MenuItem("item12");
        ContextMenu cm=new ContextMenu(item11,item12);
        Label label=new Label("右键点我出菜单");
        label.setContextMenu(cm);
        label.setOnContextMenuRequested(contextMenuEvent -> {
            System.out.println("呼出右键菜单事件");
        });

        menuBar.getMenus().addAll(menu1,menu2,menu3,menu4);


        root.getChildren().addAll(menuBar,mb,smb,label);



        AppUtils.quickInit(primaryStage,"lesson028",root);
        primaryStage.show();
    }
}
