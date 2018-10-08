package cn.willow.demo.sso.same.domain.sso.webservice;

import cn.willow.demo.sso.same.domain.sso.Cgi;
import cn.willow.demo.sso.same.domain.sso.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import sun.rmi.log.LogOutputStream;

import javax.servlet.http.Cookie;

@Controller
@RequestMapping("/")
public class LoginController {
    private final static User USER = new User("username", "password");

    @RequestMapping("/login")
    public String doLogin(Cgi cgi) {
        String username = cgi.getString("username", "");
        String password = cgi.getString("password", "");
        String callback = cgi.getString("callback", "success");
        if (username.equals(USER.getUsername()) && password.equals(USER.getPassword())) {
            Cookie cookie = new Cookie("sso", "ticket");
            cookie.setPath("/");
            cgi.getRep().addCookie(cookie);
            if (callback.equals("success")) {
                return "success";
            }
            return "redirect:" + callback;
        }
        return null;
    }

    @RequestMapping("/logout")
    public String doLogout(Cgi cgi) {
        String callback = cgi.getString("callback", "logout");
        Cookie cookie = new Cookie("sso", "ticket");
        cookie.setPath("/");
        cookie.setMaxAge(0);
        cgi.getRep().addCookie(cookie);
        if (callback.equals("logout")) {
            return "logout";
        }
        return "redirect:" + callback;
    }
}
