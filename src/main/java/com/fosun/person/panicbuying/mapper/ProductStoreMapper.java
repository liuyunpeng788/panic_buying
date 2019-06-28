package com.fosun.person.panicbuying.mapper;

import com.fosun.person.panicbuying.domain.ProductStore;
import com.sun.net.httpserver.Authenticator;
import org.apache.ibatis.annotations.*;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author: liumch
 * @create: 2019/6/28 14:35
 **/

@Mapper
public interface ProductStoreMapper {

    @Insert("INSERT INTO t_product_store_info (id, name,num, version) VALUES(#{id},#{name}, #{num}, #{version})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(ProductStore store);

    @Select("SELECT id, name,num, version FROM t_product_store_info WHERE id = #{id}")
    @Transactional(isolation=Isolation.READ_COMMITTED)
    ProductStore findById(long id);

    @Update("update t_product_store_info set num = #{num} ,version = #{version} + 1 where id = #{id} and version=#{version}")
    void update(int id,int num,int version);

     @Update("update t_product_store_info set num = #{num} ,version = #{version} + 1 where id = #{id} and version=#{version} ")
    int updateEntity(ProductStore store);
}
