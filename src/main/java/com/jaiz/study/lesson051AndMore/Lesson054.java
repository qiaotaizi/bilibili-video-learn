package com.jaiz.study.lesson051AndMore;

import com.jaiz.study.CategoryType;
import com.jaiz.study.Startable;
import com.jaiz.study.StartableMeta;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;

/**
 * 当Property值被修改后，失效监听首先调用
 * 多次修改Property的值却不做读取操作（例如get或者触发changeListener），失效监听将只被调用一次
 * 利用这个特性可以在某些场景下提升性能
 *
 * 结合WeakListener，将Listener对象声明为弱引用对象，当失去强饮用时，将被GC回收
 */
@Slf4j
@StartableMeta(title = "lesson054",
        category = CategoryType.LESSON,
        subtitle = "失效监听",
        digest = {"InvalidationListener","ChangeListener","WeakListener"})
public class Lesson054 extends Startable {
    @Override
    public void start(Stage primaryStage) throws Exception {

        SimpleIntegerProperty sip=new SimpleIntegerProperty(1);

        sip.addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                System.out.println("修改监听");
            }
        });

        sip.addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                System.out.println("失效监听");
            }
        });

        sip.set(2);
        sip.set(3);


    }
}
