package com.list.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

/**
 * Created by IntelliJ IDEA
 *
 * @Author : C22
 * @create 2022/9/16 18:16
 */

@Controller
public class LoginController {

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/dologin")
    public String doLogin(@RequestParam(value = "password", required = false) String password, HttpSession session) {
        if ("123456".equals(password)) {
            session.setAttribute("password", password);
            return "redirect:/";
        } else {
            return "login";
        }
    }
}
