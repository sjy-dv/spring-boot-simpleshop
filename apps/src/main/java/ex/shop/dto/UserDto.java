package ex.shop.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class UserDto {
    private int idx;
    private String nickname;
    private String email;
    @JsonProperty("user_id")
    private String userId;
    private String password;
}
