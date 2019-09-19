package com.fosun.person.panicbuying.controller;

import com.fosun.person.panicbuying.service.OrderProductService;
import com.fosun.person.panicbuying.vo.ResponseVo;
import com.fosun.person.panicbuying.vo.requestVo.OrderVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author: created by aimy
 * date: 2019/6/29 19:07
 */

@RestController
@RequestMapping(value="/order",method = RequestMethod.POST)
public class ProductionOrderController {
    @Autowired
    private OrderProductService orderProductService;


    @ResponseBody
    @PostMapping("/{id}")
    public ResponseVo<String> buyProduct(@PathVariable("id") Integer id,@RequestBody OrderVo vo){
//        int num = 1;
        int res = orderProductService.update(id,vo.getNum());
        String msg = res>0?"下单成功":"下单失败";
        return new ResponseVo<>(msg);
    }


}
