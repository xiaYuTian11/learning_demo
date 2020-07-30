package com.tmw.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * 响应实体
 *
 * @author TMW
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RpcResponse {

    private String requestId;
    private Throwable throwable;
    private Object result;

}
