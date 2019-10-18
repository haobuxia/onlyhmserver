package com.tianyi.helmet.server.service.job;

import com.tianyi.helmet.server.service.support.ConfigService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.File;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

/**
 * 随系统启动的任务，任务是否运行的开关工厂
 *
 * Created by liuhanc on 2017/10/10.
 */
@Component
public class JobRunFactory<Boolean> implements FactoryBean,InitializingBean {
    private org.slf4j.Logger logger = LoggerFactory.getLogger(JobRunFactory.class);
    @Autowired
    private ConfigService configService;
    /**
     * 任务调度器是否启动
     */
    private boolean jobSchdulerStart = false;

    /**
     * 根据配置判断是否启动job调度器
     *
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        logger.debug("enablefile="+configService.getJobEnableFile());
        logger.debug("enableip="+configService.getJobEnableIp());

        if(!StringUtils.isEmpty(configService.getJobEnableFile())){
            jobSchdulerStart = new File(configService.getJobEnableFile()).exists();
        }

        if(!jobSchdulerStart && !StringUtils.isEmpty(configService.getJobEnableIp())){
            Enumeration<NetworkInterface> allNetInterfaces = NetworkInterface.getNetworkInterfaces();
            InetAddress ip = null;
            while (allNetInterfaces.hasMoreElements()){
                NetworkInterface netInterface = allNetInterfaces.nextElement();
                Enumeration<InetAddress> addresses = netInterface.getInetAddresses();
                while (addresses.hasMoreElements()){
                    ip = addresses.nextElement();
                    if (ip != null && ip instanceof Inet4Address && ip.getHostAddress() != null){
                        if(ip.getHostAddress().equals(configService.getJobEnableIp())){
                            jobSchdulerStart = true;
                            break;
                        }
                    }
                }
            }
        }

        logger.debug("jobSchdulerStart = "+jobSchdulerStart);
    }

    @Override
    public Object getObject() throws Exception {
        return jobSchdulerStart;
    }

    @Override
    public Class<?> getObjectType() {
        return boolean.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
