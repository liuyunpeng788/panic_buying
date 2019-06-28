package com.fosun.person.panicbuying.service;

import com.fosun.person.panicbuying.exception.ProductInfoException;
import com.fosun.person.panicbuying.domain.ProductStore;
import com.fosun.person.panicbuying.mapper.ProductStoreMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * @author: liumch
 * @create: 2019/6/28 17:25
 **/
@Service
@Slf4j
public class OrderProductService {
    @Autowired
    private ProductStoreMapper productStoreMapper;

    public int order(int prodId,int orderNum) throws ProductInfoException{
        ProductStore storeInfo = productStoreMapper.findById(prodId);
        if(null == storeInfo){
            throw new ProductInfoException("获取库存信息失败,请稍后再试");
        }
        if(storeInfo.getNum() < orderNum){
            throw new ProductInfoException("库存不足，请减少订购数量后重新下单");
        }
        storeInfo.setNum(storeInfo.getNum() - orderNum);
        int res = productStoreMapper.updateEntity(storeInfo);
        String msg = res>0?"下单成功":"下单失败";
        log.info(msg);
        return res;

    }

}
