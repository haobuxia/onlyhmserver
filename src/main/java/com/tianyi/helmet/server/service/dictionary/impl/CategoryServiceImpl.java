package com.tianyi.helmet.server.service.dictionary.impl;

import com.tianyi.helmet.server.dao.dictionary.CategoryDao;
import com.tianyi.helmet.server.entity.dictionary.City;
import com.tianyi.helmet.server.service.dictionary.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 品类 字典表
 * Created by xiayuan on 2018/9/30.
 */
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryDao categoryDao;
    @Override
    public List<Map<Integer, String>> selectCategory() {
        return categoryDao.selectCategory();
    }
    @Override
    public List<Map<Integer, String>> selectCategory(int id) {
        return categoryDao.selectCategory(id);
    }
}
