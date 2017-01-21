package org.yecq.sample.springbootrmi.service;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.yecq.sample.springbootrmi.rmi.use.FromRemoteXiaomingService;

/**
 *
 * @author yecq
 */
@Service
@Transactional
public class LaowangService {

    @Autowired
    private JdbcTemplate jdbc;

    @Autowired
    @Qualifier("remoteXiaomingService")
    private FromRemoteXiaomingService rxs;

    // 获得小明的信息
    public Map getXiaoming() {
        System.out.println("开始进行远程rmi调用...");
        return rxs.getOwn();
    }

    // 获得自己的信息
    public Map getOwn() {
        Map info = new HashMap();
        info.put("id", 43);
        info.put("name", "老王");
        return info;
    }
}
