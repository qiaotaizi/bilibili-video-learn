package com.jaiz.study.lesson051AndMore;

import com.jaiz.study.CategoryType;
import com.jaiz.study.Startable;
import com.jaiz.study.StartableMeta;
import com.jaiz.study.utils.AppUtils;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@StartableMeta(title = "lesson055",
        category = CategoryType.LESSON,
        subtitle = "单向绑定和双向绑定",
        digest = {"绑定","解绑","双向绑定"})
public class Lesson055 extends Startable {

    @Override
    public void start(Stage primaryStage) throws Exception {
        AnchorPane root=new AnchorPane();

        Label l=new Label("代码演示见Lesson055.biDirectionBindingTest");

        Button button=new Button("宽度绑定");
        button.prefWidthProperty().bind(root.widthProperty());
        AnchorPane.setTopAnchor(button,20.0);


        TextField t1=new TextField();
        TextField t2=new TextField();
        AnchorPane.setTopAnchor(t1,60.0);
        AnchorPane.setTopAnchor(t2,60.0);
        AnchorPane.setLeftAnchor(t2,200.0);

        t1.textProperty().bindBidirectional(t2.textProperty());

        root.getChildren().addAll(l,button,t1,t2);

        AppUtils.quickInit(primaryStage,"lesson055",root);
        primaryStage.show();
    }

    public static void biDirectionBindingTest(){
        SimpleIntegerProperty x=new SimpleIntegerProperty(1);
        SimpleIntegerProperty y=new SimpleIntegerProperty(5);

        //将x的值绑定到y上，x将得到与y一致的值，当y变化时，x也会变成相同的值
        //而且绑定之后，再对x调用set方法，会抛异常
        x.bind(y);

        System.out.println(x.get());
        System.out.println(y.get());

        //解绑后，x可以自由设置值了
        x.unbind();

        System.out.println("===================");

        SimpleIntegerProperty a=new SimpleIntegerProperty(1);
        SimpleIntegerProperty b=new SimpleIntegerProperty(10);
        SimpleIntegerProperty c=new SimpleIntegerProperty(20);

        //虽然是双向绑定，但是调用这个方法的时候，a的值被b的值覆盖
        a.bindBidirectional(b);
        b.bindBidirectional(c);

        //传递性
        a.set(100);

        System.out.println(a.get());
        System.out.println(b.get());
        System.out.println(c.get());

        //双向绑定解绑
        a.unbindBidirectional(b);
        b.unbindBidirectional(c);


    }

}
