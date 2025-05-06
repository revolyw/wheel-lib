package cn.willow.demo.troubleshooting.memory.leak;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author willow
 * @since 2024/12/27
 */
@RestController
@RequestMapping("/troubleshooting/memory/leak")
public class ThreadLockLeakDemoController {
    @RequestMapping(value = "/test0")
    public String test0(HttpServletRequest request) {
        ThreadLocal<Byte[]> localVariable = new ThreadLocal<>();
        localVariable.set(new Byte[4096*1024]);// 为线程添加变量
        return "success";
    }
}
