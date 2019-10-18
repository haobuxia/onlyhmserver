package com.tianyi.helmet.server.partner;


import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;


/**
 * 合作伙伴配置
 */
@Component
public class PartnerConfig {
    private Map<String, Partner> partners = new HashMap<>();

    @PostConstruct
    public void parseXml() {
        Partner tianyiHelmet = new Partner("头盔app客户端", "tianyi.helmet", "d4fa1741dcd0d5fda94a41bc98161bdc");
        partners.put(tianyiHelmet.getAppKey(), tianyiHelmet);
    }

    public Partner getPartner(String appKey) {
        return partners.get(appKey);
    }
}