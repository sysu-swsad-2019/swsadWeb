package com.swsadWeb.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *  group
 * @author wj 2019-06-23
 */
public class Group implements Serializable {
    private static final long serialVersionUID = 1L;


    private Long id;

    /**
     * name
     */
    private String name;

    /**
     * description
     */
    private String description;


    public Group() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}