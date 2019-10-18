package com.tianyi.helmet.server.dao.client;

import com.tianyi.helmet.server.entity.client.Company;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 单位信息
 * Created by wenxinyan on 2018/9/30.
 */
@Repository
public interface CompanyDao {

    int insert(Company company);

    int deleteById(int id);

    int update(Company company);

    List<Company> listBy(Map<String, Object> param);

    int countBy(Map<String, Object> param);

    Company selectById(int id);

    List<Company> listByNoPage(Map<String, Object> param);

}
