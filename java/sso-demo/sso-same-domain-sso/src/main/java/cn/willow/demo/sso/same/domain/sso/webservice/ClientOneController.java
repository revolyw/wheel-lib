package cn.willow.demo.sso.same.domain.sso.webservice;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class ClientOneController {
    @RequestMapping("/client1")
    public String client1(Model model, HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (null != cookies) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("sso") && cookie.getValue().equals("ticket")) {
                    return "client1";
                }
            }
        }
        model.addAttribute("callback", "/client1");
        return "login";
    }
}
