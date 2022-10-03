package pers.hyy.bookshop.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HouseHold {
    private Integer keyId;
    private String name;
    private String email;
    private String address;
    private String phone;
    private String status;
}
