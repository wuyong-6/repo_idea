package com.wuy.dao;

import com.wuy.bean.Test;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface TestMapper {
    public List<Test> findAll();
}
