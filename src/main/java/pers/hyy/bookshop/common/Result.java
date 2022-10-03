package pers.hyy.bookshop.common;

import pers.hyy.bookshop.common.enums.CodeEnum;

import java.io.Serializable;

public class Result<T> implements Serializable {
    private final Integer code;
    private final String status;
    private final T data;

    private Result(CodeEnum codeEnum, T data) {
        this.code = codeEnum.getCode();
        this.status = codeEnum.getStatus();
        this.data = data;
    }
    private Result(CodeEnum codeEnum) {
        this.code = codeEnum.getCode();
        this.status = codeEnum.getStatus();
        this.data = null;
    }

    public static<T> Result<T> Return(CodeEnum codeEnum, T data) {
        return new Result(codeEnum, data);
    }

    public static<T> Result<T> Return(CodeEnum codeEnum) {
        return new Result(codeEnum);
    }

    public Integer getCode() {
        return code;
    }

    public String getStatus() {
        return status;
    }

    public T getData() {
        return data;
    }

    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
                ", status='" + status + '\'' +
                ", data=" + data +
                '}';
    }

}
