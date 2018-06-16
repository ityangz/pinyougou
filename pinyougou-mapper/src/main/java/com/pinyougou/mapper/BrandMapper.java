package com.pinyougou.mapper;

import com.pinyougou.pojo.Brand;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 品牌数据访问接口
 * @author LEE.SIU.WAH
 * @email lixiaohua7@163.com
 * @date 2017年12月1日 下午5:08:25
 * @version 1.0
 */
public interface BrandMapper extends Mapper<Brand> {

    void deleteAll(@Param("ids") Long[] ids);

    List<Brand> findAll(@Param("brand") Brand brand);

    @Select("select id, name as text from tb_brand order by id asc")
    List<Map<String,Object>> findAllByIdAndName();
}