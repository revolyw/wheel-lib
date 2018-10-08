package cn.willow.demo.sso.same.domain.sso;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by Willow on 5/2/17.
 */
@Getter
@Setter
@AllArgsConstructor
public class Cgi {
    private HttpServletRequest req;
    private HttpServletResponse rep;

    public HttpSession getSession() {
        return this.req.getSession();
    }

    public String getString(String key, String defaultValue) {
        String value = req.getParameter(key);
        if (StringUtils.isEmpty(value))
            return defaultValue;
        return value;
    }
}
