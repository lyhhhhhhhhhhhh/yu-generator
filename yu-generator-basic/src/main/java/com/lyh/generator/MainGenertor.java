package com.lyh.generator;

import com.lyh.model.MainTemplateConfig;

import java.io.File;

import static com.lyh.generator.StaticGenerator.copyFilesByRecursive;

/**
 * TODO
 *
 * @author liyuhang
 * @version 1.0
 * @time 2024-10-16-21:59
 **/

public class MainGenertor {

    public static void main(String[] args) throws Exception{

        // 1.静态文件生成
        String projectPath = System.getProperty("user.dir");
        System.out.println(projectPath);
        //输入路径
        String inputPath = projectPath + File.separator + "yu-generator-demo-projects" + File.separator + "acm-template";
        //输出路径
        String outputPath = projectPath;
        //复制-传入输入输出路径
        StaticGenerator.copyFilesByRecursive(inputPath,outputPath);

        //动态文件生成
        String dynamicInputPath = projectPath + File.separator + "yu-generator-basic" + File.separator + "src/main/resources/templates/MainTemplate.java.ftl";
        String dynamicOutputPath = projectPath + File.separator + "acm-template/src/com/lyh/acm/MainTemplate.java";

        MainTemplateConfig mainTemplateConfig = new MainTemplateConfig();
        mainTemplateConfig.setAuthor("lyh");
        mainTemplateConfig.setLoop(true);
        mainTemplateConfig.setOutputText("我输出了：");
        DynamicGenerator.doGenerate(dynamicInputPath, dynamicOutputPath, mainTemplateConfig);

    }
}
