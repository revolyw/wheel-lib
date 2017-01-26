package cn.willowspace.greenqiang.controller;

import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;


/**
 * Created by Willow on 1/22/17.
 */
@Import({GreenQiangController.class})
@ImportResource("classpath:applicationContext.xml")
public class AppController {
}
