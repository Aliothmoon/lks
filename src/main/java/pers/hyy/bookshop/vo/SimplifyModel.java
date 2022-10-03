package pers.hyy.bookshop.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SimplifyModel {
    private String name;
    private String Code;
    private String address;
    public SimplifyModel(String name,String address){
        this.name = name;
        this.address = address;
    }
}
