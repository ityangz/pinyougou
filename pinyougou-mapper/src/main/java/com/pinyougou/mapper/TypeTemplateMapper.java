package com.pinyougou.mapper;


import com.pinyougou.pojo.TypeTemplate;

import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface TypeTemplateMapper extends Mapper<TypeTemplate> {


    List<TypeTemplate> findAll(@Param("typeTemplate") TypeTemplate typeTemplate);

    void deleteAll(Long[] ids);
}