package com.tianyi.helmet.server.service.tianyuan;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tianyi.helmet.server.controller.interceptor.TianYuanUserHolder;
import com.tianyi.helmet.server.dao.scene.WorkOrderDao;
import com.tianyi.helmet.server.entity.client.TianYuanUser;
import com.tianyi.helmet.server.entity.scene.svc.WorkOrder;
import com.tianyi.helmet.server.entity.tianyuan.VclInfo;
import com.tianyi.helmet.server.service.client.TianYuanUserComponent;
import com.tianyi.helmet.server.service.support.ConfigService;
import com.tianyi.helmet.server.util.Commons;
import com.tianyi.helmet.server.vo.*;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.*;

/**
 * 天远相关服务
 * <p>
 * Created by liuhanc on 2018/1/23.
 */
@Service
public class TianYuanService {
    @Autowired
    private TianYuanApiBasicHelper tianYuanApiBasicHelper;
    @Autowired
    private TianYuanUserComponent tianYuanUserComponent;
    @Autowired
    private ConfigService configService;
    @Autowired
    private WorkOrderDao workOrderDao;


    private Logger logger = LoggerFactory.getLogger(TianYuanService.class);

    /**
     * 零配件接口调用  旧二维码
     * `
     *
     * @param serviceName
     * @param request
     * @return
     */
    public JSONObject partServiceInvoke(String serviceName, HttpServletRequest request) {
        String requestJson = null;
        int contentLength = request.getContentLength();
        if (contentLength > 0) {
            try {
                byte[] body = new byte[contentLength];
                IOUtils.readFully(request.getInputStream(), body);
                requestJson = new String(body, "utf-8");
            } catch (Exception e) {
                throw new TianYuanException("读取天远接口请求数据异常." + request.getRequestURL().toString());
            }
        }
        return partServiceInvoke(serviceName, requestJson);
    }

    /**
     * 零配件接口调用 新
     *
     * @param serviceName
     * @param request
     * @return
     */
    public ResponseVo<Map<String, Object>> partServiceInvokeTY(String serviceName, HttpServletRequest request, String orderNo) {
        logger.debug("partServiceInvokeTY调用成功1");
        Map<String, String[]> map = request.getParameterMap();
        String requestJson = JSON.toJSONString(map);
        return partServiceInvokeTY(serviceName, orderNo, requestJson);
    }

