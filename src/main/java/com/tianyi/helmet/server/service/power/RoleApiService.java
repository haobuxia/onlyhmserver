package com.tianyi.helmet.server.service.power;

import com.tianyi.helmet.server.entity.power.RoleApi;

import java.util.List;
import java.util.Map;

/**
 * 角色接口权限信息
 * Created by wenxinyan on 2018/10/10.
 */
public interface RoleApiService {

    int insert(RoleApi roleApi);

    int deleteById(RoleApi roleApi);

    List<RoleApi> listAll();

    List<RoleApi> listByNoPage(Map<String, Object> param);

}
