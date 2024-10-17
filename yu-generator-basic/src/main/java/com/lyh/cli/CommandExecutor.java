package com.lyh.cli;

import com.lyh.cli.command.ConfigCommand;
import com.lyh.cli.command.GenerateCommand;
import com.lyh.cli.command.ListCommand;
import picocli.CommandLine;
import picocli.CommandLine.Command;

/**
 *
 * @author liyuhang
 * @version 1.0
 * @time 2024-10-17-15:37
 **/

@Command(name = "yu", mixinStandardHelpOptions = true)
public class CommandExecutor implements Runnable {

    //执行命令
    private final CommandLine commandLine;

    {
        commandLine = new CommandLine(this)
                .addSubcommand(new GenerateCommand())
                .addSubcommand(new ConfigCommand())
                .addSubcommand(new ListCommand());
    }

    @Override
    public void run() {
        // 不输入子命令时，给出友好提示
        System.out.println("请输入具体命令，或者输入 --help 查看命令提示");
    }

    /**
     * 执行命令
     *
     * @param args
     * @return
     */
    public Integer doExecute(String[] args) {
        return commandLine.execute(args);
    }
}

