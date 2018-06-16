package com.pinyougou.sellergoods.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.ISelect;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pinyougou.common.pojo.PageResult;
import com.pinyougou.mapper.TypeTemplateMapper;
import com.pinyougou.pojo.TypeTemplate;
import com.pinyougou.sellergoods.service.TypeTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


/**
 * 服务实现层
 *
 * @author LEE.SIU.WAH
 * @email lixiaohua7@163.com
 * @date 2017年12月7日 下午2:00:03
 * @version 1.0
 */
@Service(interfaceName="com.pinyougou.sellergoods.service.TypeTemplateService")
@Transactional(readOnly=false)
public class TypeTemplateServiceImpl implements TypeTemplateService {
	
	//注入模板管理mapper
	@Autowired
	private TypeTemplateMapper typeTemplateMapper;
	/**
	 * 分页查询类型模版
	 * @param typeTemplate 模版实体
	 * @param page 当前页码
	 * @param rows 每页显示的记录数
	 * @return PageResult
	 */
	public PageResult findByPage(TypeTemplate typeTemplate,
								 Integer page, Integer rows){
		try{
			PageInfo<Object> pageInfo = PageHelper.startPage(page, rows).doSelectPageInfo(new ISelect() {
				@Override
				public void doSelect() {
					typeTemplateMapper.findAll(typeTemplate);
				}
			});
			return new PageResult(pageInfo.getTotal(),pageInfo.getList());
		}catch (Exception ex){
			throw new RuntimeException(ex);
		}
	}

	/** 添加类型模版 */
	public void saveTypeTemplate(TypeTemplate typeTemplate){
		try{
			typeTemplateMapper.insertSelective(typeTemplate);
		}catch (Exception ex){
			throw new RuntimeException(ex);
		}
	}

	/** 修改类型模版 */
	public void updateTypeTemplate(TypeTemplate typeTemplate){
		try{
			typeTemplateMapper.updateByPrimaryKeySelective(typeTemplate);
		}catch (Exception ex){
			throw new RuntimeException(ex);
		}
	}

	/** 删除类型模版 */
	public void deleteTypeTemplate(Long[] ids){
		try{
			typeTemplateMapper.deleteAll(ids);
		}catch (Exception ex){
			throw new RuntimeException(ex);
		}
	}


}