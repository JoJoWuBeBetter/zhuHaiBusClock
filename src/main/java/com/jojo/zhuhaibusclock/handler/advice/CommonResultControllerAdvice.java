package com.jojo.zhuhaibusclock.handler.advice;

import com.jojo.zhuhaibusclock.model.BaseResponse;
import com.jojo.zhuhaibusclock.model.entity.Token;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.Objects;

/**
 * 全局格式化返回数据
 *
 * @author JoJoWu
 */
@ControllerAdvice("com.jojo.zhuhaibusclock")
public class CommonResultControllerAdvice implements ResponseBodyAdvice<Object> {
    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object o,
                                  MethodParameter methodParameter,
                                  MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass,
                                  ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        if (o instanceof BaseResponse) {
            return o;
        }
        if (o instanceof Token) {
            Token token = (Token) o;
            serverHttpResponse.getHeaders().add("Token", token.getToken());
        }
        if ("checkWxValid".equals(Objects.requireNonNull(methodParameter.getMethod()).getName())) {
            return o;
        }

        if (o != null) {
            var status = HttpStatus.OK;
            return new BaseResponse<>(status.value(), status.getReasonPhrase(), o);
        } else {
            var status = HttpStatus.BAD_REQUEST;
            return new BaseResponse<>(status.value(), status.getReasonPhrase(), null);
        }
    }
}
