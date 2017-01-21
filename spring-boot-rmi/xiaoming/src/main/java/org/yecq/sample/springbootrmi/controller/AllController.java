package org.yecq.sample.springbootrmi.controller;

import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

/**
 *
 * @author yecq
 */
@Import({XiaomingController.class})
@ImportResource("classpath:applicationContext.xml")
public final class AllController {
}
