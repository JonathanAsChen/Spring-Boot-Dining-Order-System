package com.as.jonathan.sell.controller;

import com.as.jonathan.sell.VO.ProductInfoVO;
import com.as.jonathan.sell.VO.ProductVO;
import com.as.jonathan.sell.VO.ResultVO;
import com.as.jonathan.sell.dataObject.ProductCategory;
import com.as.jonathan.sell.dataObject.ProductInfo;
import com.as.jonathan.sell.service.CategoryService;
import com.as.jonathan.sell.service.ProductService;
import com.as.jonathan.sell.utils.ResultVOUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.transform.Result;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Yifan Chen
 * @version 1.0.0
 * @since 6/15/2020
 */
@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {

	@Autowired
	private ProductService productService;

	@Autowired
	private CategoryService categoryService;

	@GetMapping("/list")
	public ResultVO list() {
		//1. search for all products on sell
		List<ProductInfo> productInfoList = productService.findUpAll();

		//2. search category type (one time query)
		List<Integer> categoryTypeList;
//		for (ProductInfo productInfo : productInfoList) {
//			if (!categoryTypeList.contains(productInfo.getCategoryType())) {
//				categoryTypeList.add(productInfo.getCategoryType());
//			}
//		}

		// lamda expression
		categoryTypeList = productInfoList.stream()
				.map(e -> e.getCategoryType())
				.collect(Collectors.toList());

		List<ProductCategory> productCategoryList = categoryService.findByCategoryTypeIn(categoryTypeList);

		//3. json data assembly
		List<ProductVO> productVOList = new ArrayList<>();
		for (ProductCategory productCategory : productCategoryList) {
			ProductVO productVO = new ProductVO();
			productVO.setCategoryType(productCategory.getCategoryType());
			productVO.setCategoryName(productCategory.getCategoryName());

			List<ProductInfoVO> productInfoVOList = new ArrayList<>();
			for (ProductInfo productInfo: productInfoList) {
				if (productInfo.getCategoryType().equals(productCategory.getCategoryType())) {
					ProductInfoVO productInfoVO = new ProductInfoVO();
					BeanUtils.copyProperties(productInfo, productInfoVO);
					productInfoVOList.add(productInfoVO);
				}
			}
			productVO.setProductInfoVOList(productInfoVOList);
			productVOList.add(productVO);
		}



		return ResultVOUtils.success(productVOList);
	}

}
