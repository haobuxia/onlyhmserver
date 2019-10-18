package com.tianyi.helmet.server.service.tianyuan;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tianyi.helmet.server.util.Dates;
import com.tianyi.helmet.server.vo.QuadrupleVo;
import com.tianyi.svc.rest.entity.base.SvcIdEntity;
import io.swagger.annotations.ApiModelProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.persistence.Column;
import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 天远服务日志相关对象的json序列化反序列化工具
 * <p>
 * Created by liuhanc on 2018/3/28.
 */
@Component
public class TianYuanSvcJsonHelper<T extends SvcIdEntity> {

    private Logger logger = LoggerFactory.getLogger(TianYuanSvcJsonHelper.class);

    private Map<String, List<QuadrupleVo<String, String, Field, String>>> svcEntityClassFieldJsonMap = new HashMap<>();

    /**
     * 序列化，只序列化没有ignore的属性
     * 用实体类属性对应的列名作为key，而不是实体类属性值
     *
     * @param t
     * @return
     */
    public String toJson(T t) {
        Class tClass = t.getClass();
        List<QuadrupleVo<String, String, Field, String>> fieldList = getClassFieldList(tClass);
        JSONObject jsonObject = new JSONObject(fieldList.size());
        fieldList.stream().forEach(vo -> {
            String jsonKey = vo.getTwo();
            Field field = vo.getThree();
            String fieldVal = getFieldVal(field, t);
            jsonObject.put(jsonKey, fieldVal);
        });
        return jsonObject.toJSONString();
    }

    //反序列化解析.所有字段都反序列化
    public void parse(String json, T t) {
        JSONObject jsonObject = JSON.parseObject(json);
        parse(jsonObject, t);
    }

    /**
     * 反序列化.所有字段都反序列化
     *
     * @param json
     * @param t
     */
    public void parse(JSONObject json, T t) {
        Map<String, String> jsonKvData = json.keySet().stream().collect(Collectors.toMap(key -> key.toLowerCase(), key -> json.getString(key)));

        Class tClass = t.getClass();
        List<QuadrupleVo<String, String, Field, String>> fieldList = getClassFieldList(tClass);
        fieldList.stream().forEach(vo -> {
            String jsonKey = vo.getTwo();
            Field field = vo.getThree();
            String fieldVal = jsonKvData.get(jsonKey);
            setFieldVal(field, t, fieldVal);
        });
    }

    //获得各个属性的信息 fieldName, jsonKey, field, cnName
    public List<QuadrupleVo<String, String, Field, String>> getClassFieldList(Class<T> tClass) {
        String clsName = tClass.getSimpleName();
        if (!svcEntityClassFieldJsonMap.containsKey(clsName)) {
            synchronized (svcEntityClassFieldJsonMap) {
                if (!svcEntityClassFieldJsonMap.containsKey(clsName)) {
                    Field[] fields = tClass.getDeclaredFields();
                    List<QuadrupleVo<String, String, Field, String>> list = Arrays.stream(fields).map(field -> {
//                        if (field.getName().equals("id")) {
//                            return null;
//                        }
                        field.setAccessible(true);
                        String fieldName = field.getName();
                        Column column = field.getAnnotation(Column.class);
                        String jsonKey = column.name().toLowerCase();
//                        JsonIgnore ignoreAnno = field.getAnnotation(JsonIgnore.class);
//                        boolean ignore = ignoreAnno != null;
//                        if (ignore) {
//                            return null;
//                        }
                        ApiModelProperty apiModelProperty = field.getAnnotation(ApiModelProperty.class);
                        String fieldCnName = fieldName;
                        if (apiModelProperty != null) {
                            fieldCnName = apiModelProperty.value();
                        }
                        return new QuadrupleVo<>(fieldName, jsonKey, field, fieldCnName);
                    }).filter(qo -> qo != null).collect(Collectors.toList());
                    svcEntityClassFieldJsonMap.put(clsName, list);

                    return list;
                }
            }
        }
        return svcEntityClassFieldJsonMap.get(clsName);
    }

    protected String getFieldVal(Field f, T t) {
        try {
            Object obj = f.get(t);
            if (obj == null) return "";
            Class type = f.getType();
            if (type.getName().equals("java.util.Date")) {
                return Dates.format((Date) obj, "yyyy-MM-dd'T'HH:mm:ss.SSS");
            }
            return obj.toString();
        } catch (Exception e) {
            logger.error("获取属性值异常." + f.getName(), e);
            return "";
        }
    }

    protected void setFieldVal(Field f, T t, String val) {
        if (StringUtils.isEmpty(val))
            return;
        try {
            String typeName = f.getType().getName();//Long,Double,Integer,Float,Date,String
            Object valObj = null;
            if (typeName.equals("java.lang.String")) {
                valObj = val;
            } else {
                val = val.trim();
                if (val.endsWith(".0"))
                    val = val.substring(0, val.length() - 2);//天远json传过来的值以.0结尾，去掉末尾的.0
                if (typeName.equals("java.util.Date")) {
                    valObj = Dates.parse(val, "yyyy-MM-dd'T'HH:mm:ss.SSS");
                } else if (typeName.equals("java.lang.Long")) {
                    valObj = Long.valueOf(val);
                } else if (typeName.equals("java.lang.Integer")) {
                    valObj = Integer.valueOf(val);
                } else if (typeName.equals("java.lang.Float")) {
                    valObj = Float.valueOf(val);
                } else if (typeName.equals("java.lang.Double")) {
                    valObj = Double.valueOf(val);
                } else {
                    logger.error("未预料到的属性类型.fieldName=" + f.getName() + ",type=" + typeName);
                }
            }
            f.set(t, valObj);
        } catch (Exception e) {
            logger.error("设置属性值异常." + f.getName(), e);
        }
    }
}
