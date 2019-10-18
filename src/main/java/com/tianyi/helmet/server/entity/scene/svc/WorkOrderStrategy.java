package com.tianyi.helmet.server.entity.scene.svc;

import com.tianyi.helmet.server.entity.IdEntity;

/**
 * 工单中头盔自动执行的动作策略配置
 * <p>
 * Created by liuhanc on 2018/7/5.
 */
public class WorkOrderStrategy extends IdEntity {
    private String orderNo;//工单号
    private int priority;//优先级，数字越大越优先执行
    private String eventType;//事件类型 @see StrategyEventTypeEnum
    /**
     * 事件的具体定义。
     * TIME_INTERVAL("间隔时间触发") 此处可存3MIN：表示每3分钟执行1次，存5DAY：表示每5天执行1次
     * TIME("到时间触发"), 此处可存9:30：表示时间到了9:30分钟执行1次，存 2018-09-12 12:00:35表示时间在这个时刻执行1次
     * EVENT("发生事件触发"), 此处可存:SCAN表示扫码时触发,LOGIN表示登录时触发
     * POSITION("位置触发"); 此处可存116,38表示经纬度在这个值时触发，116,38,500表示经纬度在116,38周围500米范围内就触发
     * <p>
     * 更多可能情况需细化，此处仅仅举例子
     */
    private String eventVal;//事件值

    private String actionType;//执行的动作的类型 @see StrategyActionTypeEnum

    /**
     * VIDEO("拍摄视频")，可存储时长
     * IMAGE("拍摄照片"),
     * AUDIO("录音"),可存储时长
     * UPLOAD("上传"),上传？？
     * LINK("激活链接"); 存入 链接地址
     * <p>
     *
     * 更多可能情况需细化，此处仅仅举例子
     */
    private String actionVal;//动作值

    private String enventTypeName;//不入库,事件类型中文名
    private String actionTypeName;//不入库,动作类型中文名


    public String getActionType() {
        return actionType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    public String getEnventTypeName() {
        return enventTypeName;
    }

    public void setEnventTypeName(String enventTypeName) {
        this.enventTypeName = enventTypeName;
    }

    public String getActionTypeName() {
        return actionTypeName;
    }

    public void setActionTypeName(String actionTypeName) {
        this.actionTypeName = actionTypeName;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getEventVal() {
        return eventVal;
    }

    public void setEventVal(String eventVal) {
        this.eventVal = eventVal;
    }

    public String getActionVal() {
        return actionVal;
    }

    public void setActionVal(String actionVal) {
        this.actionVal = actionVal;
    }
}
