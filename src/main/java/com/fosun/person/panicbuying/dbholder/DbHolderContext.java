package com.fosun.person.panicbuying.dbholder;

import com.fosun.person.panicbuying.enu.DBEnum;

/**
 * 通过threadlocal 持有db 的信息
 * @author liumch
 * create :  2019/9/19 10:22
 **/

public class DbHolderContext {
    private final static ThreadLocal<DBEnum> threadLocal = new ThreadLocal<>();

    public static void setDataSource(DBEnum dbEnum){
        threadLocal.set(dbEnum);
    }

    public static DBEnum get(){
        return threadLocal.get();
    }


}
