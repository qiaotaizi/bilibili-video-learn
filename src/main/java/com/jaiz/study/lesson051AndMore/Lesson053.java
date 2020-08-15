package com.jaiz.study.lesson051AndMore;

import com.jaiz.study.CategoryType;
import com.jaiz.study.Startable;
import com.jaiz.study.StartableMeta;
import com.jaiz.study.utils.AppUtils;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

@StartableMeta(category = CategoryType.LESSON, title = "lesson053",
        subtitle = "绑定集合类", digest = {"集合类change事件"})
public class Lesson053 extends Startable {
    @Override
    public void start(Stage primaryStage) throws Exception {
        AnchorPane root = new AnchorPane();

        ObservableList<String> obList = FXCollections.observableArrayList();
        obList.add("a");
        obList.add("b");
        obList.add("c");

        obList.addListener(new ListChangeListener<String>() {
            @Override
            public void onChanged(Change<? extends String> change) {
                System.out.println("change = " + change);
                change.getList().forEach(System.out::println);
                System.out.println("======");
                while (change.next()) {
                    //该循环中的方法需要放在next调用后执行，否则会出错
                    System.out.println("from = " + change.getFrom());
                    System.out.println("to = " + change.getTo());
                    //添加元素时返回true
                    System.out.println("wasAdded = " + change.wasAdded());
                    //排序时返回true，注意当ObservableList当中持有的集合本身发生排序时，
                    //返回true，直接对ObservableList进行排序，则认为是进行replace操作
                    System.out.println("wasPermutated = " + change.wasPermutated());
                    //移除时返回true
                    System.out.println("wasRemoved = " + change.wasRemoved());
                    //替换时返回true
                    System.out.println("wasReplaced = " + change.wasReplaced());
                    //更新时返回true
                    System.out.println("wasUpdated = " + change.wasUpdated());
                }
            }
        });
        System.out.println("adding d");
        obList.add("d");
        System.out.println("removing a");
        obList.remove("a");


        AppUtils.quickInit(primaryStage, "lesson053", root);
    }
}
