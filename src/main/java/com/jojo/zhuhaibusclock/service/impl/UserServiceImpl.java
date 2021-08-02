package com.jojo.zhuhaibusclock.service.impl;

import com.jojo.zhuhaibusclock.config.ZhuHaiBusClockProps;
import com.jojo.zhuhaibusclock.exception.WxApiRequestException;
import com.jojo.zhuhaibusclock.mapper.SysUserMapper;
import com.jojo.zhuhaibusclock.model.SysUser;
import com.jojo.zhuhaibusclock.model.entity.Token;
import com.jojo.zhuhaibusclock.remote.WxApi;
import com.jojo.zhuhaibusclock.remote.WxApiResponseBody;
import com.jojo.zhuhaibusclock.security.jwt.JwtUtil;
import com.jojo.zhuhaibusclock.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.io.IOException;

/**
 * @author JoJoWu
 */
@Service
public class UserServiceImpl implements UserService {

    private final SysUserMapper userMapper;
    private final ZhuHaiBusClockProps props;
    private final WxApi wxApi;
    private final JwtUtil jwtUtil;

    public UserServiceImpl(SysUserMapper userMapper, ZhuHaiBusClockProps props, WxApi wxApi, JwtUtil jwtUtil) {
        this.userMapper = userMapper;
        this.props = props;
        this.wxApi = wxApi;
        this.jwtUtil = jwtUtil;
    }

    /**
     * 获得session_key
     *
     * @param code 登录时获取的 code
     * @return session_key
     */
    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Token getToken(String code) {
        WxApiResponseBody responseBody = null;
        String token = null;

        try {
            responseBody = wxApi.jsCodeToSession(
                    props.getWxAppId(), props.getWxAppSecret(), code, "authorization_code").execute().body();
        } catch (IOException e) {
            throw new WxApiRequestException(e.getMessage());
        }

        if (responseBody == null) {
            throw new WxApiRequestException("微信API请求为空");
        }

        if (responseBody.getErrcode() != null) {
            throw new WxApiRequestException(responseBody.getErrmsg());
        }


        //        判断数据库是否已经记录过该用户
        SysUser user = userMapper.selectByOpenId(responseBody.getOpenid());

        if (user == null) {
            user = new SysUser();
            user.setSessionKey(responseBody.getSessionKey());
            user.setOpenId(responseBody.getOpenid());
            userMapper.insert(user);
        } else {
            user.setSessionKey(responseBody.getSessionKey());
            userMapper.updateById(user);
        }
        return jwtUtil.createToken(user);
    }
}
