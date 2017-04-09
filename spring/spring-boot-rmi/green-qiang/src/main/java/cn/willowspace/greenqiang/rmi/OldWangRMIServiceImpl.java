package cn.willowspace.greenqiang.rmi;

import cn.willowspace.greenqiang.controller.GreenQiangController;
import cn.willowspace.greenqiang.service.GreenQiangSWifeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 绿强老婆暴露的服务
 * Created by Willow on 1/22/17.
 */
@Service
public class OldWangRMIServiceImpl implements OldWangRMIService {
    private final GreenQiangSWifeService greenQiangSWifeService;

    @Autowired
    public OldWangRMIServiceImpl(@Qualifier("greenQiangSWifeServiceImpl") GreenQiangSWifeService greenQiangSWifeService) {
        this.greenQiangSWifeService = greenQiangSWifeService;
    }


    @Override
    public Map<String, String> waitInsert() {
        Map<String, String> result = greenQiangSWifeService.waitInsert();
        GreenQiangController.isGreen = true;
        return result;
    }
}
