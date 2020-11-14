package com.jaiz.study;

import javafx.css.Stylesheet;
import javafx.scene.input.KeyCombination;
import javafx.scene.text.Font;
import lombok.extern.slf4j.Slf4j;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

import com.jaiz.study.lesson001.Lesson001;

import javax.swing.text.Style;
import javax.swing.text.html.StyleSheet;

/**
 * Unit test for simple App.
 */
@Slf4j
public class AppTest {
    /**
     * Rigorous Test :-)
     */
    @Test
    public void keyCodeTest() {
        log.info("SHORTCUT_DOWN kc = {}", KeyCombination.SHORTCUT_DOWN);
    }

    /**
     * 获取系统变量
     */
    @Test
    public void sysPropertiesTest() {
        Properties properties = System.getProperties();
        properties.forEach((k, v) -> {
            log.info(k + ":" + v);
        });
    }

    /**
     * 获取系统支持的字体
     */
    @Test
    public void fontTest() {
        log.info("Font.getDefault = {}", Font.getDefault());
        for (String family : Font.getFamilies()) {
            log.info(family);
        }
    }

    @Test
    public void formatPrintingTest() {
        log.info(String.format("hello，%s, I'm %s, %d years old this year.", "world", "jaiz", 5));
    }

    @Test
    public void packageNameTest() {
        var packName = this.getClass().getPackageName();
        Assert.assertEquals("com.jaiz.study", packName);
    }

    @Test
    public void childClassTest() {
        Class<Lesson001> lesson001Class = Lesson001.class;
        Class<Startable> startableClass = Startable.class;
        boolean isAsignable = startableClass.isAssignableFrom(lesson001Class);
        Assert.assertTrue("类型不匹配", isAsignable);
    }

    @Test
    public void cssTest() {

        StyleSheet sheet=new StyleSheet();
        sheet.importStyleSheet(this.getClass().getClassLoader().getResource("css/ladder.css"));
        Style style=sheet.getRule(".root");
        System.out.println(style.getName());
        Object o=style.getAttribute("bgc");
        System.out.println(o);


    }

}
