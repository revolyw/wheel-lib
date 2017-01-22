package org.yecq.sample.springbootrmi.rmi.exporter;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.yecq.sample.springbootrmi.service.XiaomingService;

/**
 *
 * @author yecq
 */
@Component("remoteXiaomingService1") // 这个必须要
public class RemoteXiaomingServiceImpl implements RemoteXiaomingService {

    @Autowired
    private XiaomingService cs;

    @Override
    public Map getOwn() {
        return cs.getOwn();
    }
}
