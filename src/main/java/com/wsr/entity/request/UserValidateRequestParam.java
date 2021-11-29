package com.wsr.entity.request;

import lombok.Data;

/**
 * @author ：wangsr
 * @description：
 * @date ：Created in 2021/11/26 15:02
 */
@Data
public class UserValidateRequestParam {
    private String username;

    private String password;
}
