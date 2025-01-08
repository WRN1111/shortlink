package org.wrn.shortlink.project.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.wrn.shortlink.project.common.convention.result.Result;
import org.wrn.shortlink.project.common.convention.result.Results;
import org.wrn.shortlink.project.dto.req.ShortLinkCreateReqDTO;
import org.wrn.shortlink.project.dto.req.ShortLinkPageReqDTO;
import org.wrn.shortlink.project.dto.req.ShortLinkUpdateReqDTO;
import org.wrn.shortlink.project.dto.resp.ShortLinkCreateRespDTO;
import org.wrn.shortlink.project.dto.resp.ShortLinkPageRespDTO;
import org.wrn.shortlink.project.service.ShortLinkService;

@RestController
@RequiredArgsConstructor
public class ShortLinkController {

    private final ShortLinkService shortLinkService;

    /**
     * 创建短链接
     */
    @PostMapping("/api/short-link/v1/create")
    public Result<ShortLinkCreateRespDTO> createShortLink(@RequestBody ShortLinkCreateReqDTO requestParam) {
        return Results.success(shortLinkService.createShortLink(requestParam));
    }

    /**
     * 修改短链接
     */
    @PostMapping("/api/short-link/v1/update")
    public Result<Void> updateShortLink(@RequestBody ShortLinkUpdateReqDTO requestParam) {
        shortLinkService.updateShortLink(requestParam);
        return Results.success();
    }

    /**
     * 分页查询短链接
     */
    @GetMapping("/api/short-link/v1/page")
    public Result<IPage<ShortLinkPageRespDTO>> pageShortLink(ShortLinkPageReqDTO requestParam) {
        return Results.success(shortLinkService.pageShortLink(requestParam));
    }
}