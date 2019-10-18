package com.tianyi.helmet.server.service.dictionary.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tianyi.helmet.server.entity.dictionary.DwfDicModel;
import com.tianyi.helmet.server.service.dictionary.DwfDicService;
import com.tianyi.helmet.server.service.tianyuan.TianYuanIntesrvApiHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by tianxujin on 2019/2/1 16:08
 */
@Service
public class DwfDicServiceImpl implements DwfDicService {
    @Autowired
    private TianYuanIntesrvApiHelper tianYuanIntesrvApiHelper;

    @Override
    public List<DwfDicModel> listBrand() {
        JSONObject jsonObj = tianYuanIntesrvApiHelper.getJson("BrandList", new JSONObject());
        JSONArray jsonAry = jsonObj.getJSONArray("data");
        if(jsonAry == null) {
            return null;
        }
        List<DwfDicModel> result = JSONObject.parseArray(jsonAry.toJSONString(), DwfDicModel.class);
        return result;
    }

    @Override
    public List<DwfDicModel> listMachineTypeByBrand(String id) {
        JSONObject jsonObj = tianYuanIntesrvApiHelper.getJson("DeviceTypeList", new JSONObject());
        JSONArray jsonAry = jsonObj.getJSONArray("data");
        if(jsonAry == null) {
            return null;
        }
        List<DwfDicModel> result = JSONObject.parseArray(jsonAry.toJSONString(), DwfDicModel.class);
        return result;
    }

    @Override
    public List<DwfDicModel> listMachineModelByMachineType(String id) {
        JSONObject jsonObj = tianYuanIntesrvApiHelper.getJson("DeviceModelList", new JSONObject());
        JSONArray jsonAry = jsonObj.getJSONArray("data");
        if(jsonAry == null) {
            return null;
        }
        List<DwfDicModel> result = JSONObject.parseArray(jsonAry.toJSONString(), DwfDicModel.class);
        return result;
    }
}
