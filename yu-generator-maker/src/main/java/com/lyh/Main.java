package com.lyh;


import com.lyh.maker.cli.CommandExecutor;
import com.lyh.maker.generator.MainGenerator;

public class Main {

    public static void main(String[] args) throws Exception {
        MainGenerator mainGenerator = new MainGenerator();
        mainGenerator.doGenerate();
    }
}

