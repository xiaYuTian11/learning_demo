package com.tmw.aop;

import java.util.List;

/**
 * Advisor注册接口
 *
 * @author TMW
 * @date 2020/7/29 14:36
 */
public interface AdvisorRegistry {
    /**
     * 注册Advisor
     *
     * @param ad
     */
    public void registAdvisor(Advisor ad);

    /**
     * 获取Advisor
     *
     * @return
     */
    public List<Advisor> getAdvisors();
}
