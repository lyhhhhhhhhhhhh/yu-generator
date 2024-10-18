package com.lyh.maker.meta;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import com.lyh.maker.meta.enums.FileGenerateEnum;
import com.lyh.maker.meta.enums.FileTypeEnum;
import com.lyh.maker.meta.enums.ModelTypeEnum;

import java.io.File;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * MetaValidator: 校验并填充 Meta 对象的默认值
 * 优化代码结构，使之更简洁、美观、可读
 * GPT 简化版
 * @author liyuhang
 * @version 1.0
 * @time 2024-10-18-15:07
 **/
public class MetaValidator {

    public static void doValidAndFill(Meta meta) {
        // 校验基础信息并设置默认值
        setDefault(meta::getName, meta::setName, "my-generator");
        setDefault(meta::getDescription, meta::setDescription, "我的模版代码生成器");
        setDefault(meta::getBasePackage, meta::setBasePackage, "com.lyh");
        setDefault(meta::getVersion, meta::setVersion, "1.0");
        setDefault(meta::getAuthor, meta::setAuthor, "lyh");
        setDefault(meta::getCreateTime, meta::setCreateTime, DateUtil.now());

        // 校验并处理文件配置
        validateFileConfig(meta.getFileConfig());

        // 校验并处理模型配置
        validateModelConfig(meta.getModelConfig());
    }

    // 校验并处理 FileConfig
    private static void validateFileConfig(Meta.FileConfigDTO fileConfig) {
        if (fileConfig == null) return;

        // 校验 sourceRootPath 必填项
        checkRequiredField(fileConfig.getSourceRootPath(), "sourceRootPath");

        // 设置 inputRootPath 和 outputRootPath 的默认值
        setDefault(fileConfig::getInputRootPath, fileConfig::setInputRootPath,
                () -> ".source" + File.separator + getLastPath(fileConfig.getSourceRootPath()));
        setDefault(fileConfig::getOutputRootPath, fileConfig::setOutputRootPath, "generated");
        setDefault(fileConfig::getType, fileConfig::setType, FileTypeEnum.DIR.getValue());

        // 校验并处理文件列表配置
        if (CollectionUtil.isNotEmpty(fileConfig.getFiles())) {
            fileConfig.getFiles().forEach(MetaValidator::validateFileInfo);
        }
    }

    // 校验并处理 FilesDTO 信息
    private static void validateFileInfo(Meta.FileConfigDTO.FilesDTO fileInfo) {
        // 校验 inputPath 必填项
        checkRequiredField(fileInfo.getInputPath(), "inputPath");

        // 设置 outputPath 和 type 的默认值
        setDefault(fileInfo::getOutputPath, fileInfo::setOutputPath, fileInfo.getInputPath());
        setDefault(fileInfo::getType, fileInfo::setType,
                () -> StrUtil.isBlank(FileUtil.getSuffix(fileInfo.getInputPath())) ? FileTypeEnum.DIR.getValue() : FileTypeEnum.FILE.getValue());

        // 根据文件后缀设置 generateType 的默认值
        setDefault(fileInfo::getGenerateType, fileInfo::setGenerateType,
                () -> fileInfo.getInputPath().endsWith(".ftl") ? FileGenerateEnum.DYNAMIC.getValue() : FileGenerateEnum.STATIC.getValue());
    }

    // 校验并处理 ModelConfig
    private static void validateModelConfig(Meta.ModelConfigDTO modelConfig) {
        if (modelConfig == null) return;

        // 校验并处理模型字段列表
        if (CollectionUtil.isNotEmpty(modelConfig.getModels())) {
            modelConfig.getModels().forEach(MetaValidator::validateModelInfo);
        }
    }

    // 校验并处理 ModelsDTO 信息
    private static void validateModelInfo(Meta.ModelConfigDTO.ModelsDTO modelInfo) {
        // 校验 fieldName 必填项
        checkRequiredField(modelInfo.getFieldName(), "fieldName");

        // 设置 type 的默认值
        setDefault(modelInfo::getType, modelInfo::setType, ModelTypeEnum.STRING.getValue());
    }

    // 通用的校验字段是否为空的方法
    private static void checkRequiredField(String field, String fieldName) {
        if (StrUtil.isBlank(field)) {
            throw new MetaException("未填写 " + fieldName);
        }
    }

    // 通用的设置默认值方法
    private static void setDefault(Supplier<String> getter, Consumer<String> setter, String defaultValue) {
        if (StrUtil.isBlank(getter.get())) {
            setter.accept(defaultValue);
        }
    }

    // 通用的设置默认值方法（带动态值）
    private static void setDefault(Supplier<String> getter, Consumer<String> setter, Supplier<String> defaultValueSupplier) {
        if (StrUtil.isBlank(getter.get())) {
            setter.accept(defaultValueSupplier.get());
        }
    }

    // 获取路径的最后一个层级
    private static String getLastPath(String path) {
        return Paths.get(path).getFileName().toString();
    }
}