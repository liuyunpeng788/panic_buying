package com.fosun.person.panicbuying.exception;

/**
 * @author: liumch
 * @create: 2019/6/28 17:37
 **/

public class ProductInfoException extends RuntimeException {
    static final long serialVersionUID = -703489719074576693L;
    /**
     * 错误码
     */
    private int code;
    /**
     * 错误信息
     */
    private String errorMsg;

    public ProductInfoException(int code,String errorMsg){
        super(errorMsg);
        this.code = code;
        this.errorMsg = errorMsg;
    }

    public ProductInfoException(String errorMsg){
        super(errorMsg);
        this.code = 4001;
        this.errorMsg = errorMsg;
    }
}
