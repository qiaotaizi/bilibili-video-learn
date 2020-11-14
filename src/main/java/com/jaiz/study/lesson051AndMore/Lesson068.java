package com.jaiz.study.lesson051AndMore;

import com.jaiz.study.CategoryType;
import com.jaiz.study.Startable;
import com.jaiz.study.StartableMeta;
import com.jaiz.study.utils.AppUtils;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@StartableMeta(title = "lesson068",
        category = CategoryType.LESSON,
        subtitle = "ImageView",
        digest = {"Image载入内存后的缩放知识点","获取按比例缩放图片的实际宽高","fitWidth/fitHeight","图片的裁切","viewport"})
public class Lesson068 extends Startable {



    @Override
    public void start(Stage primaryStage) throws Exception {
        /*
        ImageView加载入内存后，再通过set方法修改imageView的大小，会导致图片的失真。
        可以利用这个技巧对内存进行节约
        或者在加载时不设置大小，即使用图片自己的尺寸，再在此基础上对图片进行缩放
         */

        Image img=new Image("file:/Users/jaiz/Downloads/每周壁纸精选第151期/69.jpg");

        ImageView iv=new ImageView(img);
        iv.setFitHeight(600);
        iv.setFitWidth(600);
        iv.setPreserveRatio(true);
        iv.setSmooth(true);

        log.info("ImageView.fitHeight = {}, ImageView.fitWidth = {}",iv.getFitHeight(),iv.getFitWidth());
        //通过从Node继承过来的prefHeight/prefWidth获取计算出的真实宽高
        //参数-1的意义为：TODO
        log.info("ImageView.prefHeight = {}, ImageView.prefWidth = {}",iv.prefHeight(-1),iv.prefWidth(-1));


        //圆角裁切
        Rectangle rect=new Rectangle(iv.prefWidth(-1),iv.prefHeight(-1));
        rect.setArcHeight(30);
        rect.setArcWidth(30);

        iv.setClip(rect);

        Rectangle2D rect2d=new Rectangle2D(0,0,200,200);
        Image imgvp=new Image("file:/Users/jaiz/Downloads/每周壁纸精选第151期/68.jpg",600,600,true,false);
        ImageView ivvp=new ImageView(imgvp);
        double ta=iv.prefHeight(-1)+20;
        AnchorPane.setTopAnchor(ivvp,iv.prefHeight(-1)+20);

        ivvp.setViewport(rect2d);

        ivvp.setOnMouseDragged(event -> {
            Rectangle2D vp=new Rectangle2D(event.getSceneX(), event.getSceneY()-ta, 200,200);
            ivvp.setViewport(vp);
        });


        AnchorPane root=new AnchorPane();
        root.getChildren().addAll(iv,ivvp);
        AppUtils.quickInit(primaryStage,"lesson068",root);
        primaryStage.show();
    }
}
