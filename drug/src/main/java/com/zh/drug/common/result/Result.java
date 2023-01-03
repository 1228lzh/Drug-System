package com.zh.drug.common.result;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


/**
 * @author king
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {
    //private Boolean success;
    private Integer code;
    private String errorMsg;
    private Object data;
    private Integer total;

    public static Result ok() {
        return new Result(200, null, null, null);
    }

    public static Result ok(Object data) {
        return new Result(200, null, data, null);
    }

    public static Result ok(List<?> data) {
        return new Result(200, null, data, null);
    }
    public static Result ok(List<?> data, Integer total) {
        return new Result(200, null, data, total);
    }


    public static Result fail(){
        return new Result(201,"操作失败",null,null);
    }
    public static Result fail(String errorMsg) {
        return new Result(201, errorMsg, null, null);
    }

    public static Result fail(Integer code,String errorMsg) {
        return new Result(code, errorMsg, null, null);
    }
}

