/**
 * Aistarfish.com Inc.
 * Copyright (c) 2017-2025 All Rights Reserved.
 */
package
        cn.willow.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author willow
 * Created by on 2025-07-25 14:51
 */
@RestController
public class Controller {
    @Autowired
    private TestPrototype testPrototype;
    @GetMapping("/")
    public String test() {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        RequestContextHolder.setRequestAttributes(servletRequestAttributes,false);
        CompletableFuture<String> a = CompletableFuture.supplyAsync(()->{
            //异步线程处理任务不适合 request scope
            try {
                Thread.sleep(1000);
                System.out.println("thread" + testPrototype.toString() + "-" + testPrototype.getId());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "";
        });
        try {
            a.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
        String s = "main:" + testPrototype.toString() + "-" + testPrototype.getId();
        System.out.println(s);
        return s;
    }
}