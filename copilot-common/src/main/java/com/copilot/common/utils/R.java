package com.copilot.common.utils;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.http.HttpStatus;

import java.io.Serializable;


/**
 * @program: copilot-server
 * @description: 响应信息主体
 * @author: hfli
 * @create: 2023/4/7 14:40
 */
@Setter
@Getter
@ToString
@Accessors(chain = true)
public class R<T> implements Serializable {

    private static final long serialVersionUID = 1L;


    private int code = HttpStatus.OK.value();
    private String msg;
    private T data;

    public static <T> R<T> ok() {
        R<T> r = new R<>();
        r.setMsg("操作成功");
        return r;
    }

    public static <T> R<T> ok(T data) {
        R<T> r = new R<>();
        r.setMsg("操作成功");
        r.setData(data);
        return r;
    }

    public static <T> R<T> error() {
        return error(HttpStatus.INTERNAL_SERVER_ERROR.value(), "未知异常，请联系管理员");
    }

    public static <T> R<T> error(String msg) {
        return error(HttpStatus.INTERNAL_SERVER_ERROR.value(), msg);
    }

    public static <T> R<T> error(int code, String msg) {
        R<T> r = new R<>();
        r.setCode(code);
        r.setMsg(msg);
        return r;
    }
}
