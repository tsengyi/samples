package com.toncent.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * AUTHOR: 819521
 * DATE  : 2016/2/3
 * TIME  : 17:02
 */

@Controller
@RequestMapping(value = "/task")
public class TaskController {

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public String list() {
        System.out.println("true = " + true);
        return "list";
    }
}
