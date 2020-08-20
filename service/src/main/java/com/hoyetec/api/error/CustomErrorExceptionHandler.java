package com.hoyetec.api.error;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class CustomErrorExceptionHandler {

    private final Logger logger = LogManager.getLogger();

    /**
     * 由程式檢核錯誤時丟出 CustomErrorException
     * 
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = CustomErrorException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public CustomError customError(HttpServletRequest req, CustomErrorException e) {
        CustomError err = e.getError();
        logger.warn("[{},{}] Request: {} raised {}", err.getResponseStatus(), err.getResponseCode(),
                req.getRequestURI(),
                err.getMessage());
        return err;
    }

    /**
     * 執行時期發生任何非預期錯誤
     * 
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public CustomError unknownError(HttpServletRequest req, Exception e) {
        CustomError err = CustomError.unknownError;
        logger.error("[{},{}] Request: {} raised {} ", err.getResponseStatus(), err.getResponseCode(), req.getRequestURI(),
                e);
        logger.error("",e);
        return err;
    }

}
