package com.jojo.zhuhaibusclock.service;

import com.jojo.zhuhaibusclock.model.entity.Token;

import java.io.IOException;

/**
 * @author JoJoWu
 */
public interface UserService {
    /**
     * 获取用户的session_key
     *
     * @param code 登录时获取的 code
     * @return token
     * @throws IOException 返回为空
     */
    Token getToken(String code) throws IOException;

    boolean checkBarkKey(Long userId);

    String updateBarkKey(Long userId,String barkUrl);
}