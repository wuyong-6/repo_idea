package com.wuy.service.impl;

import com.wuy.bean.Test;
import com.wuy.dao.TestMapper;
import com.wuy.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private TestMapper testMapper;

    @Override
    public List<Test> findAll() {
        return testMapper.findAll();
    }
}
