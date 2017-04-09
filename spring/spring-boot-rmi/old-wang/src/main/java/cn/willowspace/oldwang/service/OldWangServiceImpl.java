package cn.willowspace.oldwang.service;

import cn.willowspace.oldwang.rmi.GreenQiangSWifeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 老王对外提供的服务实现
 * Created by Willow on 1/22/17.
 */
@Service
public class OldWangServiceImpl implements OldWangService {
    private final GreenQiangSWifeService greenQiangSWifeService;

    @Autowired
    public OldWangServiceImpl(@Qualifier("remoteGreenQiangService") GreenQiangSWifeService greenQiangSWifeService) {
        this.greenQiangSWifeService = greenQiangSWifeService;
    }

    public Map<String, String> insertGreenQiangSWife() {
        Map<String ,String> result = greenQiangSWifeService.waitInsert();
        return result;
    }
}
