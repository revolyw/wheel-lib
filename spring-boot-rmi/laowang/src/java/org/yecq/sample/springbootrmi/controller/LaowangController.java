package org.yecq.sample.springbootrmi.controller;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.yecq.sample.springbootrmi.service.LaowangService;

/**
 *
 * @author yecq
 */
@RestController
@EnableAutoConfiguration
public class LaowangController {

    @Autowired
    private LaowangService us;

    // 获取小明的信息
    @RequestMapping("/xiaoming")
    public Map do_getXiaoming() {
        return us.getXiaoming();
    }
}
