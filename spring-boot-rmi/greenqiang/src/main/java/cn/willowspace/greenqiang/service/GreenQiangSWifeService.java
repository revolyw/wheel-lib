package cn.willowspace.greenqiang.service;

import java.util.Map;

/**
 * 绿强老婆暴露的服务实现
 * We will start exposing the service to a remote client by using RMI and talk a bit about the drawbacks of using RMI. We’ll then continue to show an example using Hessian as the protocol.
 * Exposing services using RMI
 * Created by Willow on 1/22/17.
 */
public interface GreenQiangSWifeService {
    Map<String,String> waitInsert();
}
