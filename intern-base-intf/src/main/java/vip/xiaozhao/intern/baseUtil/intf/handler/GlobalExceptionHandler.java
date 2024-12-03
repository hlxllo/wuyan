package vip.xiaozhao.intern.baseUtil.intf.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import vip.xiaozhao.intern.baseUtil.intf.dto.ResponseDO;


/*全局异常处理器*/
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    //专门处理自定义异常
    @ExceptionHandler
    public ResponseDO baseExceptionHandler(Exception e) {
        log.error("异常信息:{}", e.getMessage());
        // 默认状态码默认都500
        return ResponseDO.fail(e.getMessage());
    }

    //专门处理参数校验异常
    //@ExceptionHandler
    //public ResponseDO validationExceptionHandler(MethodArgumentNotValidException e) {
    //    log.error("参数校验异常:{}", e.getMessage());
    //    return ResponseDO.fail(e.getMessage());
    //}

}
