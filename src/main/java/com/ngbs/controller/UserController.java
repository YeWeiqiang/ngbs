package com.ngbs.controller;

import com.ngbs.common.Const;
import com.ngbs.common.ServerResponse;
import com.ngbs.pojo.User;
import com.ngbs.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user/")
public class UserController {

    @Autowired
    private IUserService iUserService;

    @RequestMapping("login")
    private String login() {
        return "/login";
    }


    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String login(@RequestParam(value = "userno") Integer userno,
                        @RequestParam(value = "password") String password,
                        HttpSession session) {

        ServerResponse<User> response = iUserService.login(userno, password);
        if (response.isSuccess()) {
            session.setAttribute(Const.CURRENT_USER, response.getData());
        }

        return "redirect:/card/publishcard";
//        return "/publishcard";

    }

}
