package com.jaiz.study.lesson051AndMore;

import com.jaiz.study.CategoryType;
import com.jaiz.study.Startable;
import com.jaiz.study.StartableMeta;
import com.jaiz.study.utils.AppUtils;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Optional;

@Slf4j
@StartableMeta(title = "lesson070",
        category = CategoryType.LESSON,
        subtitle = "WritableImage",
        digest = {"WritableImage", "PixelWriter", "将内存中的图片保存至硬盘"})
public class Lesson070 extends Startable {


    @Override
    public void start(Stage primaryStage) throws Exception {


        WritableImage wi = new WritableImage(100, 100);
        PixelWriter writer = wi.getPixelWriter();
        ImageView iv = new ImageView(wi);
        for (int i = 0; i < 50; i++) {
            for (int j = 0; j < 50; j++) {
                writer.setColor(i, j, Color.BLACK);
            }
        }

        for (int i = 0; i < 100; i++) {
            writer.setColor(i, i, Color.GREEN);
        }

        //将磁盘已有的图片写入新的图片
        Image image = new Image("file:/Users/jaiz/Downloads/每周壁纸精选第151期/62.jpg");
        WritableImage wi2 = new WritableImage(image.getPixelReader(), 1920, 1080);
        PixelWriter w2 = wi2.getPixelWriter();
        ImageView iv2 = new ImageView(wi2);
        for (int i = 0; i < 500; i++) {
            w2.setColor(i, i, Color.WHITE);
        }


        Button b = new Button("生成图片");
        b.setOnAction(event -> {
            String format="png";// TODO mac上只支持png？
            log.info("生成新图片");
            FileChooser fc = new FileChooser();
            fc.setTitle("图片另存");
            fc.getExtensionFilters().add(new FileChooser.ExtensionFilter(format, "*."+format));
            Optional<File> fileOptional = Optional.ofNullable(fc.showSaveDialog(primaryStage));
            fileOptional.ifPresent(f -> {
//                if (!f.exists()) {
                    try {
//                        boolean created = f.createNewFile();
//                        if (!created){
//                            log.error("未能创建图片");
//                            return;
//                        }
                        //将fx内存图片写入磁盘
                        BufferedImage bi = SwingFXUtils.fromFXImage(wi2, null);
                        ImageIO.write(bi, format, f);
                        log.info("...");
                    } catch (IOException e) {
                        log.error("文件错误", e);
                    }
//                }
            });

        });

        AnchorPane root = new AnchorPane();

        root.getChildren().addAll(iv2,iv, b);

        AppUtils.quickInit(primaryStage, "lesson070", root);
        primaryStage.show();
    }


}
