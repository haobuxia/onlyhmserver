package com.tianyi.helmet.server.entity.svc;

/**
 * 服务工单图像
 *
 * Created by liuhanc on 2018/3/16.
 */
public class SvcImage extends SvcResAbstract {
    //@see SvcImageTypeEnum
    private String imageType;//imageType jihao:机号照片

    public SvcImage() {
    }

    @Override
    public SvcResTypeEnum getResType() {
        return SvcResTypeEnum.image;
    }

    @Override
    public String getResInnerTypeName() {
        if(imageType == null || imageType.length() == 0) return null;
        return SvcImageTypeEnum.valueOf(imageType).getCnName();
    }

    public String getImageType() {
        return imageType;
    }

    public void setImageType(String imageType) {
        this.imageType = imageType;
    }

    @Override
    public String toString() {
        return "rwh=" + rwh + ",imageType=" + imageType + ",imagePath=" + getOssPath();
    }
}
