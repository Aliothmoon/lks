package pers.hyy.bookshop.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PasswordChange {
    private String newWords;
    private String oldWords;
    private String token;
}
