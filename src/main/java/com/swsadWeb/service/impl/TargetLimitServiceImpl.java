package com.swsadWeb.service.impl;

import com.swsadWeb.dao.TargetLimitDao;
import com.swsadWeb.entity.TargetLimit;
import com.swsadWeb.service.TargetLimitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TargetLimitServiceImpl implements TargetLimitService {

    @Autowired
    private TargetLimitDao targetLimitDao;

    @Override
    public void insertTargetLimit(TargetLimit targetLimit){
        targetLimitDao.insertTargetLimit(targetLimit);
    }

    @Override
    public void updateByTargetLimitId(TargetLimit targetLimit){
        targetLimitDao.updateByTargetLimitId(targetLimit);
    }
}
