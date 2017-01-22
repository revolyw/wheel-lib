package cn.willowspace.greenqiang.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 绿强老婆暴露的服务实现
 * Created by Willow on 1/22/17.
 */
@Service
public class GreenQiangSWifeServiceImpl implements GreenQiangSWifeService {
    private String name;

    public GreenQiangSWifeServiceImpl() {
        this.name = "gree qiang's wife";
    }

    public Map waitInsert() {
        Map<String, String> info = new HashMap<>();
        info.put("info", "insert " + name + " success!");
        return info;
    }
}
