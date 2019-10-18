package com.tianyi.helmet.server.controller.dictionary;

import com.tianyi.helmet.server.entity.dictionary.City;
import com.tianyi.helmet.server.entity.dictionary.CompanyNature;
import com.tianyi.helmet.server.entity.dictionary.DwfDicModel;
import com.tianyi.helmet.server.entity.dictionary.Province;
import com.tianyi.helmet.server.service.dictionary.*;
import com.tianyi.helmet.server.service.tianyuan.TianYuanIntesrvApiHelper;
import com.tianyi.helmet.server.vo.ResponseVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.tianyi.helmet.server.service.dictionary.DwfDicService;

import java.util.List;
import java.util.Map;

/**
 * 字典表
 * Created by wenxinyan on 2018/9/30.
 */
@Controller
@RequestMapping("dictionary")
@ResponseBody
public class DictionaryController {
    private Logger logger = LoggerFactory.getLogger(DictionaryController.class);
    @Autowired
    private ProvinceService provinceService;
    @Autowired
    private CityService cityService;
    @Autowired
    private CompanyNatureSerivce companyNatureService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private VersionService versionService;
    @Autowired
    private StatusService statusService;
    @Autowired
    private BatchService batchService;
    @Autowired
    private  ModelService modelService;

    @Autowired
    private DwfDicService dwfDicService;

    @RequestMapping("province")
    public ResponseVo listProvince(){
        List<Province> provinceList = provinceService.listAll();

        return ResponseVo.success(provinceList);
    }

    @RequestMapping("city")
    public ResponseVo listCity(@RequestParam Integer id){
        List<City> cityList = cityService.listByProvinceId(id);

        return ResponseVo.success(cityList);
    }

    @RequestMapping("allcity")
    public ResponseVo listAllCity(){
        List<City> cityList = cityService.listAll();

        return ResponseVo.success(cityList);
    }

    @RequestMapping("companynature")
    public ResponseVo listCompanyNature(){
        List<CompanyNature> companyNatureList = companyNatureService.listAll();

        return ResponseVo.success(companyNatureList);
    }

    /**
     * 查询状态字典表
     *
     * @return
     */
    @RequestMapping(value = "device/status", method = RequestMethod.POST)
    @ResponseBody
    public ResponseVo getStatus() {
        logger.debug("getStatus调用");
        List<Map<Integer, String>> map = statusService.selectStatus();
        return ResponseVo.success(map);
    }

    /**
     * 查询品类字典表
     *
     * @return
     */
    @RequestMapping(value = "device/category", method = RequestMethod.POST)
    @ResponseBody
    public ResponseVo getCategory() {
        logger.debug("getCategory调用");
        List<Map<Integer, String>> map = categoryService.selectCategory();
        return ResponseVo.success(map);
    }

    /**
     * 查询版本字典表
     *
     * @return
     */
    @RequestMapping(value = "device/version", method = RequestMethod.POST)
    @ResponseBody
    public ResponseVo getVersion() {
        logger.debug("getVersion调用");
        List<Map<Integer, String>> map = versionService.selectVersion();
        return ResponseVo.success(map);
    }
    /**
     * 查询批次字典表
     *
     * @return
     */
    @RequestMapping(value = "device/batch", method = RequestMethod.POST)
    @ResponseBody
    public ResponseVo getBatch() {
        logger.debug("getBatch调用");
        List<Map<Integer, String>> map = batchService.selectBatch();
        return ResponseVo.success(map);
    }

    /**
     * 查询型号字典表
     *
     * @return
     */
    @RequestMapping(value = "device/model", method = RequestMethod.POST)
    @ResponseBody
    public ResponseVo getModel() {
        logger.debug("getModel");
        List<Map<Integer, String>> map = modelService.selectModel();
        return ResponseVo.success(map);
    }

    @RequestMapping(value = "brand", method = RequestMethod.POST)
    public ResponseVo listBrand(){
        List<DwfDicModel> list = dwfDicService.listBrand();
        return ResponseVo.success(list);
    }

    @RequestMapping(value = "listMachineTypeByBrand", method = RequestMethod.POST)
    public ResponseVo listMachineTypeByBrand(@RequestParam String id){
        List<DwfDicModel> list = dwfDicService.listMachineTypeByBrand(id);
        return ResponseVo.success(list);
    }

    @RequestMapping(value = "listMachineModelByMachineType", method = RequestMethod.POST)
    public ResponseVo listMachineModelByMachineType(@RequestParam String id){
        List<DwfDicModel> list = dwfDicService.listMachineModelByMachineType(id);
        return ResponseVo.success(list);
    }

}
