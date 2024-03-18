package com.linyuwei.service;

import com.linyuwei.pojo.Brand;
import com.linyuwei.pojo.PageBean;

import java.util.List;

public interface BrandService {
    List<Brand> selectAll();
    void add(Brand brand);
    Brand selectById(int id);
    void update(Brand brand);
    void deleteById(int id);
    void deleteByIds(int[] ids);
    PageBean<Brand> selectByPage(int currentPage,int pageSize);
    PageBean<Brand> selectByPageAndCondition(int currentPage,int pageSize,Brand brand);
}
