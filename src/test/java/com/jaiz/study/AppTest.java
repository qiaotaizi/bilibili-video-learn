package com.jaiz.study;

import com.jaiz.study.utils.Formatters;
import javafx.scene.input.KeyCombination;
import javafx.scene.text.Font;
import org.junit.Test;

import java.awt.*;
import java.util.Properties;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void keyCodeTest()
    {
        System.out.println(KeyCombination.SHORTCUT_DOWN);
    }

    /**
     * 获取系统变量
     */
    @Test
    public void sysPropertiesTest(){
        Properties properties=System.getProperties();
        properties.forEach((k,v)->{
            System.out.println(k+":"+v);
        });
    }

    /**
     * 获取系统支持的字体
     */
    @Test
    public void fontTest(){
        System.out.println(Font.getDefault());
        for (String family : Font.getFamilies()) {
            System.out.println(family);
        }
    }

    @Test
    public void formatPrintingTest(){
        System.out.println(String.format("hello，%s, I'm %s, %d years old this year.", "world", "jaiz", 5));
    }

}
