package com.wsr.entity.base;

import java.util.Objects;

/**
 * @author ：wangsr
 * @description：
 * @date ：Created in 2021/11/29 9:45
 */
public class ResponseUtil<T> {
    /**
     * 成功响应
     * @param <T>
     * @return
     */
    public static <T> BaseResp<T> success() {
        return new BaseResp<>(BaseStatus.SUCCESS.getCode(), BaseStatus.SUCCESS.getMsg(), null, null);
    }

    /**
     * 成功响应返回数据
     * @param t
     * @param <T>
     * @return
     */
    public static <T> BaseResp<T> success(T t) {
        return new BaseResp<>(BaseStatus.SUCCESS.getCode(), BaseStatus.SUCCESS.getMsg(), t, null);
    }

    /**
     * 成功响应返回数据
     * @param t
     * @param <T>
     * @return
     */
    public static <T> BaseResp<T> success(T t,String meg) {
        return new BaseResp<>(BaseStatus.SUCCESS.getCode(), meg, t, null);
    }

    /**
     * 成功响应返回数据
     * @param status
     * @param <T>
     * @return
     */
    public static <T> BaseResp<T> success(BaseStatus status) {
        return new BaseResp<T>(status.getCode(), status.getMsg(), null, null);
    }

    /**
     * 成功响应返回数据
     *
     * @param <T>
     * @return
     */
    public static <T> BaseResp<T> success(Integer code, String message) {
        return new BaseResp<>(code, message, null, null);
    }


    /**
     * 错误响应返回数据
     * @param code
     * @param message
     * @param t
     * @param <T>
     * @return
     */
    public static <T> BaseResp<T> error(Integer code, String message, T t) {
        return new BaseResp<>(code, message, t, null);
    }

    /**
     * 返回自定义错误
     * @param code
     * @param message
     * @param <T>
     * @return
     */
    public static <T> BaseResp<T> error(Integer code, String message) {
        return new BaseResp<>(code, message, null, null);
    }

    /**
     * 是否成功
     * @param response
     * @param code
     * @param message
     * @return
     */
    public static boolean isCallSuccess(BaseResp<?> response, Integer code, String message) {
        // 业务成功
        if (Objects.equals(BaseStatus.SUCCESS.getCode(), code)) {
            return true;
        }

        // 业务失败，透传失败message
        response.setMsg(message);
        response.setCode(code);

        return false;
    }

    /**
     * 是否成功
     * @param response
     * @return
     */
    public static boolean isCallSuccess(BaseResp<?> response) {
        return response != null && Objects.equals(BaseStatus.SUCCESS.getCode(), response.getCode());
    }

    /**
     * 是否成功
     * @param returnResp
     * @param receivedResp
     * @return
     */
    public static boolean isCallSuccess(BaseResp<?> returnResp, BaseResp receivedResp) {
        Integer code = receivedResp.getCode();
        String msg = receivedResp.getMsg();
        return isCallSuccess(returnResp, code, msg);
    }
    /**
     * 自定义返回数据
     * @param status
     * @param <T>
     * @return
     */
    public static <T> BaseResp<T> error(BaseStatus status) {
        return new BaseResp<T>(status.getCode(), status.getMsg(), null, null);
    }
}
