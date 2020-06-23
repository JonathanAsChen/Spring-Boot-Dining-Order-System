package com.as.jonathan.sell.controller;

import com.as.jonathan.sell.dataObject.ProductCategory;
import com.as.jonathan.sell.dataObject.ProductInfo;
import com.as.jonathan.sell.form.ProductForm;
import com.as.jonathan.sell.service.CategoryService;
import com.as.jonathan.sell.service.ProductService;
import com.as.jonathan.sell.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

/**
 * @author Yifan Chen
 * @version 1.0.0
 * @since 6/20/2020 5:58 PM
 */
@Controller
@RequestMapping("/seller/product")
@Slf4j
public class SellerProductController {

	@Autowired
	private ProductService productService;

	@Autowired
	private CategoryService categoryService;

	@GetMapping("/list")
	public ModelAndView list(@RequestParam(value = "page", defaultValue = "1") Integer page,
							 @RequestParam(value = "size", defaultValue = "10") Integer size,
							 Model model) {
		PageRequest request = PageRequest.of(page - 1, size);
		Page<ProductInfo> productInfoPage = productService.findAll(request);
		model.addAttribute("productPage", productInfoPage);
		model.addAttribute("currentPage", page);
		model.addAttribute("pageSize", size);

		return new ModelAndView("product/list");

	}

	@GetMapping("/shelve/{productId}")
	public ModelAndView shelve(@PathVariable("productId") String productId,
							   Model model) {

		model.addAttribute("url", "/sell/seller/product/list");
		try {
			productService.shelve(productId);
		} catch (Exception e) {
			model.addAttribute("msg", e.getMessage());

			return new ModelAndView("common/error");
		}
		model.addAttribute("msg", "Shelved the product.");
		return new ModelAndView("common/success");


	}

	@GetMapping("/unshelve/{productId}")
	public ModelAndView unshelve(@PathVariable("productId") String productId,
							   Model model) {

		model.addAttribute("url", "/sell/seller/product/list");
		try {
			productService.unshelve(productId);
		} catch (Exception e) {
			model.addAttribute("msg", e.getMessage());

			return new ModelAndView("common/error");
		}
//		model.addAttribute("msg", "Unshelved the product.");
		return new ModelAndView("common/success");


	}

	@ModelAttribute
	public void getProductInfo(@RequestParam(value = "productId", required = false) String productId,
							   Model model) {

		ProductInfo productInfo = productId == null ? new ProductInfo() : productService.findOne(productId);
		model.addAttribute("productInfo", productInfo);
	}

	@GetMapping("/index")
	public ModelAndView index(@RequestParam(value = "productId", required = false) String productId,
						   Model model) {
		ProductInfo productInfo = productId == null ? new ProductInfo() : productService.findOne(productId);
		model.addAttribute("productInfo", productInfo);

		//find all the categories
		List<ProductCategory> categoryList = categoryService.findAll();
		model.addAttribute("categoryList", categoryList);

		return new ModelAndView("product/index");

	}


	@PostMapping("/index")
	public ModelAndView save(@Valid ProductForm product, BindingResult bindingResult, Model model) {
		model.addAttribute("url", "/sell/seller/product/list");
		if (bindingResult.hasErrors()) {
			model.addAttribute("msg", bindingResult.getFieldError().getDefaultMessage());
			return new ModelAndView("common/error");
		}
		ProductInfo productInfo;
		try {
			if (!product.getProductId().isEmpty()) {
				//edit
				productInfo = productService.findOne(product.getProductId());

			} else {
				//new
				productInfo = new ProductInfo();
				product.setProductId(KeyUtil.genUniqueKey());
			}
			BeanUtils.copyProperties(product, productInfo);
			productService.save(productInfo);
		} catch (Exception e) {
			model.addAttribute("msg", e.getMessage());
			return new ModelAndView("common/error");
		}

		return new ModelAndView("common/success");
	}

}
