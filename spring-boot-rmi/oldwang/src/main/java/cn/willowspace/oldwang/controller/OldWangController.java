package cn.willowspace.oldwang.controller;

import cn.willowspace.oldwang.service.OldWangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by Willow on 1/22/17.
 */
@RestController
@EnableAutoConfiguration
public class OldWangController {
    private final OldWangService oldWangService;

    @Autowired
    public OldWangController(@Qualifier("oldWangServiceImpl") OldWangService oldWangService) {
        this.oldWangService = oldWangService;
    }

    @RequestMapping("/")
    public Object index() {
        return insertGreenQiangSWife();
    }

    @RequestMapping("/insert_greenqiang_s_wife")
    public Map<String, String> insertGreenQiangSWife() {
        Map<String,String> result = oldWangService.insertGreenQiangSWife();
        return result;
    }
}

