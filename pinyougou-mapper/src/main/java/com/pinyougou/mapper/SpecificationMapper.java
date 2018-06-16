package com.pinyougou.mapper;


import com.pinyougou.pojo.Specification;
import com.pinyougou.pojo.SpecificationOption;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface SpecificationMapper extends Mapper<Specification>{


    List<Specification> findAll(@Param("specification") Specification specification);

    //添加规格选项
    void save(Specification specification);

    //根据规格id查询规格选项
    @Select("select * from tb_specification_option"
            + " where spec_id = #{id} order by orders asc")
    List<SpecificationOption> findOne(Long id);

    //查询所有的规格
    @Select("select id, spec_name as text from tb_specification order by id asc")
    List<Map<String,Object>> findSpecificationByIdAndName();
}