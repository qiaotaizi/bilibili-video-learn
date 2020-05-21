package com.jaiz.study;

import javafx.scene.input.KeyCombination;
import javafx.scene.text.Font;

import org.junit.Assert;
import org.junit.Test;

import java.util.Properties;

import com.jaiz.study.lesson001.Lesson001;

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

    @Test
    public void packageNameTest(){
        var packName=this.getClass().getPackageName();
        Assert.assertEquals("com.jaiz.study", packName);
    }

    @Test
    public void childClassTest(){
        Class<Lesson001> lesson001Class=Lesson001.class;
        Class<Startable> startableClass=Startable.class;
        boolean isAsignable=startableClass.isAssignableFrom(lesson001Class);
        Assert.assertTrue("类型不匹配",isAsignable);
    }

}
