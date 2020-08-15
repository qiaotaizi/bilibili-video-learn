package com.jaiz.study.lesson051AndMore;

import com.jaiz.study.CategoryType;
import com.jaiz.study.Startable;
import com.jaiz.study.StartableMeta;
import com.jaiz.study.utils.AppUtils;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;

import javax.swing.text.html.Option;
import java.io.File;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@StartableMeta(title = "lesson065",
        category = CategoryType.LESSON,
        subtitle = "FileChooser, DirectChooser",
        digest = {"FileChooser", "DirectChooser"})
public class Lesson065 extends Startable {
    @Override
    public void start(Stage primaryStage) throws Exception {
        AnchorPane root = new AnchorPane();

        Button showFileChooser = new Button("选择文件");
        AnchorPane.setTopAnchor(showFileChooser, 10.0);
        AnchorPane.setLeftAnchor(showFileChooser, 10.0);

        Label fileLabel=new Label();
        AnchorPane.setTopAnchor(fileLabel,10.0);
        AnchorPane.setLeftAnchor(fileLabel,130.0);

        Button showDirChooser = new Button("选择目录");
        AnchorPane.setTopAnchor(showDirChooser, 50.0);
        AnchorPane.setLeftAnchor(showDirChooser, 10.0);

        Label dirLabel=new Label();
        AnchorPane.setTopAnchor(dirLabel,50.0);
        AnchorPane.setLeftAnchor(dirLabel,130.0);


        root.getChildren().addAll(showFileChooser, showDirChooser,fileLabel,dirLabel);

        showFileChooser.setOnAction(event -> {
            FileChooser fc = new FileChooser();
            Optional<File> fileOptional=Optional.ofNullable(fc.showOpenDialog(primaryStage));
            fileOptional.ifPresent(f->fileLabel.setText(f.getAbsolutePath()));
        });

        showDirChooser.setOnAction(event -> {
            DirectoryChooser dc = new DirectoryChooser();
            Optional.ofNullable(dc.showDialog(primaryStage)).ifPresent(f -> dirLabel.setText(f.getAbsolutePath()));
        });


        AppUtils.quickInit(primaryStage, "lesson065", root);
        primaryStage.show();
    }
}
