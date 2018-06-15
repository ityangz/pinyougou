package com.pinyougou.sellergoods.service;

import com.pinyougou.common.pojo.PageResult;
import com.pinyougou.pojo.Brand;

import java.util.List;

/**
 * 品牌service
 */
public interface BrandService {
    List<Brand> findAll();

    //分页查询
    PageResult findByPage(Brand brand,int pageNum,int pageSize);

    //添加
    void saveBrand(Brand brand);

    /** 修改品牌 */
    void updateBrand(Brand brand);

    //批量删除品牌
    void deleteBrand(Long[] ids);

}
