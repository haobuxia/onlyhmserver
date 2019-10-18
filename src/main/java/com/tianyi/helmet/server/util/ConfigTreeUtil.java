package com.tianyi.helmet.server.util;

import com.tianyi.helmet.server.entity.client.HelmetConfigInfo;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

public class ConfigTreeUtil {
    /**
     * 使用递归方法建树
     *
     * @param treeNodes
     * @return
     */
    public static List<HelmetConfigInfo> buildByRecursive(List<HelmetConfigInfo> treeNodes, int root) {
        List<HelmetConfigInfo> trees = new ArrayList<HelmetConfigInfo>();
        for (HelmetConfigInfo treeNode : treeNodes) {
            if (root == treeNode.getPid()) {
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
    public static HelmetConfigInfo findChildren(HelmetConfigInfo treeNode, List<HelmetConfigInfo> treeNodes) {
        for (HelmetConfigInfo it : treeNodes) {
            if (treeNode.getId() == it.getPid()) {
                if (treeNode.getChildren() == null) {
                    treeNode.setChildren(new ArrayList<HelmetConfigInfo>());
                }
                treeNode.add(findChildren(it, treeNodes));
            }
        }
        return treeNode;
    }
}
