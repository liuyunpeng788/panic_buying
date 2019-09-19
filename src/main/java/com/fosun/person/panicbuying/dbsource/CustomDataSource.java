package com.fosun.person.panicbuying.dbsource;

import com.fosun.person.panicbuying.dbholder.DbHolderContext;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 自定义数据源
 * @author liumch
 * create :  2019/9/19 10:54
 **/

public class CustomDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        return DbHolderContext.get();
    }
}
