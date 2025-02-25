package org.wrn.shortlink.project.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.wrn.shortlink.project.dao.entity.LinkStatsTodayDO;
import org.wrn.shortlink.project.dao.mapper.LinkStatsTodayMapper;
import org.wrn.shortlink.project.service.LinkStatsTodayService;

/**
 * 短链接今日统计接口实现层
 * 公众号：马丁玩编程，回复：加群，添加马哥微信（备注：link）获取项目资料
 */
@Service
public class LinkStatsTodayServiceImpl extends ServiceImpl<LinkStatsTodayMapper, LinkStatsTodayDO> implements LinkStatsTodayService {
}