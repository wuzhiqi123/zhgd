package com.zhgd.pojo;

import java.io.Serializable;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * 左侧菜单栏
 * @author wuyudong
 *
 */
public class TreeResult implements Serializable{
	private Long id;
    private String path;
    private String state = "open";// open,closed
    private String name ;
    private boolean checked = false;
    private Object attributes;
    @JsonInclude(Include.NON_NULL)
    private List<TreeResult> children; // null不输出
    private String icon;
    private Long pid;
    private String description;
    //右边显示标签(默认都添加)
    private String targetType = null;
    /**
     * ajax,iframe,
     */
    private String openMode;

    public String getName(){
        return name;
    }
    public void setName(String  name) {
        this.name = name;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
    
    public void setState(Integer opened) {
        this.state = (null != opened && opened == 1) ? "open" : "closed";
    }
    
    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public Object getAttributes() {
        return attributes;
    }

    public void setAttributes(Object attributes) {
        this.attributes = attributes;
    }

    public List<TreeResult> getChildren() {
        return children;
    }

    public void setChildren(List<TreeResult> children) {
        this.children = children;
    }

    public String getIconCls() {
        return icon;
    }

    public void setIconCls(String icon) {
        this.icon = icon;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public String getOpenMode() {
        return openMode;
    }

    public void setOpenMode(String openMode) {
        this.openMode = openMode;
    }

	public String getTargetType() {
		return targetType;
	}

	public void setTargetType(String targetType) {
		this.targetType = targetType;
	}


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
