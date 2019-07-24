package org.gavin.aspect;

import lombok.extern.slf4j.Slf4j;
import org.gavin.vo.SysResut;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class SysResutAspect {
    @ExceptionHandler(RuntimeException.class)
    public SysResut sysResutFail(Exception e ){
        e.printStackTrace();
        log.error(e.getMessage());
        return SysResut.fail();
    }
}
