package com.pinyougou.sellergoods.service.impl;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.ISelect;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pinyougou.common.pojo.PageResult;
import com.pinyougou.mapper.SellerMapper;
import com.pinyougou.pojo.Seller;
import com.pinyougou.sellergoods.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;


/**
 * 服务实现层
 * @author LEE.SIU.WAH
 * @email lixiaohua7@163.com
 * @date 2017年12月7日 下午1:57:51
 * @version 1.0
 */
@Service(interfaceName="com.pinyougou.sellergoods.service.SellerService")
@Transactional(readOnly=false)
public class SellerServiceImpl implements SellerService {


	/** 注入数据访问层代理对象 */
	@Autowired
	private SellerMapper sellerMapper;

	/** 添加商家 */
	public void saveSeller(Seller seller){
		try {
			//设置为未审核
			seller.setStatus("0");
			seller.setCreateTime(new Date());
			sellerMapper.insertSelective(seller);
		}catch (Exception ex){
			throw new RuntimeException(ex);
		}
	}

	/**
	 * 分页查询未审核的商家
	 * @param seller 商家实体
	 * @param page 当前页码
	 * @param rows 每页显示的记录数
	 * @return 分页结果
	 */
	public PageResult findByPage(Seller seller, Integer page, Integer rows){
		try {
            PageInfo<Seller> pageInfo = PageHelper.startPage(page, rows).doSelectPageInfo(new ISelect() {
                @Override
                public void doSelect() {
                    sellerMapper.findAll(seller);
                }
            });
            return new PageResult(pageInfo.getTotal(),pageInfo.getList());
		}catch (Exception ex){
			throw new RuntimeException(ex);
		}
	}

	/**
	 * 修改商家状态
	 * @param sellerId
	 * @param status
	 */
	public void updateStatus(String sellerId, String status){
		try {
            Seller seller = new Seller();
            seller.setSellerId(sellerId);
            seller.setStatus(status);
            sellerMapper.updateByPrimaryKeySelective(seller);
        }catch (Exception ex){
			throw new RuntimeException(ex);
		}
	}

	/** 根据sellerId查询商家对象 */
	public Seller findOne(String username){
		try {
			return sellerMapper.selectByPrimaryKey(username);
		}catch (Exception ex){
			throw new RuntimeException(ex);
		}
	}
}