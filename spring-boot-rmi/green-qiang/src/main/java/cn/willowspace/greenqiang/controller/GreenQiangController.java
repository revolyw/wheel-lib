package cn.willowspace.greenqiang.controller;


import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Willow on 1/22/17.
 */
@RestController
@EnableAutoConfiguration
public class GreenQiangController {
    public static boolean isGreen = false;

    @RequestMapping("/")
    public Object index() {
        return amIGreen();
    }

    @RequestMapping("/am_i_green")
    public boolean amIGreen() {
        return GreenQiangController.isGreen;
    }

    @RequestMapping("/time_back_to_no_green")
    public boolean timeBackToNoGreen(){
        boolean result = GreenQiangController.isGreen;
        GreenQiangController.isGreen = false;
        return result;
    }
}
