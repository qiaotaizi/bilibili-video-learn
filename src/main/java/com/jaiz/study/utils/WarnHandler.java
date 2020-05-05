package com.jaiz.study.utils;

import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.Objects;

public class WarnHandler implements Thread.UncaughtExceptionHandler {

    private static WarnHandler INSTANCE;

    private AnchorPane root;

    private Stage warnStage;

    private Text warnText;
    private Text messageText;
    private TextArea ta;

    /**
     * 在主线程中设置线程异常处理方式
     * @return
     */
    public static WarnHandler getInstance(){
        if (Objects.isNull(INSTANCE)){
            synchronized (WarnHandler.class){
                if (Objects.isNull(INSTANCE)){
                    INSTANCE=new WarnHandler();
                }
            }
        }
        return INSTANCE;
    }

    /**
     * 在ui线程中调用，只能调用一次
     * @param rootStage
     */
    public void initWarnHandlerLater(Stage rootStage){
        warnStage=new Stage();
        warnStage.initOwner(rootStage);
        root=new AnchorPane();
        Scene scene=new Scene(root);
        warnStage.setScene(scene);
        warnStage.setTitle("警告");
        warnStage.initStyle(StageStyle.UTILITY);
        warnStage.initModality(Modality.WINDOW_MODAL);

        ta=new TextArea();
        warnText=new Text();
        messageText=new Text();

        VBox vb=new VBox();
        vb.getChildren().addAll(warnText,messageText,ta);

        root.getChildren().add(vb);
    }

    private WarnHandler(){

    }

    @Override
    public String toString() {
        return "我是WarnHandler";
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        e.printStackTrace();
        warnText.setText("发生异常，请检查输入的sql");
        messageText.setText(e.getClass().getName()+": "+e.getMessage());
        ta.setText(stackTraceToString(e.getStackTrace()));
        this.warnStage.show();
    }

    /**
     * 异常栈转字符串
     * @param stackTrace
     * @return
     */
    private String stackTraceToString(StackTraceElement[] stackTrace) {
        StringBuilder sb=new StringBuilder();
        for (StackTraceElement stackTraceElement : stackTrace) {
            sb.append(stackTraceElement.toString());
            sb.append(System.lineSeparator());
        }
        return sb.toString();
    }
}
