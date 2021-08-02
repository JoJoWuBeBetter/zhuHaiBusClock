package com.jojo.zhuhaibusclock.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.jojo.zhuhaibusclock.exception.AuthenticationException;
import com.jojo.zhuhaibusclock.mapper.SysUserMapper;
import com.jojo.zhuhaibusclock.model.SysUser;
import com.jojo.zhuhaibusclock.model.entity.Token;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * 通过JWT生成和严重Token
 *
 * @author JoJoWu
 */
@Component
public class JwtUtil {
    private static final long EXPIRE_TIME = 7200;
    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Resource
    private SysUserMapper userMapper;

    /**
     * 通过openId和sessionKey生成Token
     *
     * @param user 用户实例
     * @return Token
     */
    public Token createToken(SysUser user) {
        Token token = new Token();
        String t = JWT.create()
                .withClaim("id", user.getId())
                .sign(Algorithm.HMAC256(user.getSessionKey()));
        token.setUserId(user.getId());
        token.setToken(t);
        stringRedisTemplate.opsForValue().set(user.getId().toString(), token.getToken(), EXPIRE_TIME,TimeUnit.SECONDS);
        return token;
    }

    /**
     * 验证Token
     *
     * @param token token
     * @return 结果
     */
    public boolean verifyToken(String token) {
        if (token == null) {
            throw new AuthenticationException("token错误或过期");
        }
        Long id = getId(token);

        if (id == null) {
            throw new AuthenticationException("token错误或过期");
        }

        String tokenCache = stringRedisTemplate.opsForValue().get(id.toString());

        if (tokenCache == null) {
            throw new AuthenticationException("token错误或过期");
        }
        if (!tokenCache.equals(token)) {
            throw new AuthenticationException("token错误或过期");
        }


        SysUser user = userMapper.selectById(id);

        if (user == null) {
            throw new AuthenticationException("无法查找到token绑定的用户");
        }

        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(user.getSessionKey()))
                .withClaim("id", user.getId())
                .build();

        try {
            verifier.verify(token);
        } catch (JWTVerificationException e) {
            throw new AuthenticationException("无法查找到token绑定的用户");
        }

        return true;
    }


    public static Long getId(String token) {
        return JWT.decode(token).getClaim("id").asLong();
    }

}
