package cn.willow.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Willow on 9/10/17.
 */
@RequestMapping("/")
@RestController
public class AccessController {
    @RequestMapping("/get/data")
    @ResponseBody
    public JSONObject getData() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("data", "data");
        return jsonObject;
    }
}
