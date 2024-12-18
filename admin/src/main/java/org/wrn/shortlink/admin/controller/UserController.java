package org.wrn.shortlink.admin.controller;

import cn.hutool.core.bean.BeanUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.wrn.shortlink.admin.common.convention.result.Result;
import org.wrn.shortlink.admin.common.convention.result.Results;
import org.wrn.shortlink.admin.dto.resp.UserActualRespDTO;
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
}
