package com.jaiz.study;

import javafx.application.Application;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {

    public static void main(String[] args) {
        //字体渲染抗锯齿
        System.setProperty("prism.lcdtext", "false");
        log.info("main线程名：{}", Thread.currentThread().getName());
        Application.launch(Index.class, args);
    }

}