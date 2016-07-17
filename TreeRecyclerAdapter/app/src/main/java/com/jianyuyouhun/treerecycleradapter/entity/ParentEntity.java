package com.jianyuyouhun.treerecycleradapter.entity;

import java.util.List;

/**
 * Created by 剑雨丶游魂 on 2016/7/18.
 */
public class ParentEntity {
    private int id;
    private String name;
    private List<ChildEntity> children;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ChildEntity> getChildren() {
        return children;
    }

    public void setChildren(List<ChildEntity> children) {
        this.children = children;
    }


    public static class ChildEntity{
       private int id;
       private String name;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
