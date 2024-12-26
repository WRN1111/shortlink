package org.wrn.shortlink.admin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.wrn.shortlink.admin.common.convention.result.Result;
import org.wrn.shortlink.admin.common.convention.result.Results;
import org.wrn.shortlink.admin.dto.req.ShortLinkGroupSaveReqDTO;
import org.wrn.shortlink.admin.dto.req.ShortLinkGroupUpdateReqDTO;
import org.wrn.shortlink.admin.dto.resp.ShortLinkGroupRespDTO;
import org.wrn.shortlink.admin.service.GroupService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class GroupController {

    private final GroupService groupService;

    /**
     * 查询短链接分组集合
     */
    @PostMapping("/api/short-link/v1/group")
    public Result<Void> save(@RequestBody ShortLinkGroupSaveReqDTO requestParam) {
        groupService.saveGroup(requestParam.getName());
        return Results.success();
    }

    @GetMapping("/api/short-link/v1/group")
    public Result<List<ShortLinkGroupRespDTO>> listGroup() {
        return Results.success(groupService.listGroup());
    }

    /**
     * 修改短链接分组名称
     */
    @PutMapping("/api/short-link/v1/group")
    public Result<Void> updateGroup(@RequestBody ShortLinkGroupUpdateReqDTO requestParam) {
        groupService.updateGroup(requestParam);
        return Results.success();
    }
}