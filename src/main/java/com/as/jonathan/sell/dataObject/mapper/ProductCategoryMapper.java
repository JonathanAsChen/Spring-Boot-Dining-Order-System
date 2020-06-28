package com.as.jonathan.sell.dataObject.mapper;

import com.as.jonathan.sell.dataObject.ProductCategory;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * @author Yifan Chen
 * @version 1.0.0
 * @since 6/24/2020 8:59 PM
 */
public interface ProductCategoryMapper {

	@Insert("insert into product_category(category_name, category_type) values (#{category_name, jdbcType=VARCHAR}, #{category_type, jdbcType=INTEGER})")
	int insertByMap(Map<String, Object> map);

	@Insert("insert into product_category(category_name, category_type) values (#{categoryName, jdbcType=VARCHAR}, #{categoryType, jdbcType=INTEGER})")
	int insertByObject(ProductCategory productCategory);

	 @Select("select * from product_category where category_type = #{categoryType}")
	 @Results({
			 @Result(column = "category_type", property = "categoryType"),
			 @Result(column = "category_id", property = "categoryId"),
			 @Result(column = "category_name", property = "categoryName"),
	 })
	 ProductCategory findBycategoryType(Integer categoryType);


	@Select("select * from product_category where category_name = #{categoryName}")
	@Results({
			@Result(column = "category_type", property = "categoryType"),
			@Result(column = "category_id", property = "categoryId"),
			@Result(column = "category_name", property = "categoryName"),
	})
	List<ProductCategory> findBycategoryName(String categoryName);

	@Update("update product_category set category_name = #{categoryName} where category_type = #{categoryType}")
	int updateByCategoryType(@Param("categoryName") String categoryName, @Param("categoryType") Integer categoryType);

	@Update("update product_category set category_name = #{categoryName} where category_type = #{categoryType}")
	int updateByObject(ProductCategory productCategory);


	@Delete("delete from product_category where category_type = #{categoryType}")
	int deleteByCategoryType(Integer categoryType);

	ProductCategory xmlFindByCategoryType(Integer categoryType);
}

