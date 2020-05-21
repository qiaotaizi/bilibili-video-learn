package com.jaiz.study;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.util.Objects;

import com.jaiz.study.beans.Example;

import org.apache.commons.lang3.StringUtils;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Index extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        VBox root = new VBox();

        ObservableList<Example> examples = FXCollections.observableArrayList();
        ClassLoader loader = this.getClass().getClassLoader();
        sweepStartable(this.getClass().getPackageName(), examples, loader);

        var labels = root.getChildren();
        examples.forEach(example -> {

            String text = example.getTitle();

            HBox hBox = new HBox();
            Label eLabel = new Label();

            String subtitle = example.getSubtitle();
            if (StringUtils.isNotBlank(subtitle)) {
                text += "-" + subtitle;
            }
            eLabel.setText(text);
            Button b = new Button("查看");
            b.setOnAction(event -> {
                Class<? extends Startable> appClass = example.getAppClazz();
                Startable s;
                try {
                    s = appClass.getDeclaredConstructor().newInstance();
                } catch (InstantiationException | IllegalAccessException | IllegalArgumentException
                        | InvocationTargetException | NoSuchMethodException | SecurityException e1) {
                    e1.printStackTrace();
                    return;
                }
                Stage subStage = new Stage();
                subStage.initModality(Modality.WINDOW_MODAL);
                subStage.initOwner(primaryStage);
                try {
                    s.start(subStage);
                } catch (Exception e) {
                    e.printStackTrace();
                    return;
                }
            });
            hBox.getChildren().addAll(eLabel,b);
            labels.add(hBox);
        });

        Scene scene = new Scene(root);

        primaryStage.setX(30);
        primaryStage.setY(50);
        primaryStage.setWidth(300);
        primaryStage.setHeight(700);
        primaryStage.setResizable(false);
        primaryStage.setTitle("javafx学习宝典");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * 扫描指定包下的所有Startable实现类
     * 
     * @param packageName
     * @return
     */
    private void sweepStartable(String packageName, ObservableList<Example> examples, ClassLoader loader) {
        URL url = this.getClass().getClassLoader().getResource(packageName.replace(".", "/"));
        URI uri;
        try {
            uri = url.toURI();
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return;
        }
        File f = new File(uri);
        f.listFiles(file -> {
            // 递归查找
            if (file.isDirectory()) {
                sweepStartable(packageName + "." + file.getName(), examples, loader);
                return false;
            }
            // 过滤非.class文件
            if (!file.getName().endsWith(".class")) {
                return false;
            }
            String className = packageName + "." + file.getName().replace(".class", "");
            Class<?> clazz;
            try {
                clazz = loader.loadClass(className);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                return false;
            }
            //过滤非Startable子类
            if(!Startable.class.isAssignableFrom(clazz)){
                return false;
            }
            //过滤Startable本身
            if(Objects.equals(Startable.class, clazz)){
                return false;
            }
            Class<Startable> clazzStartable=(Class<Startable>)clazz;
            //封装Example对象
            Example e=Example.fromClass(clazzStartable);
            examples.add(e);
            return false;
        });
        return;
    }

}