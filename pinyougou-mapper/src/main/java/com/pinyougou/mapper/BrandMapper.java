package com.pinyougou.mapper;

import com.pinyougou.pojo.Brand;

import java.util.List;

/**
 * 品牌数据访问接口
 *
 * @author lee.siu.wah
 * @version 1.0
 * <p>File Created at 2018-06-12<p>
 */
public interface BrandMapper {

    List<Brand> findAll();
}
