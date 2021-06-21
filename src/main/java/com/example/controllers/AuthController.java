
//package com.example.controllers;
//
//import com.example.models.Auth;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import javax.servlet.http.HttpSession;
//
//@Controller
//public class AuthController {
//    @RequestMapping(value = "/", method = RequestMethod.GET)
//    public String index() {
//        return "index";
//    }
//
//    @RequestMapping(value = "/login", method = RequestMethod.GET)
//
//    public String login(HttpSession session) {
//        if (session.getAttribute("isLogin") != null &&
//                (Boolean) session.getAttribute("isLogin") == true) {
//
//            return "redirect:/success";
//        }
//        return "login";
//    }
//
//    @RequestMapping(value = "/login", method = RequestMethod.POST)
//    public String checkLogin(@RequestParam("user") String user,
//                             @RequestParam("pass") String pass, HttpSession session) {
//
//        if ("123456".equals(pass)) {
//            if ("admin".equals(user)) {
//                session.setAttribute("isLogin", true);
//                session.setAttribute("user", user);
//                session.setAttribute("role", Auth.Role.ADMIN);
//
//                return "redirect:/success";
//            } else if ("emp".equals(user)) {
//                session.setAttribute("isLogin", true);
//                session.setAttribute("user", user);
//                session.setAttribute("role", Auth.Role.EMPLOYEE);
//
//                return "redirect:/success";
//            }
//        }
//
//        return "login";
//    }
//
//    @RequestMapping(value = "/deny", method = RequestMethod.GET)
//    public String deny() {
//
//        return "deny";
//    }
//
//    @Auth(role = Auth.Role.LOGIN)
//    @RequestMapping(value = "/success", method = RequestMethod.GET)
//    public String needLogin() {
//        return "success";
//    }
//
//    @Auth(role = Auth.Role.LOGIN)
//    @RequestMapping(value = "/logout", method = RequestMethod.GET)
//    public String logout(HttpSession session) {
//        session.removeAttribute("isLogin");
//        session.removeAttribute("user");
//        session.removeAttribute("role");
//
//        return "redirect:/";
//    }
//
//    @Auth(role = Auth.Role.ADMIN)
//    @RequestMapping(value = "/admin", method = RequestMethod.GET)
//    public String admin() {
//
//        return "admin";
//    }
//
//    @Auth(role = Auth.Role.EMPLOYEE)
//    @RequestMapping(value = "/emp", method = RequestMethod.GET)
//    public String employee() {
//
//        return "emp";
//    }
//}

