/**
 * Aistarfish.com Inc.
 * Copyright (c) 2017-2025 All Rights Reserved.
 */
package
        cn.willow.demo;

import lombok.Getter;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import java.util.random.RandomGenerator;

/**
 * @author willow
 * Created by on 2025-07-25 14:52
 */
@Getter
@RequestScope
@Component
public class TestPrototype {
    private int id = RandomGenerator.getDefault().nextInt();
}