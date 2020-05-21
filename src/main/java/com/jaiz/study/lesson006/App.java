package com.jaiz.study.lesson006;

import com.jaiz.study.CategoryType;
import com.jaiz.study.Startable;
import com.jaiz.study.StartableMeta;

import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * Screen api
 * 获取主副屏幕局
 * 获取屏幕边界，DPI等
 */
@StartableMeta(title = "lesson006",category = CategoryType.LESSON,
subtitle = "Screen api",digest = {"获取主副屏幕局","获取屏幕边界，DPI等"})
public class App extends Startable {
    @Override
    public void start(Stage primaryStage) throws Exception {
        //获取主屏幕
        Screen screen=Screen.getPrimary();
        //获取全部屏幕
        //Screen.getScreens();

        //主屏幕的两种边界
        //对于mac而言，bounds表示整个物理屏幕的边界
        //visualBounds表示除去上部系统标题栏的屏幕边界
        Rectangle2D bounds=screen.getBounds();
        System.out.println("屏幕边界"+bounds);
        Rectangle2D visualBounds=screen.getVisualBounds();
        System.out.println("可视屏幕边界"+visualBounds);

        System.out.println("屏幕Dpi="+screen.getDpi());

        //primaryStage.setHeight(visualBounds.getHeight());
        //primaryStage.setWidth(visualBounds.getWidth());

        primaryStage.show();
    }
}
