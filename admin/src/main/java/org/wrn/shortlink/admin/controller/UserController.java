package org.wrn.shortlink.admin.controller;

import cn.hutool.core.bean.BeanUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.wrn.shortlink.admin.common.convention.result.Result;
import org.wrn.shortlink.admin.common.convention.result.Results;
import org.wrn.shortlink.admin.dto.req.UserLoginReqDTO;
import org.wrn.shortlink.admin.dto.req.UserRegisterReqDTO;
import org.wrn.shortlink.admin.dto.req.UserUpdateReqDTO;
import org.wrn.shortlink.admin.dto.resp.UserActualRespDTO;
import org.wrn.shortlink.admin.dto.resp.UserLoginRespDTO;
import org.wrn.shortlink.admin.dto.resp.UserRespDTO;
import org.wrn.shortlink.admin.service.UserService;

/**
 * @author: Admin
 * @Desc:
 * @create: 2024-12-18 22:12
 **/

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/api/shortlink/v1/user/{username}")
    public Result<UserRespDTO> getUserByUsername(@PathVariable("username") String username) {
        return Results.success(userService.getUserByUsername(username));
    }

    public Result<UserActualRespDTO> getActualUserByUsername(String username) {
        return Results.success(BeanUtil.toBean(getUserByUsername(username), UserActualRespDTO.class));
    }

    /**
     * 查询用户名是否存在
     */
    @GetMapping("/api/short-link/admin/v1/user/has-username")
    public Result<Boolean> hasUsername(@RequestParam("username") String username) {
        return Results.success(userService.hasUsername(username));
    }

    /**
     * 注册用户
     */
    @PostMapping("/api/short-link/admin/v1/user")
    public Result<Void> register(@RequestBody UserRegisterReqDTO requestParam) {
        userService.register(requestParam);
        return Results.success();
    }

    /**
     * 修改用户
     */
    @PutMapping("/api/short-link/admin/v1/user")
    public Result<Void> update(@RequestBody UserUpdateReqDTO requestParam) {
        userService.update(requestParam);
        return Results.success();
    }

    /**
     * 用户登录
     */
    @PostMapping("/api/short-link/admin/v1/user/login")
    public Result<UserLoginRespDTO> login(@RequestBody UserLoginReqDTO requestParam) {
        return Results.success(userService.login(requestParam));
    }

    /**
     * 检查用户是否登录
     */
    @GetMapping("/api/short-link/admin/v1/user/check-login")
    public Result<Boolean> checkLogin(@RequestParam("username") String username, @RequestParam("token") String token) {
        return Results.success(userService.checkLogin(username, token));
    }
}
