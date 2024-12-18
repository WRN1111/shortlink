package org.wrn.shortlink.admin.service.impl;

import cn.hutool.db.sql.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.wrn.shortlink.admin.common.convention.exception.ClientException;
import org.wrn.shortlink.admin.common.enums.UserErrorCodeEnum;
import org.wrn.shortlink.admin.dao.entity.UserDO;
import org.wrn.shortlink.admin.dao.mapper.UserMapper;
import org.wrn.shortlink.admin.dto.UserRespDTO;
import org.wrn.shortlink.admin.service.UserService;

/**
 * @author: Admin
 * @Desc:
 * @create: 2024-12-04 21:51
 **/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserDO> implements UserService {
    @Override
    public UserRespDTO getUserByUsername(String username) {
        LambdaQueryWrapper<UserDO> queryWrapper = Wrappers.lambdaQuery(UserDO.class)
                .eq(UserDO::getUsername, username);
        UserDO userDO = baseMapper.selectOne(queryWrapper);
        if(userDO == null) {
            throw new ClientException(UserErrorCodeEnum.USER_NULL);
        }

        UserRespDTO result = new UserRespDTO();
        BeanUtils.copyProperties(userDO, result);
        return result;
    }
}
