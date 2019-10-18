package com.tianyi.helmet.server.service.data;

import com.tianyi.helmet.server.dao.data.ReferenceValueDao;
import com.tianyi.helmet.server.entity.data.ReferenceValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wenxinyan on 2018/9/8.
 */
@Service
public class ReferenceValueService {
    @Autowired
    private ReferenceValueDao referenceValueDao;

    public Map<String, Object> selectByModel(String model) {
        List<ReferenceValue> referenceValues = referenceValueDao.selectByModel(model);
        Map<String, Object> map1 = new HashMap<>();
        Map<String, Object> map2 =  new HashMap<>();
        Map<String, Object> map3 = new HashMap<>();
        Map<String, Integer> map4 = new HashMap<>();
        map1.put("model", model);
        String action = referenceValues.get(0).getAction();

        for(ReferenceValue rv : referenceValues)
        {
            if(action.equals(rv.getAction()))
            {
                map4 = new HashMap<>();
                map4.put("stateUp", rv.getStateUp());
                map4.put("stateLower", rv.getStateLower());
                map3.put(rv.getState(), map4);
            }
            else
            {
                map2.put(action, map3);
                map3 = new HashMap<>();
                map4 = new HashMap<>();
                map4.put("stateUp", rv.getStateUp());
                map4.put("stateLower", rv.getStateLower());
                map3.put(rv.getState(), map4);
                action = rv.getAction();
            }
        }
        map2.put(action, map3);
        map1.put("param", map2);

        return map1;
    }
}
