package com.pinyougou.sellergoods.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.ISelect;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pinyougou.common.pojo.PageResult;
import com.pinyougou.mapper.BrandMapper;
import com.pinyougou.pojo.Brand;
import com.pinyougou.sellergoods.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 指定接口名返回服务名
 */
@Service(interfaceName = "com.pinyougou.sellergoods.service.BrandService")
@Transactional
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandMapper brandMapper;

    @Override
    public List<Brand> findAll() {
        //使用分页插件   开启分页
        PageInfo<Brand> pageInfo = PageHelper.startPage(1, 10).doSelectPageInfo(new ISelect() {
            @Override
            public void doSelect() {
                brandMapper.selectAll();
            }
        });

        return pageInfo.getList();
    }

    @Override
    public PageResult findByPage(Brand brand,int pageNum, int pageSize) {
        PageResult pageResult = null;
        try {
            PageInfo<Brand> pageInfo = PageHelper.startPage(pageNum, pageSize).doSelectPageInfo(new ISelect() {
                @Override
                public void doSelect() {
                   brandMapper.findAll(brand);
                }
            });
            pageResult = new PageResult();
            pageResult.setTotal(pageInfo.getTotal());
            pageResult.setRows(pageInfo.getList());
        } catch (Exception e) {
           throw new RuntimeException(e);
        }
        return pageResult;
    }

    @Override
    public void saveBrand(Brand brand) {
        try {
            brandMapper.insertSelective(brand);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateBrand(Brand brand) {
        try {
            brandMapper.updateByPrimaryKeySelective(brand);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteBrand(Long[] ids) {
        try {
            brandMapper.deleteAll(ids);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    /** 查询所有的品牌*/
    @Override
    public List<Map<String, Object>> findBrandByIdAndName() {
        try {
            return brandMapper.findAllByIdAndName();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
