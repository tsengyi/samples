package com.toncent.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * AUTHOR: 819521
 * DATE  : 2016/2/6
 * TIME  : 15:04
 */
@Controller
public class CustomErrorController implements ErrorController {

    private static final String PATH = "/error";


    private boolean debug = true;

    @Autowired
    private ErrorAttributes errorAttributes;

    @RequestMapping(value = PATH)
    @ResponseBody
    Map<String, Object> error(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> errorAttributes = getErrorAttributes(request, debug);

        return errorAttributes;
    }

    @Override
    public String getErrorPath() {
        return PATH;
    }

    private Map<String, Object> getErrorAttributes(HttpServletRequest request, boolean includeStackTrace) {
        RequestAttributes requestAttributes = new ServletRequestAttributes(request);
        return errorAttributes.getErrorAttributes(requestAttributes, includeStackTrace);
    }

}