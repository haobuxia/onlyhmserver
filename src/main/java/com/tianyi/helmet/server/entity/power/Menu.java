package com.tianyi.helmet.server.entity.power;

import com.tianyi.helmet.server.entity.IdEntity;

import java.time.LocalDateTime;

/**
 * 功能点信息
 * Created by wenxinyan on 2018/10/10.
 */
public class Menu extends IdEntity {
    private LocalDateTime createTime;
    private String menuName;
    private int fatherId;
    private String menuPage;  //页面信息
    private String menuImage;  //图片或图标信息
    private int menuType;  //标识网页、头盔、APP
    private Integer defaultMenu; //默认打开1

    public Integer getDefaultMenu() {
        return defaultMenu;
    }

    public void setDefaultMenu(Integer defaultMenu) {
        this.defaultMenu = defaultMenu;
    }

    public String getMenuImage() {
        return menuImage;
    }

    public void setMenuImage(String menuImage) {
        this.menuImage = menuImage;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public int getFatherId() {
        return fatherId;
    }

    public void setFatherId(int fatherId) {
        this.fatherId = fatherId;
    }

    public String getMenuPage() {
        return menuPage;
    }

    public void setMenuPage(String menuPage) {
        this.menuPage = menuPage;
    }

    public int getMenuType() {
        return menuType;
    }

    public void setMenuType(int menuType) {
        this.menuType = menuType;
    }
}
