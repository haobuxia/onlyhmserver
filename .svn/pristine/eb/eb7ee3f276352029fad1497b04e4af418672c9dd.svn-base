package com.tianyi.helmet.server.service.data;

import com.tianyi.helmet.server.dao.data.HelmetStateDao;
import com.tianyi.helmet.server.entity.data.HelmetState;
import com.tianyi.helmet.server.vo.PageListVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * 头盔状态数据服务
 *
 * Created by liuhanc on 2017/10/17.
 */
@Service
public class HelmetStateService {
    @Autowired
    private HelmetStateDao helmetStateDao;

    public void insert(HelmetState state){
        helmetStateDao.insert(state);
    }

    public HelmetState selectById(int id){
        return helmetStateDao.selectById(id);
    }

    public List<HelmetState> selectBy(Map<String,Object> params){
        List<HelmetState> list = helmetStateDao.selectBy(params);
        return list;
    }


    public PageListVo<HelmetState> selectByClientId(String clientId, LocalDateTime createTime1, LocalDateTime createTime2, int page, int pageSize, boolean withCount){
        PageListVo<HelmetState> vo = new PageListVo<HelmetState>();
        vo.setPage(page);
        vo.setPageSize(pageSize);

        Map<String,Object> map = PageListVo.createParamMap(page,pageSize);
        map.put("clientId",clientId);
        map.put("createTime1",createTime1);
        map.put("createTime2",createTime2);
        map.put("order",1);

        if(withCount){
            int count = helmetStateDao.countBy(map);
            vo.setTotal(count);
            if(count > 0 ){
                List<HelmetState> list = helmetStateDao.selectBy(map);
                vo.setList(list);
            }
        }else{
            List<HelmetState> list = helmetStateDao.selectBy(map);
            vo.setList(list);
        }
        return vo;
    }
}
