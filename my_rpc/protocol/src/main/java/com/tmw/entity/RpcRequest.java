package com.tmw.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * 请求实体
 *
 * @author TMW
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RpcRequest {

    private String requestId;

    private String className;

    private String methodName;

    private Class<?>[] parameterTypes;

    private Object[] parameters;

}
