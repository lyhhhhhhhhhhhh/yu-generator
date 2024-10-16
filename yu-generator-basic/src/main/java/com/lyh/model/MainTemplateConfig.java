package com.lyh.model;

import lombok.Data;

/**
 * 静态模版配置
 * @author liyuhang
 * @version 1.0
 * @time 2024-10-16-21:35
 **/

@Data
public class MainTemplateConfig {

    /**
     * 作者（字符串，填充值）
     */
    private String author = "lyh";

    /**
     * 输出文本（字符串，填充值）
     */
    private String outputText = "输出结果";

    /**
     * 是否循环（开关）
     */
    private boolean loop;

}
