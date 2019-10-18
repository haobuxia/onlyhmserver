package com.tianyi.helmet.server.dao.power;

import com.tianyi.helmet.server.entity.power.Menu;
import com.tianyi.helmet.server.entity.power.MenuDTO;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 功能点信息
 * Created by wenxinyan on 2018/10/10.
 */
@Repository
public interface MenuDao {

    List<Menu> listAll();

    Menu selectById(int id);

    List<Menu> listByNoPage(Map<String, Object> param);

    List<MenuDTO> treeList(Map<String, Object> param);

    List<MenuDTO> roleTreeList(Map<String, Object> param);
}
