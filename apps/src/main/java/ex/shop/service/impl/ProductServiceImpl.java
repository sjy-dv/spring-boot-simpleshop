package ex.shop.service.impl;

import org.springframework.stereotype.Service;

import ex.shop.dto.ProductDto;
import ex.shop.mapper.ProductMapper;
import ex.shop.service.ProductService;
import ex.shop.utils.Jwt;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductMapper productMapper;
    private final Jwt jwt;

    @Override
    public int addProduct(ProductDto product, String token) throws Exception {
        try {
            String userId = jwt.VerifyToken(token);
            if (userId == null) {
                throw new Exception("token is invalid");
            }
            product.setUserId(userId);
            return productMapper.addProduct(product);
        } catch (Exception e) {
            // TODO: handle exception
            throw new Exception(e.toString());
        }
    }
}
