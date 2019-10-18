package com.tianyi.helmet.server.util;
import java.io.File;
import java.net.URL;

import org.apache.commons.io.FileUtils;
/**
 * Created by tianxujin on 2019/8/3 11:34
 */
public class DownloadURLFile {
    /**
     * @param args
     */
    public static void main(String[] args) {
        String res = downloadFromUrl("http://192.168.22.43:58081/records/video/2019/08/20/4109E3E8-5AF1-4157-952A-11FEFBA78BC2_1.mp4","d:/");
        System.out.println(res);
    }

    public static String downloadFromUrl(String url,String dir) {
        String filePath = "";
        try {
            URL httpurl = new URL(url);
            String fileName = getFileNameFromUrl(url);
            System.out.println(fileName);
            filePath = dir + fileName;
            File f = new File(dir + fileName);
            FileUtils.copyURLToFile(httpurl, f);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return filePath;
    }

    public static String getFileNameFromUrl(String url){
        String name = new Long(System.currentTimeMillis()).toString() + ".X";
        int index = url.lastIndexOf("/");
        if(index > 0){
            name = url.substring(index + 1);
            if(name.trim().length()>0){
                return name;
            }
        }
        return name;
    }
}
