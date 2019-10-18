package com.tianyi.helmet.server.dao.file;

import com.tianyi.helmet.server.entity.file.File;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 *  文件上传信息
 *
 */
@Repository
public interface FileDao {

	void insert(File file);

	File selectById(int id);

	int deleteById(int id);

	List<File> listBy(Map<String,Object> map);

	int countBy(Map<String,Object> map);

	int increaseViewCount(int id);
}
