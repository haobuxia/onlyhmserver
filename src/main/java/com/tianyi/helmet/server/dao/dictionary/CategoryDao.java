package com.tianyi.helmet.server.dao.dictionary;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


/**品类 字典表
 * Created by xiayuan on 2018/9/30.
 */
@Repository
public interface CategoryDao {
    List<Map<Integer, String>> selectCategory();
    List<Map<Integer, String>> selectCategory(@Param("categoryId") Integer categoryId);

}