    /**
     * 零配件接口调用  新
     *
     * @param serviceName
     * @param orderNo
     * @param requestJson
     * @return
     */
    public ResponseVo<Map<String, Object>> partServiceInvokeTY(String serviceName, String orderNo, String requestJson) {
        logger.debug("partServiceInvokeTY调用成功2");
        String authorization = null;
        boolean useProdEnv = Arrays.stream(configService.getTianYuanPartServiceProdServiceNames()).filter(srvName -> serviceName.equalsIgnoreCase(srvName)).findAny().isPresent();
        if (useProdEnv) {
            TianYuanUser tianYuanUser = tianYuanUserComponent.getSpecialUser();
            logger.debug("天远零件接口调用.接口=" + serviceName + ",使用天远生产环境=" + useProdEnv + ",对应的天远用户账号=" + tianYuanUser.getUsername());
            authorization = tianYuanUser.getFullToken();
        } else {
            authorization = TianYuanUserHolder.getFullToken();
        }
        String partServieUrl = useProdEnv ? configService.getTianYuanPartServiceProdUrl() : "http://tyapitest.tygps.com/v1/ZK/";
        //生产环境
//        String partServieUrl = configService.getTianYuanPartServiceProdUrl();
        String url = Commons.concatUrl(partServieUrl, serviceName);
        String codeMsg = "条形码";
        String codeType = "1";
        logger.debug("requestJson:" + requestJson);
        //对条形码和二维码做区分
        // TODO: 2018/8/13 如何判断二维码
        /*if (requestJson.contains("二维码")) {
            // TODO: 2018/8/10 二维码的处理
            codeMsg = "二维码";
            codeType = "2";
        }*/

        //拿到返回值需要进行本地数据库操作
        //对工单号和零件号以及零件名称进行关联绑定


        // 拼接请求天远的字符串
        JSONObject jsonRequest = JSONObject.parseObject(requestJson);
        List<String> partNoList = (List) jsonRequest.get("partNo");
        String partNo = partNoList.get(0);
        logger.debug("partNo:" + partNo);
        String partNoNew = partNo;
        int index = partNo.indexOf(".");
        if (index == -1) {
            partNoNew = "01." + partNo;
            logger.debug("partNoNew:" + partNoNew);
        }
        String requestJsonNew = "{\"partNo\":\"" + partNoNew + "\"}";

        //根据工单号查对应机型
        WorkOrder workOrder = workOrderDao.selectByOrderNo(orderNo);
        String model = workOrder.getModel();

        logger.debug("requestJsonNew:" + requestJsonNew);
        //解析得到的json字符串，封装对象
        logger.debug("开始，请求天远地址url：" + url);
        JSONObject resultJson = tianYuanApiBasicHelper.postJson(url, requestJsonNew, authorization, "json");
        logger.debug("调用天远接口结束");
        List<JSONObject> list = (List) resultJson.get("data");

        ResultVo resultVo = new ResultVo();
        List<PartInfoList> list2 = new ArrayList<>();
        List<InventoryList> list3 = new ArrayList<>();
        JSONObject o = list.get(0);
        JSONArray partInfoList1 = o.getJSONArray("partInfoList");
        JSONArray inventoryList1 = o.getJSONArray("inventoryList");
        Map<String, String> map = new HashMap<>();
        List<PartsList> list1 = new ArrayList<>();
        for (int i = 0; i < partInfoList1.size(); i++) {

            //创建要封装的对象
            PartInfoList partInfoList = new PartInfoList();
            list1 = new ArrayList<>();
            JSONObject jsonPart = (JSONObject) partInfoList1.get(i);

            //PartInfoList对象的封装
            String vclType = jsonPart.get("vclType").toString();
            if (!model.equals(vclType)) {
                continue;
            }
            partInfoList.setVclType(vclType);
            JSONArray partsList1 = jsonPart.getJSONArray("partsList");

            //PartsList对象的封装
            for (int j = 0; j < partsList1.size(); j++) {

                PartsList partsList = new PartsList();
                JSONObject jsonObject = (JSONObject) partsList1.get(j);
                String sysOneName = jsonObject.get("sysOneName").toString();
                String sysTwoName = jsonObject.get("sysTwoName").toString();
                String image = jsonObject.get("image").toString();
                String partName = jsonObject.get("partName").toString();
                String partPrice = jsonObject.get("partPrice").toString();
                partsList.setImage(image);
                partsList.setSysOneName(sysOneName);
                partsList.setPartName(partName);
                partsList.setPartPrice(partPrice);
                partsList.setSysTwoName(sysTwoName);
                list1.add(partsList);
                if (StringUtils.isEmpty(map.get("partName"))) {
                    map.put("partName", partName);
                }
            }
            map.put("codeMsg", codeMsg);
            map.put("codeType", codeType);
            map.put("partNo", partNoNew);
            map.put("orderNo", orderNo);


            partInfoList.setPartsList(list1);
            list2.add(partInfoList);
            break;
        }


        //InventoryList对象的封装
        String warehouseNames = "";
        for (int i = 0; i < inventoryList1.size(); i++) {
            InventoryList inventoryList = new InventoryList();
            JSONObject jsonObject = (JSONObject) inventoryList1.get(i);
            String warehouseName = jsonObject.get("warehouseName").toString();
            String inventoryQty = jsonObject.get("inventoryQty").toString();
            inventoryList.setInventoryQty(inventoryQty);
            inventoryList.setWarehouseName(warehouseName);
            list3.add(inventoryList);
            if (i == 0) {
                warehouseNames = warehouseName;
                continue;
            }
            warehouseNames = warehouseNames + "," + warehouseName;//英文的逗号
        }
        map.put("warehouseName", warehouseNames);
        //插入数据库
        int rs = workOrderDao.insertRel(map);
        if (!(rs > 0)) {
            logger.debug("方法调用结束失败");
            return ResponseVo.fail("操作数据库失败");
        }

        resultVo.setInventoryList(list3);
        resultVo.setPartInfoList(list2);

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("inventoryList", resultVo.getInventoryList());

        resultMap.put("partsList", list1);
        resultMap.put("vclType",model);

        //以上过程完成ResultVo对象的封装
        logger.debug("方法调用结束");
        return ResponseVo.success(resultMap);

    }

