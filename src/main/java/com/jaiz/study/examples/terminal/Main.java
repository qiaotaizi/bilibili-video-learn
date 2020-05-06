package com.jaiz.study.examples.terminal;

import javafx.application.Application;

public class Main {

    public static void main(String[] args) {
        //字体渲染抗锯齿
        System.setProperty("prism.lcdtext", "false");
        Application.launch(FXTerminal.class,args);
    }

}
