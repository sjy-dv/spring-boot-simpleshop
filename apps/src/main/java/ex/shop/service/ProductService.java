package ex.shop.service;

import ex.shop.dto.ProductDto;

public interface ProductService {

    int addProduct(ProductDto product, String token) throws Exception;
}
