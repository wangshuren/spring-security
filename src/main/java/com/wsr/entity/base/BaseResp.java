package com.wsr.entity.base;

import com.alibaba.fastjson.JSON;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author ：wangsr
 * @description：
 * @date ：Created in 2021/11/29 9:23
 */
@Data
@AllArgsConstructor
public class BaseResp<T> {
    /** 业务响应码 **/
    private Integer code;

    /** 业务响应信息 **/
    private String msg;

    /** 数据对象 **/
    private T data;

    /** 签名值 **/
    private String sign;

    /** 输出json格式字符串 **/
    public String toJSONString(){
        return JSON.toJSONString(this);
    }

    /** 业务处理成功 **/
    public BaseResp ok(){
        return ok(null);
    }

    /** 业务处理成功 **/
    public BaseResp ok(Object data){
        return new BaseResp(BaseStatus.SUCCESS.getCode(), BaseStatus.SUCCESS.getMsg(), data, null);
    }

    /** 业务处理失败 **/
    public static BaseResp fail(BaseStatus apiCodeEnum, String... params){

        if(params == null || params.length <= 0){
            return new BaseResp(apiCodeEnum.getCode(), apiCodeEnum.getMsg(), null, null);
        }
        return new BaseResp(apiCodeEnum.getCode(), String.format(apiCodeEnum.getMsg(), params), null, null);
    }

    /** 自定义错误信息, 原封不用的返回输入的错误信息 **/
    public static BaseResp customFail(String customMsg){
        return new BaseResp(BaseStatus.CUSTOM_FAIL.getCode(), customMsg, null, null);
    }
}
