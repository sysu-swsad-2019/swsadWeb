package com.swsadWeb.dao;

import com.swsadWeb.entity.TargetLimit;

public interface TargetLimitDao {

    void insertTargetLimit(TargetLimit targetLimit);

    void updateByTargetLimitId(TargetLimit targetLimit);
}
