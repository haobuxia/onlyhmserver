package com.tianyi.helmet.service.baidu;

import com.tianyi.helmet.BaseServiceTest;
import com.tianyi.helmet.server.service.baidu.ImageClassifyService;
import com.tianyi.helmet.server.util.image.ImageUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.FileInputStream;

/**
 * Created by liuhanc on 2017/12/25.
 */
public class ImageClassifyServiceTest extends BaseServiceTest {

    @Autowired
    private ImageClassifyService imageClassifyService;

    @Test
    public void testDetect() throws Exception{
        String img="d:\\img.jpg";
        FileInputStream fis = new FileInputStream(img);
        byte[] bytes = ImageUtils.readToBytes(fis);
        imageClassifyService.detectCar(bytes);
    }

    @Test
    public void testLogo() throws Exception{
        String img="d:\\img.jpg";
        FileInputStream fis = new FileInputStream(img);
        byte[] bytes = ImageUtils.readToBytes(fis);
        imageClassifyService.detectLogo(bytes);
    }
}
