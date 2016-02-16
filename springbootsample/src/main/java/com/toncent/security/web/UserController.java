package com.toncent.security.web;

import com.toncent.security.domain.User;
import com.toncent.security.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * AUTHOR: 819521
 * DATE  : 2016/2/3
 * TIME  : 17:02
 */

@Controller
@RequestMapping(value = {"/web/user", "/wx/user"})
public class UserController {

    @Autowired
    private SecurityService securityService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        Iterable<User> users = securityService.listUsers();
        model.addAttribute("users", users);
        return "user/list";
    }


    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(Model model) {
        model.addAttribute("user", new User());
        return "user/edit";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String save(@Valid @ModelAttribute("user") User user, BindingResult result) {
        if (result.hasErrors()) {
            return "user/edit";
        }
        securityService.saveUser(user);
        return "redirect:/user/list";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(Model model, @PathVariable("id") Long id) {
        User user = securityService.findUserById(id);
        model.addAttribute("user", user);

        return "user/edit";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public String update(@PathVariable("id") Long id, @Valid @ModelAttribute("user") User user, BindingResult result) {
        if (result.hasErrors()) {
            return "user/edit";
        }
        securityService.saveUser(user);
        return "redirect:/user/list";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public String delete(@PathVariable("id") Long id) {
        securityService.deleteUserById(id);
        return "deleted successfully!";
    }

    @RequestMapping(value = "/deletes", method = RequestMethod.POST)
    @ResponseBody
    public String deletes(@RequestParam(value = "ids", defaultValue = "0") Long[] ids) {
        securityService.deleteUserByIds(ids);
        return "deleted successfully!";
    }

}
