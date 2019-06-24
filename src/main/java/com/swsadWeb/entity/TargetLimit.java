package com.swsadWeb.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *  target_limit
 * @author wj 2019-06-23
 */
public class TargetLimit implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    /**
     * sex
     */
    private Integer sex;

    /**
     * grade
     */
    private Integer grade;

    /**
     * creditmin
     */
    private Integer creditMin;

    /**
     * group_id
     */
    private Long groupId;


    public TargetLimit() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public Integer getCreditMin() {
        return creditMin;
    }

    public void setCreditMin(Integer creditMin) {
        this.creditMin = creditMin;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

}
