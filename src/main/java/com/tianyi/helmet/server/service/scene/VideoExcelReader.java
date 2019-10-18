package com.tianyi.helmet.server.service.scene;

import com.tianyi.helmet.server.util.Dates;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 视频信息excel读取工具
 * <p>
 * 视频和指定工况数据间的对应关系，杨俊超会提供一个标准Excel表格，
 * 表格内容为：头盔号、TYBOX号、视频通话开始时间、视频通话结束时间、车辆型号、工地类型、工作类型。
 * 注：车辆型号、工地类型、工作类型三个属性与当前功能无关。
 * <p>
 * Created by liuhanc on 2018/7/25.
 */
@Component
public class VideoExcelReader {

    public List<VideoExcelDataPo> readExcel(InputStream is) throws IOException {
        Workbook workbook = null;
        //支持2003和2007版excel
        try {
            workbook = new XSSFWorkbook(is);
        } catch (Exception ex) {
            workbook = new HSSFWorkbook(is);
        }

        //获取工作表
        Sheet sheet = workbook.getSheetAt(0);
        //获取行,行号作为参数传递给getRow方法,第一行从0开始计算
//        HSSFRow row = sheet.getRow(0);//标题行忽略
        int lastRowNo = sheet.getLastRowNum();

        List<VideoExcelDataPo> dataList = new ArrayList<>();
        for (int i = 1; i <= lastRowNo; i++) {
            Row dataRow = sheet.getRow(i);//标题行忽略
            if (dataRow == null)
                continue;
            Cell cell0 = dataRow.getCell(0);
            Cell cell1 = dataRow.getCell(1);
            Cell cell2 = dataRow.getCell(2);
            Cell cell3 = dataRow.getCell(3);

            if (cell0 == null || cell1 == null || cell2 == null || cell3 == null) {
                throw new RuntimeException("数据不能为空.行号:" + (i + 1));
            }

            String neUsername = readCellValue(cell0);
            String jh = readCellValue(cell1);
            String time1 = readCellValue(cell2);
            String time2 = readCellValue(cell3);

            if (StringUtils.isEmpty(neUsername) || StringUtils.isEmpty(jh) || time1 == null || time2 == null) {
                throw new RuntimeException("数据不能为空.行号:" + (i + 1));
            }

            Date date1 = Dates.parse(time1, "yyyyMMddHHmmss");
            Date date2 = Dates.parse(time2, "yyyyMMddHHmmss");
            if (date1 == null || date2 == null || date1.after(date2) || date1.equals(date2)) {
                throw new RuntimeException("起止时间有误.行号:" + (i + 1));
            }

            dataList.add(new VideoExcelDataPo(neUsername, jh, date1, date2));
        }
        workbook.close();
        is.close();
        return dataList;
    }

    private String readCellValue(Cell cell) {
        cell.setCellType(CellType.STRING);
        return cell.getStringCellValue();
    }
}
