package com.jaiz.study.utils;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.Objects;

public class AppUtils {

    private static Stage aboutStage;

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
     * @param appClass
     * @param root
     */
    public static void quickInitMenuBar(Stage stage, Class<? extends Application> appClass, Pane root){

        String title=titleByAppClassPackageName(appClass);

        MenuBar menuBar=new MenuBar();
        menuBar.setUseSystemMenuBar(true);
        Menu file=new Menu("File");
        MenuItem close=new MenuItem("quit "+title);
        close.setAccelerator(KeyCombination.valueOf("Shortcut+q"));
        file.getItems().add(close);
        Menu help=new Menu("Help");
        MenuItem about=new MenuItem("about "+title);
        about.setAccelerator(KeyCombination.valueOf("Shortcut+,"));
        help.getItems().add(about);
        menuBar.getMenus().addAll(file,help);

        root.getChildren().add(menuBar);

        close.setOnAction(actionEvent -> {
            stage.close();
            System.out.println("cmd+q 退出stage");
        });

        initAboutStage(stage,appClass,title);

        about.setOnAction(actionEvent->{
            if (aboutStage.isShowing()){
                return;
            }
            aboutStage.show();
        });

        quickInit(stage,title,root);
        stage.show();
    }

    /**
     * 初始化aboutStage
     * @param stage
     * @param appClass
     */
    private static void initAboutStage(Stage stage, Class<? extends Application> appClass,String title) {
        aboutStage=new Stage();
        aboutStage.setTitle("小节说明");

        AnchorPane root=new AnchorPane();

        Text aboutTitle=new Text("关于"+title);
        aboutTitle.setFont(Font.font(20));

        TextArea aboutTA=new TextArea();
        aboutTA.setEditable(false);
        aboutTA.setText(desc(appClass));

        VBox vb=new VBox();
        vb.getChildren().addAll(aboutTitle,aboutTA);

        root.getChildren().addAll(vb);


        Scene scene=new Scene(root);
        aboutStage.setScene(scene);
        aboutStage.initModality(Modality.WINDOW_MODAL);
        aboutStage.initStyle(StageStyle.UTILITY);
        aboutStage.initOwner(stage);
    }

    /**
     * 从AppDesc注解抽取说明
     * @param appClass
     * @return
     */
    private static String desc(Class<? extends Application> appClass) {
        String defaultResult="暂无说明";
        AppDesc desc=appClass.getAnnotation(AppDesc.class);
        if (Objects.isNull(desc)){
            return defaultResult;
        }
        String[] descValue=desc.value();
        if (descValue.length==1 && Objects.equals("",descValue[0])){
            return defaultResult;
        }
        StringBuilder result=new StringBuilder();
        for (String s : descValue) {
            result.append(s).append(System.lineSeparator());
        }
        return result.toString();
    }

    /**
     * 根据App类的报名确定窗口Title
     * 暂时取package的最后一部分
     * @param appClass
     * @return
     */
    private static String titleByAppClassPackageName(Class<? extends Application> appClass) {

        String[] arr=appClass.getPackageName().split("\\.");
        return arr[arr.length-1];

    }
}
