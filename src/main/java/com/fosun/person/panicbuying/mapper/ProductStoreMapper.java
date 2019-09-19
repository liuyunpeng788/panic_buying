package com.fosun.person.panicbuying.mapper;

import com.fosun.person.panicbuying.domain.ProductStore;
import org.apache.ibatis.annotations.*;

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
    ProductStore findById(long id);

    /**
     * 采用悲观锁更新
     * 适用于写多读少的场景
     * @param id id 主键
     * @param num 数量
     */
    @Update("update t_product_store_info set num = #{num} ,version = #{version} + 1 where id = #{id} for update")
    void pessimisticUpdate(int id,int num );

    /**
     * 采用乐观锁进行更新
     * 适用于写少读多的场景，且冲突率小于20%
     * @param store 商品信息
     * @return 更新的结果
     */
     @Update("update t_product_store_info set num = #{num} ,version = #{version} + 1 where id = #{id} and version=#{version} ")
    int updateEntity(ProductStore store);
}
