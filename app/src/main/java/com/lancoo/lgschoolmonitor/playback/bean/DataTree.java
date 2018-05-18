package com.lancoo.lgschoolmonitor.playback.bean;

import java.util.List;

/**
 * File description.
 *
 * @author Hinata-Liu
 * @date 2018/5/17 19:16.
 */
public class DataTree<K, V> {
    private K groupItem;
    private List<V> subItems;

    public DataTree(K groupItem, List<V> subItems) {
        this.groupItem = groupItem;
        this.subItems = subItems;
    }

    public K getGroupItem() {
        return groupItem;
    }

    public List<V> getSubItems() {
        return subItems;
    }
}
