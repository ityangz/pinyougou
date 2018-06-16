package com.pinyougou.manager.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.pinyougou.common.pojo.PageResult;
import com.pinyougou.pojo.Brand;
import com.pinyougou.sellergoods.service.BrandService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

//@Controller
@RestController
@RequestMapping("/brand")
public class BrandController {

    //注入service服务
    //   因为在bean配置了 <dubbo:annotation package="com.pinyougou.manager.controller"/>  所以需要加reference
    @Reference
    private BrandService brandService;

    /**
     * 查询所有的品牌
     */
    @GetMapping("/selectBrandList")
    public List<Map<String,Object>> selectBrandList(){
        return brandService.findBrandByIdAndName();
    }

    /**
     * 分页查询
     * @param page  当前页
     * @param rows   每页大小
     * @return
     */
    /*@RequestMapping("/findAll")
    @ResponseBody*/
    @GetMapping("/findAll")
    public PageResult findAll(Brand brand,int page,int rows) throws Exception {
        /** GET请求中文转码 */
        if (brand != null && StringUtils.isNoneBlank(brand.getName())) {
            brand.setName(new String(brand.getName().getBytes("ISO-8859-1"),"UTF-8"));
        }
        PageResult PageResult  = brandService.findByPage(brand,page, rows);
        return PageResult;
    }

    /**
     * 添加
     * @param brand
     * @return
     */
    @PostMapping("/save")
    public boolean save(@RequestBody Brand brand){
        try {
            brandService.saveBrand(brand);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    /**
     * 修改
     */
    @PostMapping("/update")
    public boolean update(@RequestBody Brand brand){
        try {
            brandService.updateBrand(brand);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    /**
     * 批量删除品牌
     */
    @GetMapping("/delete")
    public boolean deleteAll(Long[] ids){
        try {
            brandService.deleteBrand(ids);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
