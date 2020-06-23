package com.as.jonathan.sell.form;

import lombok.Data;

import java.util.Date;

/**
 * @author Yifan Chen
 * @version 1.0.0
 * @since 6/22/2020 9:31 PM
 */
@Data
public class CategoryForm {
	private Integer categoryId;

	/*category name*/
	private String categoryName;

	/*category type*/
	private Integer categoryType;

	private Date createTime;

	private Date updateTime;
}
