package com.tianyi.helmet.server.util;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Process输出处理器
 *
 * Created by liuhanc on 2017/10/17.
 */
public class CommandOutHandler extends Thread {
    /**
     * 控制状态
     */
    private volatile boolean desstatus = true;

    /**
     * 读取输出流
     */
    private BufferedReader br = null;

    /**
     * 输出消息列表
     */
    private List<String> msgList = null;

    public CommandOutHandler(InputStream is) {
        br = new BufferedReader(new InputStreamReader(is));
        this.msgList = new ArrayList<>();
    }

    /**
     * 重写线程销毁方法，安全的关闭线程
     */
    @Override
    public void destroy() {
        setDesStatus(false);
    }

    public void setDesStatus(boolean desStatus) {
        this.desstatus = desStatus;
    }

    public List<String> getMsgList(){
        return msgList;
    }

    /**
     * 执行输出线程
     */
    @Override
    public void run() {
        String msg = null;
        try {
            if(true){
                while (desstatus && (msg = br.readLine()) != null) {
                    msgList.add(msg);
                }
            }else{
                Thread.yield();
            }
        } catch (IOException e) {
            System.out.println("发生内部异常错误，命令执行结果监听线程自动关闭[" + this.getId() + "]线程");
            destroy();
        } finally {
            if (this.isAlive()) {
                destroy();
            }
        }
    }
}