    /**
     * 零配件接口调用  旧二维码
     *
     * @param serviceName
     * @param requestJson
     * @return
     */
    public JSONObject partServiceInvoke(String serviceName, String requestJson) {
        //天远零件接口的基本数据获取调用天远线上环境，下单购买调用天远线下环境
        boolean useProdEnv = Arrays.stream(configService.getTianYuanPartServiceProdServiceNames()).filter(srvName -> serviceName.equalsIgnoreCase(srvName)).findAny().isPresent();
        String authorization = null;
        if (useProdEnv) {
            TianYuanUser tianYuanUser = tianYuanUserComponent.getSpecialUser();
            logger.debug("天远零件接口调用.接口=" + serviceName + ",使用天远生产环境=" + useProdEnv + ",对应的天远用户账号=" + tianYuanUser.getUsername());
            authorization = tianYuanUser.getFullToken();
        } else {
            authorization = TianYuanUserHolder.getFullToken();
        }
        String partServieUrl = useProdEnv ? configService.getTianYuanPartServiceProdUrl() : configService.getTianYuanPartServiceUrl();
        logger.debug("天远零件接口调用.接口=" + serviceName + ",使用天远生产环境=" + useProdEnv + ",零件接口地址=" + partServieUrl);
        String url = Commons.concatUrl(partServieUrl, serviceName);
        JSONObject json = tianYuanApiBasicHelper.postJson(url, requestJson, authorization, "json");
        return json;
    }

    /**
     * 服务日志接口调用-form参数
     *
     * @param serviceName
     * @param params
     * @return
     */
    public JSONObject svcServiceInvoke(String serviceName, Map<String, ? extends Object> params) {
        String url = Commons.concatUrl(configService.getTianYuanSvcServiceUrl(), serviceName);

        String fileKey = params.keySet().stream().filter(key -> {
            Object val = params.get(key);
            if (val != null && val instanceof File) {
                return true;
            }
            return false;
        }).findFirst().orElse(null);

        JSONObject json = null;
        if (!StringUtils.isEmpty(fileKey)) {
            //存在文件上传
            File file = (File) params.remove(fileKey);
            json = tianYuanApiBasicHelper.postUploadForm(url, params, fileKey, file, null, "xml");
        } else {
            json = tianYuanApiBasicHelper.postForm(url, params, null, "xml", true);
        }
        return json;
    }


    /**
     * 根据蓝牙盒子的imei得到车辆信息
     *
     * @param imei
     * @return
     */
    public VclInfo getVclInfoByImei(String imei) {
        JSONObject param = new JSONObject();
        param.put("imei", imei);
        JSONObject jo = this.partServiceInvoke("getVclInfo", param.toJSONString());
        if (jo != null) {
            VclInfo info = new VclInfo();
            info.setVcl_no(jo.getString("vcl_no"));
            info.setVcl_brand(jo.getString("vcl_brand"));
            info.setVcl_type(jo.getString("vcl_type"));
            info.setVcl_producedate(jo.getString("vcl_producedate"));
            info.setVcl_saledate(jo.getString("vcl_saledate"));
            info.setClnt_name(jo.getString("clnt_name"));
            info.setClnt_mobile(jo.getString("clnt_mobile"));
            info.setClnt_adrs(jo.getString("clnt_adrs"));
            return info;
        }
        return null;
    }

}
