package com.tmw.tree;

import java.util.Date;
import java.util.Set;

public class TreeNode {

    private String id;
    private String name;
    private String code;
    private String parentCode;
    private String B0194;
    private Integer SORTID = 9999999;
    private Date createTime;
    private String B0114;
    private String b01Z101;
    private Set<TreeNode> children;
    private String hasSub;

    public TreeNode(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public TreeNode setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public TreeNode setName(String name) {
        this.name = name;
        return this;
    }

    public String getCode() {
        return code;
    }

    public TreeNode setCode(String code) {
        this.code = code;
        return this;
    }

    public String getParentCode() {
        return parentCode;
    }

    public TreeNode setParentCode(String parentCode) {
        this.parentCode = parentCode;
        return this;
    }

    public String getB0194() {
        return B0194;
    }

    public TreeNode setB0194(String b0194) {
        B0194 = b0194;
        return this;
    }

    public Integer getSORTID() {
        return SORTID;
    }

    public TreeNode setSORTID(Integer SORTID) {
        this.SORTID = SORTID;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public TreeNode setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public String getB0114() {
        return B0114;
    }

    public TreeNode setB0114(String b0114) {
        B0114 = b0114;
        return this;
    }

    public String getB01Z101() {
        return b01Z101;
    }

    public TreeNode setB01Z101(String b01Z101) {
        this.b01Z101 = b01Z101;
        return this;
    }

    public Set<TreeNode> getChildren() {
        return children;
    }

    public TreeNode setChildren(Set<TreeNode> children) {
        this.children = children;
        return this;
    }

    public String getHasSub() {
        return hasSub;
    }

    public TreeNode setHasSub(String hasSub) {
        this.hasSub = hasSub;
        return this;
    }
}
