package ex.shop.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import ex.shop.dto.ProductDto;
import ex.shop.exception.GlobalException;
import ex.shop.service.ProductService;
import ex.shop.utils.ResponseHandler;
import ex.shop.utils.Upload;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ProductController {

    private final Upload multer;
    private final GlobalException globalException;
    private final ResponseHandler response;
    private final ProductService productService;

    @PostMapping("/ex/shop/upload/product")
    public ResponseEntity<Object> UploadProduct(@ModelAttribute ProductDto req, @RequestHeader("xauth") String token) {
        try {
            req.setPimage(multer.uploadFile(req.getProductImage()));
            int res = productService.addProduct(req, token);
            if (res == 1) {
                return response.SuccessHandler();
            }
            return response.FailHandler();
        } catch (Exception e) {
            // TODO: handle exception
            return globalException.handleRuntimeException(e);
        }
    }
}
