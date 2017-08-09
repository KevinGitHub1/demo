package com.zmk.cms.common.util;

public class TreeObject {
    private String id;
    private String name;
    private String parentId;
    private String type ;
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    //private String state = "0";
    private TreeObject[] children ;
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getParentId() {
        return parentId;
    }
    public void setParentId(String parentId) {
        this.parentId = parentId;
    }
    public TreeObject[] getChildren() {
        return children;
    }
    public void setChildren(TreeObject[] children) {
        this.children = children;
    }
    }

