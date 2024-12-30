package org.wrn.shortlink.project.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.wrn.shortlink.project.dao.entity.ShortLinkDO;
import org.wrn.shortlink.project.dao.mapper.ShortLinkMapper;
import org.wrn.shortlink.project.dto.req.ShortLinkCreateReqDTO;
import org.wrn.shortlink.project.dto.resp.ShortLinkCreateRespDTO;
import org.wrn.shortlink.project.service.ShortLinkService;
import org.wrn.shortlink.project.tookit.HashUtil;

/**
 * @author: Admin
 * @Desc:
 * @create: 2024-12-30 19:46
 **/
@Slf4j
@Service
public class ShortLinkServiceImpl extends ServiceImpl<ShortLinkMapper, ShortLinkDO> implements ShortLinkService {
    @Override
    public ShortLinkCreateRespDTO createShortLink(ShortLinkCreateReqDTO requestParam) {
        String suffix = generateSuffix(requestParam);
        ShortLinkDO shortLinkDO = BeanUtil.toBean(requestParam, ShortLinkDO.class);
        shortLinkDO.setShortUri(suffix);
        shortLinkDO.setEnableStatus(1);
        shortLinkDO.setFullShortUrl(requestParam.getDomain() + "/" + suffix);
        baseMapper.insert(shortLinkDO);
        return ShortLinkCreateRespDTO.builder()
                .fullShortUrl(shortLinkDO.getFullShortUrl())
                .originUrl(shortLinkDO.getOriginUrl())
                .gid(shortLinkDO.getGid())
                .build();
    }

    private String generateSuffix(ShortLinkCreateReqDTO requestParam) {
        String originUrl = requestParam.getOriginUrl();
        return HashUtil.hashToBase62(originUrl);
    }
}
