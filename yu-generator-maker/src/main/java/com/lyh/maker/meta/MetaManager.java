package com.lyh.maker.meta;

import cn.hutool.core.io.resource.ResourceUtil;
import cn.hutool.json.JSONUtil;

/**
 * @author liyuhang
 * @version 1.0
 * @time 2024-10-17-20:31
 **/

public class MetaManager {

    //volatile 用于确保多线程环境下的内存可见行
    private static volatile Meta meta;

    public static Meta getMetaObject() {
        //双检锁
        if (meta == null){
            synchronized (MetaManager.class){
                if (meta == null){
                    meta = initMeta();
                }
            }
        }
        return meta;
    }

    public static Meta initMeta() {
        String metaJson = ResourceUtil.readUtf8Str("meta.json");
        Meta newMeta = JSONUtil.toBean(metaJson, Meta.class);
        // todo 校验配置文件、处理默认值
        return newMeta;
    }
}
