package com.jojo.zhuhaibusclock.service.impl;

import com.jojo.zhuhaibusclock.config.ZhuHaiBusClockProps;
import com.jojo.zhuhaibusclock.exception.NotFoundException;
import com.jojo.zhuhaibusclock.exception.WxApiRequestException;
import com.jojo.zhuhaibusclock.mapper.SysUserMapper;
import com.jojo.zhuhaibusclock.model.SysUser;
import com.jojo.zhuhaibusclock.model.entity.Token;
import com.jojo.zhuhaibusclock.remote.WxApi;
import com.jojo.zhuhaibusclock.remote.WxApiResponseBody;
import com.jojo.zhuhaibusclock.security.jwt.JwtUtil;
import com.jojo.zhuhaibusclock.service.MessageService;
import com.jojo.zhuhaibusclock.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author JoJoWu
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private final SysUserMapper userMapper;
    private final ZhuHaiBusClockProps props;
    private final MessageService messageService;
    private final WxApi wxApi;
    private final JwtUtil jwtUtil;

    public UserServiceImpl(SysUserMapper userMapper, ZhuHaiBusClockProps props, MessageService messageService, WxApi wxApi, JwtUtil jwtUtil) {
        this.userMapper = userMapper;
        this.props = props;
        this.messageService = messageService;
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
        WxApiResponseBody responseBody;

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

    @Override
    public boolean checkBarkKey(Long userId) {
        SysUser user = userMapper.selectById(userId);
        return user.getBarkKey() != null && !"".equals(user.getBarkKey());
    }

    @Override
    public String updateBarkKey(Long userId, String barkUrl) {
        String pattern = "/([\\w]+)/";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(barkUrl);
        if (m.find() && m.group(1) != null) {
            log.info("barkKey:" + m.group(1));
            SysUser user = userMapper.selectById(userId);
            user.setBarkKey(m.group(1));
            userMapper.updateById(user);
            messageService.pushMessage(user.getBarkKey(), "test");
            return m.group(1);
        } else {
            throw new NotFoundException("bark更新失败");
        }
    }
}
