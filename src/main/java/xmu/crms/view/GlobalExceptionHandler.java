package xmu.crms.view;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import xmu.crms.entity.ErrorInfo;
import xmu.crms.exception.UserDumplicatedException;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    public static final String DEFAULT_ERROR_VIEW = "common/error";

    @ExceptionHandler(value = Exception.class)
    public ModelAndView defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {

        ModelAndView mav = new ModelAndView();
        mav.addObject("exception",e);
        mav.addObject("url", req.getRequestURL());
        mav.setViewName(DEFAULT_ERROR_VIEW);
        return mav;
    }

    @ExceptionHandler(value = UserDumplicatedException.class)
    @ResponseBody
    public ErrorInfo<String> UserDumplicatedExceptionHandler(HttpServletRequest req, UserDumplicatedException e) throws Exception{

        ErrorInfo<String> res = new ErrorInfo<>();
        res.setMessage(e.getMessage());
        res.setCode(ErrorInfo.ERROR);
        res.setUrl(req.getRequestURL().toString());
        return res;
    }
}


