package org.wrn.shortlink.admin.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.wrn.shortlink.admin.common.database.BaseDO;
import org.wrn.shortlink.admin.dao.entity.GroupDO;
import org.wrn.shortlink.admin.dao.mapper.GroupMapper;
import org.wrn.shortlink.admin.dto.resp.ShortLinkGroupRespDTO;
import org.wrn.shortlink.admin.service.GroupService;
import org.wrn.shortlink.admin.tookit.RandomGenerator;

import java.util.List;

@Slf4j
@Service
public class GroupServiceImpl extends ServiceImpl<GroupMapper, GroupDO> implements GroupService {

    @Override
    public void saveGroup(String groupName) {
        String gid;
        do {
            gid = RandomGenerator.generateRandom();
        } while (!hasGid(gid));
        GroupDO groupDO = GroupDO.builder()
                .gid(gid)
                .sortOrder(0)
                .name(groupName)
                .build();
        baseMapper.insert(groupDO);
    }

    @Override
    public List<ShortLinkGroupRespDTO> listGroup() {
        LambdaQueryWrapper<GroupDO> queryWrapper = Wrappers.lambdaQuery(GroupDO.class)
                .eq(GroupDO::getDelFlag, 0)
                .eq(GroupDO::getUsername, "admin")
                .orderByDesc(GroupDO::getSortOrder, BaseDO::getUpdateTime);

        List<GroupDO> selectList = baseMapper.selectList(queryWrapper);
        return BeanUtil.copyToList(selectList, ShortLinkGroupRespDTO.class);
    }

    private boolean hasGid(String gid) {
        LambdaQueryWrapper<GroupDO> queryWrapper = Wrappers.lambdaQuery(GroupDO.class)
                .eq(GroupDO::getGid, gid)
                .eq(GroupDO::getUsername, null);
        GroupDO hasGroupFlag = baseMapper.selectOne(queryWrapper);
        return hasGroupFlag == null;

    }
}