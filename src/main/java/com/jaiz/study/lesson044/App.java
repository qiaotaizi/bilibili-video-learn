package com.jaiz.study.lesson044;

import com.jaiz.study.beans.Student;
import com.jaiz.study.utils.AppDesc;
import com.jaiz.study.utils.AppUtils;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@AppDesc({"Pagination的使用","分页器"})
public class App extends Application {

    private Random generator=new Random();


    @Override
    public void start(Stage primaryStage) throws Exception {

        AnchorPane root = new AnchorPane();

        Pagination page=new Pagination();

        //当前页码索引（从零开始）
        page.setCurrentPageIndex(0);
        //页码栏显示几个页面按钮
        page.setMaxPageIndicatorCount(8);
        //总页数，如果希望设置无限翻页的话可以设置为Pagination.INDETERMINATE
        page.setPageCount(30);

        //启用子弹样式
        page.getStyleClass().add(Pagination.STYLE_CLASS_BULLET);

        //分页方法
        page.setPageFactory(new Callback<Integer, Node>() {
            @Override
            public Node call(Integer param) {

                List<Student> data= requestData(param);
                VBox vBox=new VBox();

                data.forEach(
                        student -> {
                            HBox hBox=new HBox();
                            Label idLabel=new Label(student.getId()+"");
                            Label nameLabel=new Label(student.getName());
                            nameLabel.setPadding(new Insets(0,0,0,10));
                            Label ageLabel=new Label(student.getAge()+"");
                            ageLabel.setPadding(new Insets(0,0,0,10));
                            hBox.getChildren().addAll(idLabel,nameLabel,ageLabel);
                            vBox.getChildren().addAll(hBox);
                        }
                );

                return vBox;
            }
        });

        root.getChildren().add(page);

        AppUtils.quickInitMenuBar(primaryStage,  this.getClass(), root);
    }

    /**
     * 模拟数据请求
     * @param param
     * @return
     */
    private List<Student> requestData(Integer param) {
        List<Student> data=new ArrayList<>(20);
        int startIndex=param*20+1;
        for (int i=startIndex;i<startIndex+20;i++){
            data.add(Student.builder().id(i)
                    .name("学生"+generator.nextInt(100))
                    .age(generator.nextInt(20))
                    .build());
        }

        return data;
    }

}
