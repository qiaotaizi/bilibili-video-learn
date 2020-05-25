package com.jaiz.study;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import com.jaiz.study.beans.Example;

import org.apache.commons.lang3.StringUtils;

import javafx.application.Application;
import javafx.application.HostServices;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.Tooltip;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Index extends Application {

    public static HostServices GLOBAL_HS;

    private Stage mainStage;

    private Map<CategoryType, ObservableList<Example>> accordionMap;

    private ScrollPane root;
    private AnchorPane dataPane;
    private AnchorPane lookingForPane;

    private Accordion accordion;

    @Override
    public void init() throws Exception {
        super.init();
        // 初始化Application HostService
        GLOBAL_HS = getHostServices();
        log.info("Application.init()，线程名：{}", Thread.currentThread().getName());

        // 初始化示例数据
        this.accordionMap = initAccordionMap();
        ClassLoader loader = this.getClass().getClassLoader();
        sweepStartable(this.getClass().getPackageName(), accordionMap, loader);
        // 排序优化
        // LESSON类型靠前
        // EXAMPLE类型靠后
        resolveSort(accordionMap);

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.mainStage = primaryStage;

        log.info("Application.init()，线程名：{}", Thread.currentThread().getName());
        root = new ScrollPane();
        dataPane = new AnchorPane();
        accordion = new Accordion();
        dataPane.getChildren().add(accordion);
        root.setContent(dataPane);

        // 向accordion填充数据
        fillData(accordion, accordionMap);

        // 展开首个TitledPane
        accordion.getPanes().get(0).setExpanded(true);

        primaryStageSettings(primaryStage);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();

        // 初始化查找面板UI
        lookingForPaneInit();
        addQuickEvent(scene);
    }

    /**
     * 初始化查找面板的UI
     */
    private void lookingForPaneInit() {
        lookingForPane = new AnchorPane();
        HBox inputLine = new HBox();
        TextField searchTextField = new TextField();
        searchTextField.setPromptText("请输入查找项");
        Button clearBtn = new Button("清除");
        Button backBtn = new Button("返回");
        inputLine.getChildren().addAll(searchTextField, clearBtn, backBtn);

        VBox container = new VBox();
        VBox dataContainer=new VBox();
        container.getChildren().addAll(inputLine,dataContainer);
        lookingForPane.getChildren().add(container);

        addSearchLineEvent(searchTextField, clearBtn, backBtn, dataContainer);

    }

    /**
     * 为搜索行添加事件
     * 
     * @param searchTextField
     * @param clearBtn
     * @param backBtn
     * @param container
     */
    private void addSearchLineEvent(TextField searchTextField, Button clearBtn, Button backBtn, VBox container) {
        // 点击返回时显示数据面板
        backBtn.setOnAction(event -> {
            root.setContent(dataPane);
        });

        // 点击清除时清空搜索内容，并获取焦点
        clearBtn.setOnAction(event -> {
            searchTextField.setText("");
            searchTextField.requestFocus();
        });
        
        searchTextField.textProperty().addListener((ob, o, n) -> {
            List<Example> matchedData = filtingData(n);
            var elems=container.getChildren();
            elems.clear();
            matchedData.forEach(e->{
                Hyperlink link=example2Link(e);
                elems.add(link);
            });
        });

    }

    /**
     * 根据关键字过滤数据
     * 
     * @param keyword
     * @return
     */
    private List<Example> filtingData(String keyword) {
        var result = new ArrayList<Example>();
        if (StringUtils.isBlank(keyword)) {
            return result;
        }
        accordionMap.forEach((k, v) -> {
            result.addAll(v.stream().filter(e -> e.getTitle().contains(keyword) || e.getSubtitle().contains(keyword))
                    .collect(Collectors.toList()));
        });
        return result;
    }

    /**
     * 向scene添加快捷键监听事件
     * 
     * @param scene
     */
    private void addQuickEvent(Scene scene) {
        //cmd+f查找
        KeyCombination kc_cmd_f = new KeyCodeCombination(KeyCode.F, KeyCombination.SHORTCUT_DOWN);
        scene.getAccelerators().put(kc_cmd_f, () -> {
            log.info("cmd+f fired");
            root.setContent(lookingForPane);
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
        accordionMap.forEach((type, data) -> {
            VBox vb = new VBox();
            TitledPane tp = new TitledPane(type.toString(), vb);
            var dataList = vb.getChildren();
            data.forEach(example -> {
                Hyperlink link = example2Link(example);
                dataList.add(link);
            });
            tp.setAnimated(false);
            acc.getPanes().add(tp);
        });
    }

    /**
     * 将一个example转化为Hyperlink
     * 
     * @param example
     * @return
     */
    private Hyperlink example2Link(Example example) {
        String text = example.getTitle();
        if (StringUtils.isNotBlank(example.getSubtitle())) {
            text += " - " + example.getSubtitle();
        }
        Hyperlink link = new Hyperlink(text);
        addLinkEvent(link, example);
        Tooltip tip = new Tooltip();
        tip.setText(concatTooltipText(example.getDigest()));
        link.setTooltip(tip);
        return link;
    }

    /**
     * 添加跳转事件
     * 
     * @param link
     * @param example
     */
    private void addLinkEvent(Hyperlink link, Example example) {
        link.setOnAction(event -> {
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
        accordionMap.forEach((k, v) -> resolveSort(v));
    }

    /**
     * 为ObservableList元素排序
     * 
     * @param v
     * @return
     */
    private void resolveSort(ObservableList<Example> examples) {
        examples.sort((e1, e2) -> {
            int typeCompare = e1.getType().compareTo(e2.getType());
            if (typeCompare != 0) {
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
        Map<CategoryType, ObservableList<Example>> result = new LinkedHashMap<>();
        for (CategoryType type : CategoryType.values()) {
            result.put(type, FXCollections.observableArrayList());
        }
        return result;
    }

    /**
     * 拼接摘要作为提示工具的内容
     */
    private String concatTooltipText(String[] digest) {
        if (digest.length == 0) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (String d : digest) {
            sb.append(d).append("，");
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    /**
     * 扫描指定包下的所有Startable实现类
     * 
     * @param packageName
     * @return
     */
    @SuppressWarnings("unchecked") // 消除类型转换警告
    private void sweepStartable(String packageName, Map<CategoryType, ObservableList<Example>> accordionMap,
            ClassLoader loader) {
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
            // 过滤非Startable子类
            if (!Startable.class.isAssignableFrom(clazz)) {
                return false;
            }
            // 过滤Startable本身
            if (Objects.equals(Startable.class, clazz)) {
                return false;
            }
            Class<Startable> clazzStartable = (Class<Startable>) clazz;
            // 封装Example对象
            Example e = Example.fromClass(clazzStartable);
            var examples = accordionMap.get(e.getType());
            if (Objects.nonNull(examples)) {
                examples.add(e);
            }
            return false;
        });
        return;
    }

}
