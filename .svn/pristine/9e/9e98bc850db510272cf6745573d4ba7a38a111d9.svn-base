package com.tianyi.helmet.server.entity.power;

import java.util.ArrayList;
import java.util.List;

public class MenuDTO {
    private int id;
    private String menuName;
    private int fatherId;
    private List<MenuDTO> children = new ArrayList<MenuDTO>();

    public void add(MenuDTO node) {
        children.add(node);
    }

    public List<MenuDTO> getChildren() {
        return children;
    }

    public void setChildren(List<MenuDTO> children) {
        this.children = children;
    }

    public MenuDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
}
