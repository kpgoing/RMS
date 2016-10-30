package org.sel.rms.exception;

import org.sel.rms.common.ResponseMessage;
import org.sel.rms.exception.annoation.WithStatus;
import org.sel.rms.status.PaperStatus;
import org.springframework.core.annotation.AnnotationUtils;
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

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResponseMessage defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {

            if (e instanceof PaperException) {
                return new ResponseMessage(((PaperException) e).getPaperStatus());
            } else {
                return new ResponseMessage(1,"ERROR: " + e.getMessage(),null);
            }
        // Otherwise setup and send the user to a default error-view.
//        ModelAndView mav = new ModelAndView();
//        mav.addObject("exception", e);
//        mav.addObject("url", req.getRequestURL());
//        mav.setViewName(DEFAULT_ERROR_VIEW);
//        return mav;
    }


}

