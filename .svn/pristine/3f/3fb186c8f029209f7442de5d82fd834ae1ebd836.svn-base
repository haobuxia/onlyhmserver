package com.tianyi.helmet.server.entity.data;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tianyi.helmet.server.entity.data.gpsenum.ActionTypeEnum;
import com.tianyi.helmet.server.entity.data.gpsenum.GpsCatagoryEnum;
import org.springframework.util.StringUtils;

/**
 * 车载gps终端动作数据
 * <p>
 * Created by liuhanc on 2017/10/23.
 */
public class GpsActionData extends AbstractGpsData {
    private int action;//动作指示 0-有动作，1-无动作  -99无数据
    private String actionVal;//动作类型       0-动臂上升        1-动臂下降       2-斗杆回收        3-斗杆伸出       4-铲斗挖掘       5-铲斗卸载       6-回转       7-行走
    private int walk;//行走类型 0-左右行走同时动作，1-左右行走其中一个动作  -99无数据

    public GpsCatagoryEnum getGpsCatagoryEnum() {
        return GpsCatagoryEnum.ACTION;
    }

    public int getAction() {
        return action;
    }

    public void setAction(int action) {
        this.action = action;
    }

    public String getActionVal() {
        return actionVal;
    }

    public void setActionVal(String actionVal) {
        this.actionVal = actionVal;
    }

    public int getWalk() {
        return walk;
    }

    public void setWalk(int walk) {
        this.walk = walk;
    }

    public String dataJson() {
        return "{action:" + action + ",actionVal:\"" + actionVal + "\",walk:" + walk + "}";
    }

    public static GpsActionData jsonToData(String dataJson) {
        GpsActionData actionData = new GpsActionData();
        JSONObject jo = JSON.parseObject(dataJson);
        actionData.setAction(jo.getInteger("action"));
        actionData.setActionVal(jo.getString("actionVal"));
        actionData.setWalk(jo.getInteger("walk"));
        return actionData;
    }

    public String toCnString() {
        if (action == 1) {
            return "无动作";
        }
        StringBuffer sb = new StringBuffer();
        if (!StringUtils.isEmpty(actionVal)) {
            for (int i = 0; i < actionVal.length(); i++) {
                int val = Integer.parseInt(actionVal.substring(i, i + 1));
                if(val == 1){
                    //该位置的动作存在
                    ActionTypeEnum actionTypeEnum = ActionTypeEnum.get(i);
                    if (actionTypeEnum != null) {
                        sb.append(actionTypeEnum.getName());
                    }
                }
            }
            sb.append(" ");
        }
        if (walk == 0) {
            sb.append(",同时动作");
        } else if (walk == 1) {
            sb.append(",1个动作");
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        return "动作数据：" + super.toString() + ".动作指示=" + action + ",动作类型=" + actionVal + ",行走类型=" + walk;
    }
}
