package com.tianyi.helmet.server.util;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

/**
 * <p>
 * 这个类用于在Java程序中构建和执行Linux中的sudo命令。
 * 用法1：
 *    1. 执行buildCommands(...)方法构造sudo命令串。有两种方法可以构造sudo命令串：
 *
 *       若调用builderCommands(String cmd)方法构造sudo命令串，则此前应先修改/etc/sudoers文件，
 *       在其中添加一行：
 *          username  ALL=(ALL) NOPASSWD:ALL
 *       其中，"username"是需要运行这个程序的用户名。
 *
 *       若不想修改/etc/sudoers文件，则需要调用builderCommands(String cmd, String passwd)方法
 *       构造sudo命令串，
 *
 *       注意：无论使用哪种方法，形参cmd中均值包含需要以sudo方式执行的命令，不包含"sudo"命令本身。
 *
 *    2. 调用run(String[] cmds)执行由buildCommands返回的命令串数组。
 *
 * 用法2：
 *    1. 修改/etc/sudoers文件，在其中添加一行：
 *          username  ALL=(ALL) NOPASSWD:ALL
 *       其中，"username"是需要运行这个程序的用户名。
 *
 *    2. 调用run(String cmd)方法执行命令。
 *
 *    注意：形参cmd中仅包含需要以sudo方式执行的命令字符串，不要包含"sudo"命令本身。
 * </p>
 */

/**
 * @author kingfox
 *
 */
public class CommandExecutor {

    /**
     * @param cmd
     * @return
     */
    public static String[] buildSudoCommands(String cmd)   // to use this method, you should modify /etc/sudoers
    {
        String[] cmds = {shellName, shellParam, sudoCmd + " " + cmd};
        return cmds;
    }

    public static String[] buildSudoCommands(String cmd, String sudoPasswd) {
        String[] cmds = {shellName, shellParam, "echo \"" + sudoPasswd + "\" | " + sudoCmd + " -S " + cmd};
//        System.err.println("构造复合命令:"+Arrays.toString(cmds));
        return cmds;
    }

    protected static String sudoCmd = "sudo";
    protected static String shellName = "/bin/bash";
    protected static String shellParam = "-c";

    /**
     * 执行命令
     *
     * @param cmds
     * @return
     * @throws IOException
     */
    public static List<String> executeCommands(String... cmds) throws IOException{
        Process process= null;
        CommandOutHandler outHandler = null;
        try {
            Runtime rt = Runtime.getRuntime();

            if(cmds.length == 1) {
                process = rt.exec(cmds[0]);
            }
            else {
                process = rt.exec(cmds);
            }

            outHandler = new CommandOutHandler(process.getErrorStream());
            outHandler.start();
            try{
                outHandler.join();
            }catch(Exception e){
//                System.err.println("等待命令行执行结果输出线程结束异常");
                e.printStackTrace();
            }
            List<String> msgList = outHandler.getMsgList();
            return msgList;
        } catch (IOException e) {
//            System.err.println("执行命令失败！正在停止进程和输出线程...");
            stop(outHandler);
            stop(process);
        }
        return Collections.emptyList();
    }

    public static boolean stop(Process process) {
        if (process != null) {
            process.destroy();
            return true;
        }
        return false;
    }

    public static boolean stop(Thread outHandler) {
        if (outHandler != null && outHandler.isAlive()) {
            outHandler.stop();
            outHandler.destroy();
            return true;
        }
        return false;
    }
}
