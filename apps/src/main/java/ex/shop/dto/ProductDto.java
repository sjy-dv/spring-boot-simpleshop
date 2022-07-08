package ex.shop.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ProductDto {
    private int idx;
    private String userId;
    private String productName;
    private String productDescription;
    private MultipartFile productImage;
    private String Pimage;
    private String productAmount;
}
