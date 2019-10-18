package com.tianyi.helmet.server.service.dictionary;

import com.tianyi.helmet.server.entity.dictionary.CompanyNature;

import java.util.List;

/**
 * 单位性质 字典表
 * Created by wenxinyan on 2018/9/30.
 */
public interface CompanyNatureSerivce {

    List<CompanyNature> listAll();

    CompanyNature selectById(int id);

}
