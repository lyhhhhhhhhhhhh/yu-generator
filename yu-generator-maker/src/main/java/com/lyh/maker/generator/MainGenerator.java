package com.lyh.maker.generator;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.resource.ClassPathResource;
import cn.hutool.core.util.StrUtil;
import com.lyh.maker.generator.file.DynamicFileGenerator;
import com.lyh.maker.meta.Meta;
import com.lyh.maker.meta.MetaManager;

import java.io.File;

/**
 * @author liyuhang
 * @version 1.0
 * @time 2024-10-17-20:50
 **/

public class MainGenerator extends GenerateTemplate{


    @Override
    protected void buildDist(String outputPath, String sourceCopyDestPath, String jarPath, String shellOutputFilePath) {
        System.out.println("不输出dist");
    }

}
