package com.jojo.zhuhaibusclock.handler;

import com.jojo.zhuhaibusclock.exception.AbstractException;
import com.jojo.zhuhaibusclock.model.BaseResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * 全局异常监视器
 *
 * @author JoJoWu
 */
@ControllerAdvice
public class BusClockExceptionHandler {
//    /**
//     * 微信接口异常监视器
//     *
//     * @param e 异常
//     * @return 格式化后的异常数据
//     */
//    @ResponseBody
//    @ExceptionHandler(value = WxApiRequestException.class)
//    public BaseResponse<Object> wxApiRequestErrorHandler(Exception e) {
//        return new BaseResponse<>(400, e.getMessage(), null);
//    }

    /**
     * Token异常监视器
     *
     * @param e 异常
     * @return 格式化后的异常数据
     */
    @ExceptionHandler(value = AbstractException.class)
    public ResponseEntity<BaseResponse<?>> tokenExceptionErrorHandler(AbstractException e) {
        BaseResponse<Object> baseResponse = new BaseResponse<>(e.getStatus().value(), e.getMessage(), e.getErrorData());
        return new ResponseEntity<>(baseResponse, e.getStatus());
    }

//    /**
//     * SQL异常监视器
//     *
//     * @param e 异常
//     * @return 格式化后的异常数据
//     */
//    @ResponseBody
//    @ExceptionHandler(value = NotFoundException.class)
//    public BaseResponse<Object> notFoundExceptionErrorHandler(Exception e) {
//        return new BaseResponse<>(404, e.getMessage(), null);
//    }
}
