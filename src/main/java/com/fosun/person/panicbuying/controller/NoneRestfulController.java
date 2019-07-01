package com.fosun.person.panicbuying.controller;

import com.fosun.person.panicbuying.vo.ResponseVo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: created by aimy
 * date: 2019/6/29 23:28
 */

@RestController
public class NoneRestfulController {
    @RequestMapping(value = "/normal",method = RequestMethod.GET)
    @ResponseBody
    public ResponseVo<String> testResponse(){
        return new ResponseVo<>("in test normal...");
    }
}
