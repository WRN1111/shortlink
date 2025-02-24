package org.wrn.shortlink.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.wrn.shortlink.admin.dao.entity.UserDO;
import org.wrn.shortlink.admin.dto.req.UserLoginReqDTO;
import org.wrn.shortlink.admin.dto.req.UserRegisterReqDTO;
import org.wrn.shortlink.admin.dto.req.UserUpdateReqDTO;
import org.wrn.shortlink.admin.dto.resp.UserLoginRespDTO;
import org.wrn.shortlink.admin.dto.resp.UserRespDTO;

/**
 * 用户接口层
 */
public interface UserService extends IService<UserDO> {

    /**
     * 根据用户名查询用户信息
     *
     * @param username 用户名
     * @return 用户返回实体
     */
    UserRespDTO getUserByUsername(String username);

    /**
     * @param username 用户名
     * @return 存在返回true 不存在返回false
     */
    Boolean hasUsername(String username);

    void register(UserRegisterReqDTO requestParam);

    /**
     * 根据用户名修改用户
     *
     * @param requestParam 修改用户请求参数
     */
    void update(UserUpdateReqDTO requestParam);

    /**
     * 用户登录
     *
     * @param requestParam 用户登录请求参数
     * @return 用户登录返回参数 Token
     */
    UserLoginRespDTO login(UserLoginReqDTO requestParam);

    /**
     * 检查用户是否登录
     *
     * @param username 用户名
     * @param token    用户登录 Token
     * @return 用户是否登录标识
     */
    Boolean checkLogin(String username, String token);

    /**
     *
     * @param username 用户名
     * @param token 用户登录token
     */
    void logout(String username,String token);
}