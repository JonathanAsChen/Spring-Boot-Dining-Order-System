package com.as.jonathan.sell.service.impl;

import com.as.jonathan.sell.DTO.CartDTO;
import com.as.jonathan.sell.dataObject.ProductInfo;
import com.as.jonathan.sell.enums.ProductStatusEnum;
import com.as.jonathan.sell.enums.ResultEnum;
import com.as.jonathan.sell.exception.SellException;
import com.as.jonathan.sell.repository.ProductInfoRepository;
import com.as.jonathan.sell.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Yifan Chen
 * @version 1.0.0
 * @since 6/15/2020
 */
@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductInfoRepository repository;

	@Override
	public ProductInfo findOne(String productId) {
		return repository.findById(productId).orElse(null);
	}

	@Override
	public List<ProductInfo> findUpAll() {
		return repository.findByProductStatus(ProductStatusEnum.UP.getCode());
	}

	@Override
	public Page<ProductInfo> findAll(Pageable pageable) {
		return repository.findAll(pageable);
	}

	@Override
	public ProductInfo save(ProductInfo productInfo) {
		return repository.save(productInfo);
	}

	@Override
	public void increaseStock(List<CartDTO> cartDTOList) {
		for (CartDTO cartDTO: cartDTOList) {
			ProductInfo productInfo = repository.findById(cartDTO.getProductId()).orElse(null);
			if (productInfo == null) {
				throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
			}
			Integer result = productInfo.getProductStock() + cartDTO.getProductQuantity();
			productInfo.setProductStock(result);
			repository.save(productInfo);
		}
	}

	@Override
	@Transactional
	public void decreaseStock(List<CartDTO> cartDTOList) {
		for (CartDTO cartDTO : cartDTOList) {
			ProductInfo productInfo = repository.findById(cartDTO.getProductId()).orElse(null);
			if (productInfo == null) {
				throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
			}
			Integer result = productInfo.getProductStock() - cartDTO.getProductQuantity();
			if (result < 0) {
				throw new SellException(ResultEnum.PRODUCT_STOCK_ERROR);
			}
			productInfo.setProductStock(result);
			repository.save(productInfo);
		}
	}

	@Override
	public ProductInfo shelve(String productId) {
		ProductInfo productInfo = findOne(productId);
		if (productInfo == null) {
			throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
		}
		if (productInfo.getProductStatusEnum() == ProductStatusEnum.UP) {
			throw new SellException(ResultEnum.PRODUCT_STATUS_ERROR);
		}
		productInfo.setProductStatus(ProductStatusEnum.UP.getCode());
		return repository.save(productInfo);

	}

	@Override
	public ProductInfo unshelve(String productId) {
		ProductInfo productInfo = findOne(productId);
		if (productInfo == null) {
			throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
		}
		if (productInfo.getProductStatusEnum() == ProductStatusEnum.DOWN) {
			throw new SellException(ResultEnum.PRODUCT_STATUS_ERROR);
		}
		productInfo.setProductStatus(ProductStatusEnum.DOWN.getCode());
		return repository.save(productInfo);
	}
}
