package com.fosun.person.panicbuying.aop;

import com.fosun.person.panicbuying.dbholder.DbHolderContext;
import com.fosun.person.panicbuying.enu.DBEnum;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Datasource aspect 拦截器
 * @author liumch
 * create :  2019/9/19 9:01
 **/
@Component
@Aspect
@Order(1)
public class DataSourceAspect {

    @Around("execution(* com.fosun.person.panicbuying.service..*(..))")
    public Object around(ProceedingJoinPoint pjp) throws Throwable{
        Class<?> cls = pjp.getTarget().getClass();
        System.out.println("class name :" + cls.getName());
        String methodName = pjp.getSignature().getName();
        Object[] args = pjp.getArgs();
        System.out.println("method name :" + methodName);
        System.out.println("args:");
        for (int i = args.length - 1; i >= 0; i--) {
            System.out.print(args[i] + " ");
        }
        return pjp.proceed();
    }


    /**
     * 第一个* 表示方法的返回类型
     */
    @Pointcut("!@annotation(com.fosun.person.panicbuying.aop.Master)"
            + "&& execution(* com.fosun.person.panicbuying..select*(..))"
            + "||execution(* com.fosun.person.panicbuying..get*(..))"
            + "||execution(* com.fosun.person.panicbuying..find*(..))"
            + "||execution(* com.fosun.person.panicbuying..query*(..))"
         )
    public void slavePointCut(){}



    @Pointcut("@annotation(com.fosun.person.panicbuying.aop.Master)"
            + "||execution(* com.fosun.person.panicbuying..insert*(..))"
            + "||execution(* com.fosun.person.panicbuying..put*(..))"
            + "||execution(* com.fosun.person.panicbuying..update*(..))"
            + "||execution(* com.fosun.person.panicbuying..save*(..))"
    )
    public void masterPointCut(){}

    @Before("masterPointCut()")
    public void master(){
        DbHolderContext.setDataSource(DBEnum.MASTER);
        System.out.println("主节点。。。。");
    }
    @Before("slavePointCut()")
    public void slave(){
        DbHolderContext.setDataSource(DBEnum.SLAVE);
        System.out.println("从节点。。。。");
    }
}
