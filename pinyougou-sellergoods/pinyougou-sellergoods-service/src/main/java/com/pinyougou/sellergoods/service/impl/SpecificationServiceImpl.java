package com.pinyougou.sellergoods.service.impl;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.ISelect;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pinyougou.common.pojo.PageResult;
import com.pinyougou.mapper.SpecificationMapper;
import com.pinyougou.mapper.SpecificationOptionMapper;
import com.pinyougou.pojo.Specification;
import com.pinyougou.pojo.SpecificationOption;
import com.pinyougou.sellergoods.service.SpecificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 规格服务接口实现层
 * @author LEE.SIU.WAH
 * @email lixiaohua7@163.com
 * @date 2017年12月7日 下午1:58:29
 * @version 1.0
 */
@Service(interfaceName="com.pinyougou.sellergoods.service.SpecificationService")
@Transactional(readOnly=false)
public class SpecificationServiceImpl implements SpecificationService {
	//注入specificationMapper
	@Autowired
	private SpecificationMapper specificationMapper;
	@Autowired
    private SpecificationOptionMapper specificationOptionMapper;
	/**
	 * 分页查询规格
	 * @param specification 规格实体
	 * @param page 当前页码
	 * @param rows 每页显示的记录数
	 * @return PageResult
	 */
	public PageResult findByPage(Specification specification,
								 Integer page, Integer rows){
		try{
            PageInfo<Object> pageInfo = PageHelper.startPage(page, rows).doSelectPageInfo(new ISelect() {
                @Override
                public void doSelect() {
                    specificationMapper.findAll(specification);
                }
            });
            PageResult pageResult = new PageResult(pageInfo.getTotal(), pageInfo.getList());
            return pageResult;
		}catch (Exception ex){
			throw new RuntimeException(ex);
		}
	}
	/** 添加规格与规格选项 */
	public void saveSpecification(Specification specification){
		try{
		    //保存规格表
			specificationMapper.insertSelective(specification);
			//保存规格选项表
            specificationMapper.save(specification);

		}catch (Exception ex){
			throw new RuntimeException(ex);
		}
	}

	/** 根据规格id查询规格选项 */
	public List<SpecificationOption> findOne(Long id){
		try{
			return specificationMapper.findOne(id);
		}catch (Exception ex){
			throw new RuntimeException(ex);
		}
	}

    /** 修改规格与规格选项  */
    public void updateSpecification(Specification specification){
        try{
           //修改规格表
            specificationMapper.updateByPrimaryKeySelective(specification);
            //修改规格选项表
            List<SpecificationOption> specificationOptions = specification.getSpecificationOptions();
            if(specificationOptions!=null && specificationOptions.size()>0){
                for (SpecificationOption specificationOption :specificationOptions){
                    specificationOptionMapper.updateByPrimaryKeySelective(specificationOption);
                }
            }
        }catch (Exception ex){
            throw new RuntimeException(ex);
        }
    }

    /** 删除规格与规格选项 */
    public void deleteSpecification(Long[] ids){
        try{
            //ids存的是规格的id
            for(Long id : ids){
                //先删除规格选项
                SpecificationOption specificationOption = new SpecificationOption();
                specificationOption.setSpecId(id);
                specificationOptionMapper.delete(specificationOption);
                //再删除规格
                specificationMapper.deleteByPrimaryKey(id);
            }


        }catch (Exception ex){
            throw new RuntimeException(ex);
        }
    }

	/** 查询规格列表(id,name) */
	public List<Map<String,Object>> findSpecByIdAndName(){
		try{
			return specificationMapper.findSpecificationByIdAndName();
		}catch (Exception ex){
			throw new RuntimeException(ex);
		}
	}
}