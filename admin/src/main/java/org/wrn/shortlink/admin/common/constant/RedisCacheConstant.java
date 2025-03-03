package org.wrn.shortlink.admin.common.constant;

/**
 * @author: Admin
 * @Desc:
 * @create: 2024-12-19 21:13
 **/
public class RedisCacheConstant {
    /**
     * 用户注册分布式锁
     */
    public static final String LOCK_USER_REGISTER_KEY = "short-link:lock_user-register:";

    /**
     * 分组创建分布式锁
     */
    public static final String LOCK_GROUP_CREATE_KEY = "short-link:lock_group-create:%s";
}
