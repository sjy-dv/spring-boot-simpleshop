package ex.shop.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginVo {
    private String token;
    private String refreshToken;
    private String grade;
}
