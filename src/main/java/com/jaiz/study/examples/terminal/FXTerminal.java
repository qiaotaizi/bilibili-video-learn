package com.jaiz.study.examples.terminal;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class FXTerminal extends Application {

    Process shellProcess;

    @Override
    public void stop() throws Exception {
        super.stop();
        //关闭窗口时强制终止shell进程
        shellProcess.destroy();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {



        TextArea ta = new TextArea();

        ta.setEditable(false);
        ta.setFont(Font.font("mononoki", FontWeight.BOLD, FontPosture.REGULAR,18));
        ta.setStyle("-fx-background-color: antiquewhite;");

        VBox vb = new VBox();
        vb.setAlignment(Pos.CENTER);

        TextField cmdField = new TextField();
        //当前目录
        Label pwd=new Label();

        vb.getChildren().addAll(ta, cmdField);

        Scene scene = new Scene(vb);

        primaryStage.setTitle("FXTerminal");
        primaryStage.setScene(scene);
        primaryStage.show();

        primaryStage.heightProperty().addListener(
                (observable, oldValue, newValue) -> ta.setPrefHeight(newValue.doubleValue()));
        primaryStage.widthProperty().addListener(
                (observable, oldValue, newValue) -> ta.setPrefWidth(newValue.doubleValue()));

        //窗口展示后：

        //切换系统标准输出，将输出内容追加至textArea
        sysout2UI(ta);

        //启动zsh
        shellProcess = Runtime.getRuntime().exec("/bin/zsh");
        //将进程的标准输入流打印在TextArea上
        new Thread(new Runnable() {
            @Override
            public void run() {
                try(LineNumberReader reader=new LineNumberReader(new InputStreamReader(shellProcess.getInputStream(), StandardCharsets.UTF_8))){
                    String line;
                    while ((line=reader.readLine())!=null){
                        System.out.println(line);
                    }
                }catch (IOException e){
                    e.printStackTrace();
                }

            }
        }).start();
        //将进程的标准异常流打印在TextArea上
        new Thread(new Runnable() {
            @Override
            public void run() {
                try(LineNumberReader reader=new LineNumberReader(new InputStreamReader(shellProcess.getErrorStream(), StandardCharsets.UTF_8))){
                    String line;
                    while ((line=reader.readLine())!=null){
                        System.out.println(line);
                    }
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }).start();

        //获取进程的标准输出流
        BufferedWriter out=new BufferedWriter(new OutputStreamWriter(shellProcess.getOutputStream()));
        //命令输入完毕回车触发该事件
        cmdField.setOnAction(event -> {
            String t = cmdField.getText();
            //将输入的命令在TextArea上打印一份
            System.out.println(t);
            try {
                out.write(t+System.lineSeparator());
                out.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
            cmdField.clear();
        });

    }

    /**
     * 切换系统标准输出，将输出内容追加至textArea
     *
     * @param ta
     */
    private void sysout2UI(TextArea ta) {
        System.setOut(new PrintStream(new OutputStream() {
            @Override
            public void write(int b) throws IOException {
                //OutputStream的write(byte[] b, int off, int len)方法是调用write(int b)的
                //如果在write(int b)方法中向ta追加文本，由于方法实际接到的是一个byte而不是int，直接转char是不对的
                //所以这里废弃write(int b)方法，直接在write(byte[] b, int off, int len)中追加文本
            }

            @Override
            public void write(byte[] b, int off, int len) throws IOException {
                //如果s在lambda表达式内构建，会多追加一个换行，这里在表达式外构建
                String s = new String(b, off, len);
                //使用Platform.runLater，当UI线程空闲时进行输出，其中的逻辑运行于UI线程
                Platform.runLater(() -> ta.appendText(s));
            }
        }));
        //切换标准异常流与标准输出流相同
        System.setErr(System.out);
        ta.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                ta.setScrollTop(ta.getPrefHeight());
            }
        });
    }
}
