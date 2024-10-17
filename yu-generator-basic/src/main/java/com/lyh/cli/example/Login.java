package com.lyh.cli.example;

import picocli.CommandLine;
import picocli.CommandLine.Option;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Callable;


/**
 * TODO
 *
 * @author liyuhang
 * @version 1.0
 * @time 2024-10-17-14:43
 **/

public class Login implements Callable<Integer> {

    @Option(names = {"-u", "--user"}, description = "user name")
    String user;

    //interactive 交互式输入
    @Option(names = {"-p", "--password"}, description = "password", interactive = true,prompt = " 请输入密码 ",arity = "0..1")
    String password;

    //interactive 交互式输入
    @Option(names = {"-cp", "--checkPassword"}, description = "Check Password", interactive = true,arity = "0..1")
    String checkPassword;

    @Override
    public Integer call() throws Exception {
        System.out.println("用户名: " + user);
        System.out.println("密码: " + password);
        System.out.println("确认密码: " + checkPassword);
        return 0;
    }

    public static void main(String[] args) {
        //args = new String[] {"-u", "liyuhang", "-p", "123456"};
        List<String> newArgs = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        // 检查是否有 -u 参数，没有则提示输入
        boolean hasUser = false;
        boolean hasPassword = false;
        boolean hasCheckPassword = false;

        for (String arg : args) {
            if (arg.equals("-u") || arg.equals("--user")) {
                hasUser = true;
            }
            if (arg.equals("-p") || arg.equals("--password")) {
                hasPassword = true;
            }
            if (arg.equals("-cp") || arg.equals("--checkPassword")) {
                hasCheckPassword = true;
            }
            newArgs.add(arg);
        }

        // 如果没有输入 -u 选项，则自动提示输入
        if (!hasUser) {
            System.out.print("请输入用户名: ");
            String user = scanner.nextLine();
            newArgs.add("-u");
            newArgs.add(user);
        }

        // 如果没有输入 -p 选项，则自动提示输入密码
        if (!hasPassword) {
            System.out.print("请输入密码: ");
            String password = scanner.nextLine();
            newArgs.add("-p");
            newArgs.add(password);
        }

        // 如果没有输入 -cp 选项，则自动提示输入确认密码
        if (!hasCheckPassword) {
            System.out.print("请再次输入密码: ");
            String checkPassword = scanner.nextLine();
            newArgs.add("-cp");
            newArgs.add(checkPassword);
        }

        // 执行命令行
        new CommandLine(new Login()).execute(newArgs.toArray(new String[0]));
    }
}