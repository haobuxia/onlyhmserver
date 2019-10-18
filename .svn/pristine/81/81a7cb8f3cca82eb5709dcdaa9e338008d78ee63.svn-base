function rotateImage() {
    if (!browser.versions.iPhone) {
        return;
    }

    console.debug('iphone手机，开始检查图片旋转方向');
    var img = $("#displayDiv>img");
    EXIF.getData(img[0], function () {
        var orient = EXIF.getTag(this, 'Orientation');
        console.debug('图片旋转方向orient=' + orient);
        /**
         * 0°    1
         顺时针90°    6
         逆时针90°    8
         180°    3
         */
        if (orient == 6)
            img.css("transform", "rotate(-90deg)");
        else if (orient == 8)
            img.css("transform", "rotate(90deg)");
        else if (orient == 3)
            img.css("transform", "rotate(-180deg)");
    });
}

$(function () {
    rotateImage();
});