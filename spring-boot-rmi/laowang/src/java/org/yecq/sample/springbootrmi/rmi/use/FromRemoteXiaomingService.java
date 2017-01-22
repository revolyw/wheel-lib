package org.yecq.sample.springbootrmi.rmi.use;

import java.util.Map;

/**
 *
 * @author yecq
 */

// 这个接口名不一定要与远程的接口名一样，但方法签名要一样
// 为了方便起见，还是取一样的接口名为好
// 不足之处是：必须等远程先起来，才可以，如果要这两个服务相互调用，还得想新的办法

public interface FromRemoteXiaomingService {

    public Map getOwn();
}
