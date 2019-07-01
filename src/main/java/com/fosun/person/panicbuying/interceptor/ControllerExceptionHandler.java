package com.fosun.person.panicbuying.interceptor;

import com.fosun.person.panicbuying.exception.ProductInfoException;
import com.fosun.person.panicbuying.vo.ResponseVo;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author: liumch
 * @create: 2019/6/28 17:50
 **/
@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ProductInfoException.class)
    public ResponseVo<String> handleProdException(final ProductInfoException ex){
        ResponseVo<String> vo = new ResponseVo<>();
        vo.setErrMsg(ex.getErrorMsg());
        vo.setErrCode(ex.getCode());
        return vo;

    }

}
