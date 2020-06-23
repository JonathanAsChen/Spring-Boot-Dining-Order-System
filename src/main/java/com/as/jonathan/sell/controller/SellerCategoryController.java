package com.as.jonathan.sell.controller;

import com.as.jonathan.sell.dataObject.ProductCategory;
import com.as.jonathan.sell.dataObject.ProductInfo;
import com.as.jonathan.sell.form.CategoryForm;
import com.as.jonathan.sell.form.ProductForm;
import com.as.jonathan.sell.service.CategoryService;
import com.as.jonathan.sell.utils.KeyUtil;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.jws.WebParam;
import javax.validation.Valid;
import javax.ws.rs.POST;
import java.util.List;

/**
 * @author Yifan Chen
 * @version 1.0.0
 * @since 6/22/2020 9:11 PM
 */
@Controller
@RequestMapping("/seller/category")
@Slf4j
public class SellerCategoryController {

	@Autowired
	private CategoryService categoryService;

	/**
	 * list category.
	 * @param model
	 * @return
	 */
	@GetMapping("/list")
	public ModelAndView list(Model model) {
		List<ProductCategory> categoryList = categoryService.findAll();
		model.addAttribute("categoryList", categoryList);
		return new ModelAndView("category/list");
	}

	/**
	 * category new/edit route.
	 * @param categoryId
	 * @param model
	 * @return
	 */
	@GetMapping("/index")
	public ModelAndView index(@RequestParam(value = "categoryId", required = false) Integer categoryId,
							  Model model) {
		ProductCategory productCategory =
				categoryId == null ? new ProductCategory() : categoryService.findOne(categoryId);
		if (productCategory == null) {
			//not found
			productCategory = new ProductCategory();
		}
		model.addAttribute("productCategory", productCategory);

		return new ModelAndView("category/index");
	}

	/**
	 * category new/edit.
	 * @param categoryForm
	 * @param bindingResult
	 * @param model
	 * @return
	 */
	@PostMapping("/index")
	public ModelAndView save(@Valid CategoryForm categoryForm, BindingResult bindingResult,
							  Model model) {
		model.addAttribute("url", "/sell/seller/category/list");
		if (bindingResult.hasErrors()) {
			model.addAttribute("msg", bindingResult.getFieldError().getDefaultMessage());
			return new ModelAndView("common/error");
		}
		ProductCategory productCategory;
		try {
			productCategory =
					categoryForm.getCategoryId() == null ? new ProductCategory() :
							categoryService.findOne(categoryForm.getCategoryId());
			if (productCategory == null) {
				// not found
				productCategory = new ProductCategory();
			}

			BeanUtils.copyProperties(categoryForm, productCategory);
			categoryService.save(productCategory);
		} catch (Exception e) {
			model.addAttribute("msg", e.getMessage());
			return new ModelAndView("common/error");
		}

		return new ModelAndView("common/success");
	}

}
