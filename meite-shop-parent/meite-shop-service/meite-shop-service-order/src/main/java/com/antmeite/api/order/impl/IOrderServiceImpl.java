/**
 * 功能说明:
 * 功能作者:
 * 创建日期:
 * 版权归属:每特教育|蚂蚁课堂所有 www.itmayiedu.com
 */
package com.antmeite.api.order.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.antmeite.api.order.IOrderService;
import com.antmeite.code.base.BaseApiService;
import com.antmeite.code.base.ResponseBase;
import com.antmeite.feign.StockFeign;
import com.antmeite.mapper.OrderMapper;
import com.codingapi.tx.annotation.TxTransaction;
//import com.codingapi.tx.annotation.TxTransaction;
import com.itmayeidu.api.entity.OrderEntity;

@RestController
public class IOrderServiceImpl extends BaseApiService implements IOrderService {
	@Autowired
	private OrderMapper orderMapper;
	@Autowired
	private StockFeign stockFeign;

	// 下单扣库存
	@TxTransaction(isStart = true)
	@Transactional
	@GetMapping(value = "/addOrderAndStock")
	public ResponseBase addOrderAndStock(int i) throws Exception {
		OrderEntity orderEntity = new OrderEntity();
		orderEntity.setName("牙刷");
		orderEntity.setOrderCreatetime(new Date());
		// 价格是300元
		orderEntity.setOrderMoney(300d);
		// 状态为 未支付
		orderEntity.setOrderState(0);
		Long commodityId = 30l;
		// 商品id
		orderEntity.setCommodityId(commodityId);
		// 1.先下单，创建订单
		int orderResult = orderMapper.addOrder(orderEntity);
		System.out.println("orderResult:" + orderResult);
		// 2.下单成功后,调用库存服务
		ResponseBase inventoryReduction = stockFeign.inventoryReduction(commodityId);
		if (inventoryReduction.getRtnCode() != 200) {
			// 1.使用手动事务 -
			// TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			// 2.获取将异常抛出给上一层，外面回滚。
			throw new Exception("调用库存服务接口失败，开始回退订单事务代码");
		}
		int reuslt = 1 / i;
		return setResultSuccess("下单成功!");
	}

}
