package ex.shop.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import ex.shop.dto.ProductDto;

@Mapper
public interface ProductMapper {

    @Insert("insert into product (user_id, p_name, p_desc, p_img, p_amount) values (#{userId}, #{productName}, #{productDescription}, #{Pimage}, #{productAmount})")
    int addProduct(ProductDto product);
}
