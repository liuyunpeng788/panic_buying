package com.fosun.person.panicbuying.conf;

import com.alibaba.druid.pool.DruidDataSource;
import com.fosun.person.panicbuying.dbsource.CustomDataSource;
import com.fosun.person.panicbuying.enu.DBEnum;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;


/**
 * @author liumch
 * create :  2019/9/19 13:54
 **/
@Configuration
public class DataSourceConfig {

    @Bean("master")
    @ConfigurationProperties(prefix = "spring.datasource.druid-master")
    public DataSource masterDataSource(){
        return new DruidDataSource();
    }

    @Bean("slave")
    @ConfigurationProperties(prefix = "spring.datasource.druid-slave")
    public DataSource slaveDataSource(){
        return new DruidDataSource();
    }

    /**
     * 设置动态数据源,通过@Primary 来确定主DataSource
     * @param master 主库
     * @param slave 从库
     * @return 自定义的数据源
     */
    @Bean
    @Primary
    public CustomDataSource createDataSource(@Qualifier("master") DataSource master,@Qualifier("slave")DataSource slave){
        CustomDataSource customDataSource = new CustomDataSource();
        customDataSource.setDefaultTargetDataSource(master);
        Map<Object,Object> map = new HashMap<>(2);
        map.put(DBEnum.MASTER,master);
        map.put(DBEnum.SLAVE,slave);
        customDataSource.setTargetDataSources(map);
         return customDataSource ;
    }


}
