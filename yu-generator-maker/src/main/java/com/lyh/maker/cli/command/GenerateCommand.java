package com.lyh.maker.cli.command;

import cn.hutool.core.bean.BeanUtil;
import com.lyh.maker.generator.file.FileGenerator;
import com.lyh.maker.model.DataModel;
import lombok.Data;
import picocli.CommandLine.*;
import picocli.CommandLine.Command;

import java.util.concurrent.Callable;


@Command(name = "generate", description = "生成代码", mixinStandardHelpOptions = true)
@Data
public class GenerateCommand implements Callable<Integer> {

    @Option(names = {"-l", "--loop"}, arity = "0..1", description = "是否循环", interactive = true, echo = true)
    private boolean loop;

    @Option(names = {"-a", "--author"}, arity = "0..1", description = "作者", interactive = true, echo = true)
    private String author = "yupi";

    @Option(names = {"-o", "--outputText"}, arity = "0..1", description = "输出文本", interactive = true, echo = true)
    private String outputText = "sum = ";

    public Integer call() throws Exception {
        DataModel DataModel = new DataModel();
        BeanUtil.copyProperties(this, DataModel);
        System.out.println("配置信息：" + DataModel);
        FileGenerator.doGenerate(DataModel);
        return 0;
    }
}

