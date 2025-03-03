package org.wrn.shortlink.project.handler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.wrn.shortlink.project.common.convention.result.Result;
import org.wrn.shortlink.project.dto.req.ShortLinkCreateReqDTO;
import org.wrn.shortlink.project.dto.resp.ShortLinkCreateRespDTO;

/**
 * @author: Admin
 * @Desc:
 * @create: 2025-03-03 20:02
 **/
public class CustomBlockHandler {
    public static Result<ShortLinkCreateRespDTO> createShortLinkBlockHandlerMethod(ShortLinkCreateReqDTO requestParam, BlockException exception) {
        return new Result<ShortLinkCreateRespDTO>().setCode("B100000").setMessage("当前访问网站人数过多，请稍后再试...");
    }
}
