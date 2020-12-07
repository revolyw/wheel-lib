package cn.willow.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class GoController implements EnvironmentAware {
    private final Log logger = LogFactory.getLog(GoController.class);
    /**
     * 包含 servletConfig、servletContext 以及 SystemEnvironment
     *
     */
    private Environment environment;

    @RequestMapping(value = {"/"}, method = RequestMethod.HEAD)
    public String head() {
        return "go.jsp";
    }

    /**
     * 如果没有配置 {@link org.springframework.web.servlet.ViewResolver}
     * Spring MVC 将默认使用 {@link org.springframework.web.servlet.view.InternalResourceViewResolver}
     * 而且其 prefix 和 suffix 都为空
     * 所以 go.jsp 返回值对应的就是根目录下的 go.jsp 文件
     *
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/index", "/"}, method = RequestMethod.GET)
    public String index(Model model) throws Exception {
        logger.info("processed by index");
        model.addAttribute("msg", "GO GO GO!");
        return "go.jsp";
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }
}
