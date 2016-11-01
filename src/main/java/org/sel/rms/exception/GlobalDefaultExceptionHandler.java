package org.sel.rms.exception;

import org.apache.log4j.Logger;
import org.sel.rms.common.ResponseMessage;
import org.sel.rms.controller.PaperController;
import org.sel.rms.exception.annoation.WithStatus;
import org.sel.rms.status.PaperStatus;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.awt.print.Paper;

/**
 * Created by xubowei on 2016/10/15.
 */
@ControllerAdvice
public class GlobalDefaultExceptionHandler {
    private static final String DEFAULT_ERROR_VIEW = "static/error";
    private final static Logger logger = Logger.getLogger(GlobalDefaultExceptionHandler.class);
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResponseMessage defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {

        logger.error(e.getMessage(),e);
            if (e instanceof PaperException) {
                return new ResponseMessage(((PaperException) e).getPaperStatus());
            } else if (e instanceof ProjectException) {
                return new ResponseMessage(((ProjectException) e).getProjectStatus());
            } else if (e instanceof HttpMessageNotReadableException) {
                return new ResponseMessage(PaperStatus.ARGUMENTS_ERROR);
            } else {
                return new ResponseMessage(1, "ERROR: " + e.getMessage(), null);
            }
        // Otherwise setup and send the user to a default error-view.
//        ModelAndView mav = new ModelAndView();
//        mav.addObject("exception", e);
//        mav.addObject("url", req.getRequestURL());
//        mav.setViewName(DEFAULT_ERROR_VIEW);
//        return mav;
    }


}

