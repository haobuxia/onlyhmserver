package com.tianyi.helmet.server.entity.file;

/**
 * 图片信息
 */
public class Image extends HelmetShot {
    private String imageType;//图片类型 @see ImageCategoryEnum

    @Override
    public UploadEntityTypeEnum getType() {
        return UploadEntityTypeEnum.image;
    }

    public String getImageType() {
        return imageType;
    }

    public void setImageType(String imageType) {
        this.imageType = imageType;
    }
}
