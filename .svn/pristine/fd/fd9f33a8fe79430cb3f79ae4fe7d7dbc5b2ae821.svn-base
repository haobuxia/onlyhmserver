package com.tianyi.helmet.server.service.dictionary;

import com.tianyi.helmet.server.entity.dictionary.DwfDicModel;

import java.util.List;

/**
 * DWF获取基础字典表
 * Created by tianxujin on 2019/2/1.
 */
public interface DwfDicService {
    /**
     * 获取品牌列表
     * @return
     */
    List<DwfDicModel> listBrand();
    /**
     * 依据品牌获取机型列表
     * @return
     */
    List<DwfDicModel> listMachineTypeByBrand(String id);
    /**
     * 依据机型获取机号列表
     * @return
     */
    List<DwfDicModel> listMachineModelByMachineType(String id);

}
