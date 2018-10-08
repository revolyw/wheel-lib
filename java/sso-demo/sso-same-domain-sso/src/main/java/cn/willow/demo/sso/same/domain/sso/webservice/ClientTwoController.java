package cn.willow.demo.sso.same.domain.sso.webservice;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class ClientTwoController {
    @RequestMapping("/client2")
    public String client2(Model model,HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (null != cookies) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("sso") && cookie.getValue().equals("ticket")) {
                    return "client2";
                }
            }
        }
        model.addAttribute("callback", "/client2");
        return "login";
    }
}
