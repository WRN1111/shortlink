package org.wrn.shortlink.admin.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.wrn.shortlink.admin.common.constant.RedisCacheConstant;
import org.wrn.shortlink.admin.common.convention.exception.ClientException;
import org.wrn.shortlink.admin.common.enums.UserErrorCodeEnum;
import org.wrn.shortlink.admin.dao.entity.UserDO;
import org.wrn.shortlink.admin.dao.mapper.UserMapper;
import org.wrn.shortlink.admin.dto.req.UserRegisterReqDTO;
import org.wrn.shortlink.admin.dto.resp.UserRespDTO;
import org.wrn.shortlink.admin.service.UserService;

/**
 * @author: Admin
 * @Desc:
 * @create: 2024-12-04 21:51
 **/
@Service
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, UserDO> implements UserService {

    private final RBloomFilter<String> rBloomFilter;
    private final RedissonClient redissonClient;

    @Override
    public UserRespDTO getUserByUsername(String username) {
        LambdaQueryWrapper<UserDO> queryWrapper = Wrappers.lambdaQuery(UserDO.class)
                .eq(UserDO::getUsername, username);
        UserDO userDO = baseMapper.selectOne(queryWrapper);
        if (userDO == null) {
            throw new ClientException(UserErrorCodeEnum.USER_NULL);
        }

        UserRespDTO result = new UserRespDTO();
        BeanUtils.copyProperties(userDO, result);
        return result;
    }

    @Override
    public Boolean hasUsername(String username) {
        return !rBloomFilter.contains(username);
    }

    @Override
    public void register(UserRegisterReqDTO requestParam) {
        if (!hasUsername(requestParam.getUsername())) {
            throw new ClientException(UserErrorCodeEnum.USER_EXIST);
        }
        RLock lock = redissonClient.getLock(RedisCacheConstant.LOCK_USER_REGISTER_KEY + requestParam.getUsername());

        try {
            if(lock.tryLock()) {
                int insert = baseMapper.insert(BeanUtil.toBean(requestParam, UserDO.class));
                if (insert < 1) {
                    throw new ClientException(UserErrorCodeEnum.USER_SAVE_ERROR);
                }
                rBloomFilter.add(requestParam.getUsername());
                return;
            }
            throw new ClientException(UserErrorCodeEnum.USER_NAME_EXIST);
        }finally {
            lock.unlock();
        }


    }


}
