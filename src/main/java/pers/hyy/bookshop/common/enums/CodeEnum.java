package pers.hyy.bookshop.common.enums;

import org.springframework.http.HttpStatus;

public enum CodeEnum {
    SUCCESS(200,"successful"),
    FAILURE(500,"Internal Server Error"),
    REDIRECT(500,"redirect"),
    NOT_FOUND(404,"not found");
    private Integer code;
    private String status;

    CodeEnum(Integer code, String status) {
        this.code = code;
        this.status = status;
    }

    public Integer getCode() {
        return code;
    }

    public String getStatus() {
        return status;
    }
}
