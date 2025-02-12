
package com.cyy.common.utils;


import com.cyy.common.enums.ErrorCode;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @author winter
 * @author CYY
 * R 类的使用简介：
 * 不允许通过直接实例化 R 类 ， 建议使用 R.ok() , R.error().data().... 来链式编程
 * 比如在我们
 * 成功（不出异常）返回 数据（json）时，示例： return R.ok().data("data", json).
 * 失败（全局异常处理） : return R.error().message("服务器内部异常")
 */
@Data
@Schema(description = "通用返回类")
public class R {
    @Schema(description = "请求是否成功")
    private boolean success;
    @Schema(description = "返回码")
    private String code;
    @Schema(description = "返回信息")
    private String message;
    @Schema(description = "返回的 JSON 数据")
    private Map<String, Object> data = new HashMap<>();

    // 不允许通过直接实例化 R 类 ， 建议使用 R.ok() , R.error().data().... 来链式编程
    private R() {}

    //成功静态方法
    public static R ok(){
        R r = new R();
        r.setSuccess(true);
        r.setCode(ErrorCode.SUCCESS.getCode());
        r.setMessage("成功");
        return r;
    }

    public static R systemError(){
        R r = new R();
        r.setSuccess(false);
        r.setCode(ErrorCode.SYSTEM_ERROR.getCode());
        r.setMessage("失败");
        return r;
    }

    public static R clientError(){
        R r = new R();
        r.setSuccess(false);
        r.setCode(ErrorCode.CLIENT_ERROR.getCode());
        r.setMessage("失败");
        return r;
    }

    public static R error(ErrorCode errorCode) {
        R r = new R();
        r.setSuccess(false);
        r.setCode(errorCode.getCode());
        r.setMessage(errorCode.getMessage());
        return r;
    }

    public static R error(String code, String message) {
        R r = new R();
        r.setSuccess(false);
        r.setCode(code);
        r.setMessage(message);
        return r;
    }


    // 通过return this 来使用达到链式编程的目的hh
    public R success(Boolean success){
        this.setSuccess(success);
        return this;
    }
    public R message(String message){
        this.setMessage(message);
        return this;
    }
    public R code(String code){
        this.setCode(code);
        return this;
    }
    //链式编程在放map的Data里似乎特别好用 ， 使用 map 的好处在于 一些原本用 多种场景的dto类，现在只需要在map里设置不同的 key来实现
    public R data(String key, Object value){
        this.data.put(key, value);
        return this;
    }
    public R data(Map<String, Object> map){
        this.setData(map);
        return this;
    }

}