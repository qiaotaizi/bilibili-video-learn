package com.jaiz.study.lesson033;

import com.jaiz.study.CategoryType;
import com.jaiz.study.Startable;
import com.jaiz.study.StartableMeta;
import com.jaiz.study.utils.AppUtils;

import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import lombok.extern.slf4j.Slf4j;

/**
 * 通过TextFormatter限制输入内容
 */
@Slf4j
@StartableMeta(title = "lesson033", category = CategoryType.LESSON,
        subtitle = "TextFormatter")
public class App extends Startable {
    @Override
    public void start(Stage primaryStage) throws Exception {

        VBox root = new VBox();


        TextField textField = new TextField();
        TextArea textArea = new TextArea();
        root.getChildren().addAll(textField, textArea);


        /*
         * TextFormatter的第一种用法：
         * filter用法
         */
        textField.setTextFormatter(new TextFormatter<>(change -> {
            //当输入内容满足小写英文字母时，响应内容变化，否则什么都不做
            if (change.getText().matches("[a-z]*")) {
                return change;
            }
            return null;
        }));

        /*
         * TextFormatter的第二种用法：
         * valueConverter用法
         * 当文本域获取焦点时触发toString，
         * 失焦时先触发fromString然后触发toString
         * 或者通过textArea.commitValue();来主动触发
         *
         * StringConverter可以设置泛型为其他类型例如Integer，用以限制用户输入的内容
         */
        textArea.setTextFormatter(new TextFormatter<>(
                new StringConverter<String>() {
                    @Override
                    public String toString(String o) {
                        log.info("StringConverter.toString 触发");
                        return o;
                    }

                    @Override
                    public String fromString(String s) {
                        log.info("StringConverter.fromString 触发");
                        return s;
                    }
                }
        ));


        AppUtils.quickInit(primaryStage, "lesson033", root);
        primaryStage.show();
    }
}
