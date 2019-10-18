package com.tianyi.helmet.server.util;

import com.tianyi.helmet.server.entity.power.MenuDTO;
import org.apache.poi.ss.formula.functions.T;

import java.util.ArrayList;
import java.util.List;

public class TreeUtil {
    /**
     * 使用递归方法建树
     *
     * @param treeNodes
     * @return
     */
    public static List<MenuDTO> buildByRecursive(List<MenuDTO> treeNodes, int root) {
        List<MenuDTO> trees = new ArrayList<MenuDTO>();
        for (MenuDTO treeNode : treeNodes) {
            if (root == treeNode.getFatherId()) {
                trees.add(findChildren(treeNode, treeNodes));
            }
        }
        return trees;
    }

    /**
     * 递归查找子节点
     *
     * @param treeNodes
     * @return
     */
    public static MenuDTO findChildren(MenuDTO treeNode, List<MenuDTO> treeNodes) {
        for (MenuDTO it : treeNodes) {
            if (treeNode.getId() == it.getFatherId()) {
                if (treeNode.getChildren() == null) {
                    treeNode.setChildren(new ArrayList<MenuDTO>());
                }
                treeNode.add(findChildren(it, treeNodes));
            }
        }
        return treeNode;
    }
}
