package ex.shop.dto;

import java.util.Date;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class RefundHistory {
    private int idx;
    private String userId;
    private int productId;
    private Date createdAt;
}
