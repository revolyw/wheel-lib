package org.yecq.sample.springbootrmi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RestController;
import org.yecq.sample.springbootrmi.service.XiaomingService;

/**
 *
 * @author yecq
 */
@RestController
@EnableAutoConfiguration
public class XiaomingController {

    @Autowired
    private XiaomingService us;
}
