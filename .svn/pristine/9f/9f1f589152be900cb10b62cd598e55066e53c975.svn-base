package com.tianyi.helmet.server.service.client;

import com.tianyi.helmet.server.entity.client.Company;
import com.tianyi.helmet.server.entity.client.User;

import java.util.List;
import java.util.Map;

/**
 * 单位管理服务
 * Created by wenxinyan on 2018/9/30.
 */
public interface CompanyService {

    int insert(Company company);

    int deleteById(int id);

    int update(Company company);

    List<Company> listBy(Map<String, Object> param);

    int countBy(Map<String, Object> param);

    Company selectById(int id);

    List<Company> listByNoPage(Map<String, Object> param);

    void clearByMobileCache(String mobile);



}
