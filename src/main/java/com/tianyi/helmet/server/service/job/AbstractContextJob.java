package com.tianyi.helmet.server.service.job;

import com.tianyi.helmet.server.entity.IdEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

/**
 *  随应用启动的自动循环获取数据并处理的job
 *
 * Created by liuhanc on 2017/10/17.
 */
public abstract class AbstractContextJob<T extends IdEntity> implements ApplicationListener<ContextRefreshedEvent> {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    protected JobRunFactory jobRunFactory;

    private boolean jobStarted = false;

    public void onApplicationEvent(ContextRefreshedEvent event){

        if(!needStartJob()){
            return;
        }

        if(jobStarted)
            return;

        logger.info("job onApplicationEvent.ContextRefreshedEvent happened.start job. source="+event.getSource()+",context="+event.getApplicationContext()+",time="+event.getTimestamp());
        jobStarted = true;

        new Thread(new Runnable() {
            @Override
            public void run() {
                startJob();
            }
        }).start();
    }

    /**
     * 处理步骤
     * 1 从数据库获取待处理数据列表
     * 2 循环处理
     * 3 处理完毕后再次从数据库获取列表
     * 4 如果数据库没有数据则等待xx秒后再次检查
     */
    public abstract void startJob();

    /**
     * 当前job是否启动
     * @return
     */
    public boolean thisJobStart(){
        return true;
    }

    protected boolean needStartJob(){
        Boolean jobRun = false;
        /*try{
            jobRun = (Boolean)jobRunFactory.getObject();
        }catch(Exception e){
        }

        if(jobRun == null || !jobRun.booleanValue()){
            return false;
        }*/

        if(!thisJobStart()){
            logger.debug("thisJobStart configed current job not start.");
            return false;
        }

        return true;
    }
}
