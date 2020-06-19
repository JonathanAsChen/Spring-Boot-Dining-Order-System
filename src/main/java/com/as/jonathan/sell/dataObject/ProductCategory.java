package com.as.jonathan.sell.dataObject;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * @author Yifan Chen
 * @version 1.0.0
 * @since 6/15/2020
 */
//@Table(name="table_name")
@Entity
@DynamicUpdate  // update updateTime dynamically
@Data  // no need to write getter or setter with this annotation. Great!
public class ProductCategory {

	/*category id*/
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer categoryId;

	/*category name*/
	private String categoryName;

	/*category type*/
	private Integer categoryType;

	private Date createTime;

	private Date updateTime;

	public ProductCategory() {
	}

	public ProductCategory(String categoryName, Integer categoryType) {
		this.categoryName = categoryName;
		this.categoryType = categoryType;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Integer getCategoryType() {
		return categoryType;
	}

	public void setCategoryType(Integer categoryType) {
		this.categoryType = categoryType;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Override
	public String toString() {
		return "ProductCategory{" +
				"categoryId=" + categoryId +
				", categoryName='" + categoryName + '\'' +
				", categoryType=" + categoryType +
				'}';
	}
}
