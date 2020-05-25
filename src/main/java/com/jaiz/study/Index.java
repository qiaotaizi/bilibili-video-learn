package com.jaiz.study;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

import com.jaiz.study.beans.Example;

import org.apache.commons.lang3.StringUtils;

import javafx.application.Application;
import javafx.application.HostServices;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TitledPane;
import javafx.scene.control.Tooltip;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Index extends Application {

    public static HostServices GLOBAL_HS;

    private Stage mainStage;

    private Map<CategoryType,ObservableList<Example>> accordionMap;

    private ScrollPane root;
    private AnchorPane ap_data;
    private AnchorPane ap_lookingFor;

    private Accordion accordion;

    @Override
    public void init() throws Exception {
        super.init();
        //初始化Application HostService
        GLOBAL_HS=getHostServices();
        log.info("Application.init()，线程名：{}",Thread.currentThread().getName());

        //初始化示例数据
        this.accordionMap=initAccordionMap();
        
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.mainStage=primaryStage;

        log.info("Application.init()，线程名：{}",Thread.currentThread().getName());
        root=new ScrollPane();
        ap_data=new AnchorPane();
        ap_lookingFor=new AnchorPane();
        ap_lookingFor.getChildren().add(new Label("查找面板打开了"));
        accordion=new Accordion();
        ap_data.getChildren().add(accordion);
        root.setContent(ap_data);
        ClassLoader loader = this.getClass().getClassLoader();
        sweepStartable(this.getClass().getPackageName(), accordionMap, loader);
        //排序优化
        //LESSON类型靠前
        //EXAMPLE类型靠后
        resolveSort(accordionMap);

        //向accordion填充数据
        fillData(accordion,accordionMap);

        //展开首个TitledPane
        accordion.getPanes().get(0).setExpanded(true);

        primaryStageSettings(primaryStage);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();

        addQuickEvent(scene);
    }

    /**
     * 向scene添加监听事件
     * @param scene
     */
    private void addQuickEvent(Scene scene) {
        KeyCombination kc_cmd_f=new KeyCodeCombination(KeyCode.F,KeyCombination.SHORTCUT_DOWN);
        scene.getAccelerators().put(kc_cmd_f, ()->{
            log.info("cmd+f fired");
            root.setContent(ap_lookingFor);
            // TODO 实现查找功能
        });
    }

    /**
     * stage参数初始化
     * 
     * @param primaryStage
     * @param scene
     */
    private void primaryStageSettings(Stage primaryStage) {
        primaryStage.setX(30);
        primaryStage.setY(50);
        primaryStage.setWidth(300);
        primaryStage.setHeight(700);
        primaryStage.setResizable(false);
        primaryStage.setTitle("javafx学习宝典");
    }

    /**
     * 填充数据至accordion
     * 
     * @param acc
     * @param accordionMap
     */
    private void fillData(Accordion acc, Map<CategoryType, ObservableList<Example>> accordionMap) {
        accordionMap.forEach((type,data)->{
            VBox vb=new VBox();
            TitledPane tp=new TitledPane(type.toString(),vb);
            var dataList=vb.getChildren();
            data.forEach(example->{
                String text=example.getTitle();
                if(StringUtils.isNotBlank(example.getSubtitle())){
                    text+=" - "+example.getSubtitle();
                }
                Hyperlink link=new Hyperlink(text);
                addLinkEvent(link,example);
                Tooltip tip=new Tooltip();
                tip.setText(concatTooltipText(example.getDigest()));
                link.setTooltip(tip);
                dataList.add(link);
            });
            tp.setAnimated(false);
            acc.getPanes().add(tp);
        });
    }

    /**
     * 添加跳转事件
     * @param link
     * @param example
     */
    private void addLinkEvent(Hyperlink link, Example example) {
        link.setOnAction(event->{
            Class<? extends Startable> appClass = example.getAppClazz();
                Startable s;
                try {
                    s = appClass.getDeclaredConstructor().newInstance();
                } catch (InstantiationException | IllegalAccessException | IllegalArgumentException
                        | InvocationTargetException | NoSuchMethodException | SecurityException e1) {
                    e1.printStackTrace();
                    return;
                }
                Stage subStage = new Stage();
                subStage.initModality(Modality.WINDOW_MODAL);
                subStage.initOwner(this.mainStage);
                try {
                    s.start(subStage);
                } catch (Exception e) {
                    e.printStackTrace();
                    return;
                }
        });
    }

    /**
     * 为map中的ObservableList元素排序
     */
    private void resolveSort(Map<CategoryType, ObservableList<Example>> accordionMap) {
        accordionMap.forEach((k,v)->resolveSort(v));
    }

    /**
     * 为ObservableList元素排序
     * @param v
     * @return
     */
    private void resolveSort(ObservableList<Example> examples) {
        examples.sort((e1,e2)->{
            int typeCompare=e1.getType().compareTo(e2.getType());
            if(typeCompare!=0){
                return typeCompare;
            }
            return e1.getTitle().compareTo(e2.getTitle());
        });
    }

    /**
     * 初始化accordionMap 为每个CategoryType初始化一个ObservableList
     * 
     * @return
     */
    private Map<CategoryType, ObservableList<Example>> initAccordionMap() {
        Map<CategoryType, ObservableList<Example>> result=new LinkedHashMap<>();
        for(CategoryType type :CategoryType.values()){
            result.put(type, FXCollections.observableArrayList());
        }
        return result;
    }

    /**
     * 拼接摘要作为提示工具的内容
     */
    private String concatTooltipText(String[] digest) {
        if(digest.length==0){
            return null;
        }
        StringBuilder sb=new StringBuilder();
        for(String d:digest){
            sb.append(d).append("，");
        }
        sb.deleteCharAt(sb.length()-1);
        return sb.toString();
    }

    /**
     * 扫描指定包下的所有Startable实现类
     * 
     * @param packageName
     * @return
     */
    @SuppressWarnings("unchecked")//消除类型转换警告
    private void sweepStartable(String packageName,Map<CategoryType,ObservableList<Example>> accordionMap, ClassLoader loader) {
        URL url = this.getClass().getClassLoader().getResource(packageName.replace(".", "/"));
        URI uri;
        try {
            uri = url.toURI();
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return;
        }
        File f = new File(uri);
        f.listFiles(file -> {
            // 递归查找
            if (file.isDirectory()) {
                sweepStartable(packageName + "." + file.getName(), accordionMap, loader);
                return false;
            }
            // 过滤非.class文件
            if (!file.getName().endsWith(".class")) {
                return false;
            }
            String className = packageName + "." + file.getName().replace(".class", "");
            Class<?> clazz;
            try {
                clazz = loader.loadClass(className);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                return false;
            }
            //过滤非Startable子类
            if(!Startable.class.isAssignableFrom(clazz)){
                return false;
            }
            //过滤Startable本身
            if(Objects.equals(Startable.class, clazz)){
                return false;
            }
            Class<Startable> clazzStartable=(Class<Startable>)clazz;
            //封装Example对象
            Example e=Example.fromClass(clazzStartable);
            var examples=accordionMap.get(e.getType());
            if (Objects.nonNull(examples)) {
                examples.add(e);
            }
            return false;
        });
        return;
    }

}
