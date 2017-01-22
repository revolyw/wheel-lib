package org.yecq.sample.springbootrmi.service;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author yecq
 */
@Service
@Transactional
public class XiaomingService {

    @Autowired
    private JdbcTemplate jdbc;

    // 获得自己的信息
    public Map getOwn() {
        Map info = new HashMap();
        info.put("id", 15);
        info.put("name", "小明_new");
        System.out.println("服务被rmi调用");
        return info;
    }
}
